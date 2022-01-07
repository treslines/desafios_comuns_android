package com.progdeelite.dca.searchview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.progdeelite.dca.MainActivity
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentSearchViewBinding
import com.progdeelite.dca.design.getListDivider
import com.progdeelite.dca.filter.Alarm
import com.progdeelite.dca.language.ActivityCallback
import com.progdeelite.dca.util.hideActionBar
import com.progdeelite.dca.util.hideKeyboard
import com.progdeelite.dca.util.setVisible
import com.progdeelite.dca.util.toast

// 1) Como criar um filtro de busca livre (layout)
// 2) Como fazer o setup dos filtros
// 3) Como exibir o scrollview na ultima posição selectionada
// 4) Como adicionar os item divider na recyclerview
class SearchViewFragment : Fragment(R.layout.fragment_search_view), AlarmListAdapter.OnAlarmInteraction {

    // VEJA TBM VIDEO COMO DELEGAR VIEW BINDING
    private val binding by viewBinding(FragmentSearchViewBinding::bind)

    private var scrollPosition = -1
    private lateinit var searchView: SearchView
    private var llm: LinearLayoutManager? = null
    // LISTAS PARA AUXILIAR FILTRAGEM
    private var fullAlarmList: MutableList<Alarm> = mutableListOf()
    private var filteredAlarmList: MutableList<Alarm> = mutableListOf()
    // MAP PARA AUXILIAR SETUP DOS FILTROS
    private lateinit var filterMap: Map<String, MutableList<String>>
    // VIDE VIDEO: COMO SE COMUNICAR COM ACTIVITIES / CALLBACKS
    private lateinit var activityCallback: ActivityCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        // VEJA COMO SE COMUNICAR COM ACTIVITY / CALLBACKS
        activityCallback = requireActivity() as ActivityCallback
        activityCallback.showAppBarBackButton(false)
        activityCallback.showAppBarTitle(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // PARTE INTERESSANTE, SETUP DO FILTRO (VEREMOS NO FINAL)
        setupMockData(AlarmListAdapter(this))
        setUpFilterMenu()

        // COMO RECUPERAR ULTIMA POSICÃO DA RECYCLERVIEW
        savedInstanceState?.let {
            binding.recyclerview.scrollToPosition(it.getInt("SCROLL_POS"))
        }
    }

    private fun setupMockData(alarmListAdapter: AlarmListAdapter?) {
        fullAlarmList = AlarmMock.getAlarmList()
        filteredAlarmList.addAll(fullAlarmList)
        alarmListAdapter?.let { setUpRecyclerView(it) }
        alarmListAdapter?.submitList(filteredAlarmList)
    }

    private fun setUpRecyclerView(alarmListAdapter: AlarmListAdapter) {
        with(binding) {

            recyclerview.apply {
                llm = LinearLayoutManager(requireContext())

                layoutManager = llm
                adapter = alarmListAdapter

                llm?.let {
                    // VEJA VIDEO COMO CRIAR ITEM DECORATORS EM RECYCLERVIEW
                    addItemDecoration(getListDivider(requireContext().resources, recyclerview, it))
                }

                // COMO PEGAR A POSICÃO DO PRIMEIRO ELEMENTO VISÍVEL
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        llm?.let { scrollPosition = it.findFirstVisibleItemPosition() }
                    }
                })
            }
        }
    }

    // SALVAR ULTIMA POSICÃO VISÍVEL PARA QUANDO RETORNAR SABER ONDE EXIBIR
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("SCROLL_POS", scrollPosition)
        super.onSaveInstanceState(outState)
    }

    // VEJA VÍDEO COMO CRIAR MENU EM ACTIONBAR
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_filter_menu -> {
                when {
                    searchView.hasFocus() -> {
                        searchView.clearFocus()
                        binding.filterComponent.alarmFilterComponent.setVisible(true)
                        // VEJA VIDEO COMO ESCONDER KEYBOARD COM EXTENSÕES (TOP)
                        hideKeyboard()
                    }
                    else -> {
                        with(binding.filterComponent.alarmFilterComponent) {
                            setVisible(!isVisible)
                            this@SearchViewFragment.hideKeyboard()
                        }
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // OBTENDO A VIEW DE BUSCA
        inflater.inflate(R.menu.search_view, menu)
        val item = menu.findItem(R.id.action_text_filter_menu)
        searchView = item.actionView as SearchView

        // REMOVE ESPACO EM FRENTE DO CAMPO DE BUSCA
        searchView.setIconifiedByDefault(false)
        searchView.queryHint = "Busca de texto livre"

        // TRATAR GANHO OU PERDA DE FOCUS
        searchView.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                hideKeyboard()
            } else {
                binding.filterComponent.alarmFilterComponent.setVisible(false)
            }
        }

        // REAGINDO AO FOCUS NO TEXTO (LIMPAR CHIPS, FECHAR KEYBOARD)
        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if(hasFocus){
                binding.filterComponent.clearFilterButton.performClick()
            }
            with(binding.filterComponent.alarmFilterComponent) {
                setVisible(!hasFocus)
                this@SearchViewFragment.hideKeyboard()
            }
        }
        // LISTENER DE TOQUE NO ICONE (FECHAR KEYBOARD SE ABERTA)
        searchView.setOnSearchClickListener {
            hideKeyboard()
            binding.filterComponent.alarmFilterComponent.setVisible(false)
        }

        // LISTENER DE BUSCA TEXTUAL
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.filterComponent.alarmFilterComponent.setVisible(false)
                return false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    filteredAlarmList.clear()
                    when {
                        newText.isNotEmpty() -> {
                            // LISTA CHEIA
                            fullAlarmList.forEach { alarm ->
                                if (alarm.anyMatch(newText)) { // VEJA VIDEO DE LOGICA DE FILTRAGEM (TOP)
                                    // LISTA FILTRADA
                                    filteredAlarmList.add(alarm)
                                }
                            }
                            binding.recyclerview.adapter?.notifyDataSetChanged() // IMPORTANTE
                        }
                        else -> {
                            filteredAlarmList.clear()
                            filteredAlarmList.addAll(fullAlarmList)
                            binding.recyclerview.adapter?.notifyDataSetChanged() // IMPORTANTE
                        }
                    }
                }
                return false
            }
        })
    }

    override fun onAlarmClicked(alarmClicked: Alarm) {
        toast("Cliquei!")
    }

    @SuppressLint("InflateParams")
    private fun setUpFilterMenu() {

        filterMap = mapOf(
            "Tipo" to mutableListOf(),
            "Prioridade" to mutableListOf(),
            "Status" to mutableListOf(),
            "Ocorrência" to mutableListOf()
        )
        // setup labels
        with(binding) {
            filterComponent.filterLabel.text = "Filters"
            filterComponent.typeFilter.filterTitle.text = "Tipo"
            filterComponent.priorityFilter.filterTitle.text = "Prioridade"
            filterComponent.statusFilter.filterTitle.text = "Status"
            filterComponent.timeFilter.filterTitle.text = "Ocorrência"
        }

        // setup listeners
        with(binding.filterComponent) {
            closeFilterButton.setOnClickListener {
                alarmFilterComponent.setVisible(false)
            }
            innerFilterComponent.setOnClickListener { hideKeyboard() }
        }

        with(binding.filterComponent.clearFilterButton) {
            setOnClickListener {
                filterMap.entries.forEach { it.value.clear() }
                with(binding.filterComponent) {
                    typeFilter.chipGroup.checkedChipIds.forEach { id ->
                        (typeFilter.chipGroup.findViewById(id) as Chip).isChecked = false
                    }
                    statusFilter.chipGroup.checkedChipIds.forEach { id ->
                        (statusFilter.chipGroup.findViewById(id) as Chip).isChecked = false
                    }
                    priorityFilter.chipGroup.checkedChipIds.forEach { id ->
                        (priorityFilter.chipGroup.findViewById(id) as Chip).isChecked = false
                    }
                    timeFilter.chipGroup.checkedChipIds.forEach { id ->
                        (timeFilter.chipGroup.findViewById(id) as Chip).isChecked = false
                    }
                }
                hideKeyboard()
                resetFilters()
            }
        }

        with(binding.filterComponent.typeFilter.chipGroup) {
            resources.getStringArray(R.array.filter_type).forEach { key ->
                val filterKey = binding.filterComponent.typeFilter.filterTitle.text.toString()
                val chipView = layoutInflater.inflate(R.layout.alarm_list_item_chip, null) as Chip
                chipView.text = key
                chipView.setOnCheckedChangeListener { _, isChecked ->
                    toast("Faça o que quiser")
                }
                addView(chipView)
            }
        }

        with(binding.filterComponent.priorityFilter.chipGroup) {
            resources.getStringArray(R.array.filter_priority).forEach { key ->
                val filterKey = binding.filterComponent.priorityFilter.filterTitle.text.toString()
                val chipView = layoutInflater.inflate(R.layout.alarm_list_item_chip, null) as Chip
                chipView.text = key
                chipView.setOnCheckedChangeListener { _, isChecked ->
                    toast("Faça o que quiser")
                }
                addView(chipView)
            }
        }

        with(binding.filterComponent.statusFilter.chipGroup) {
            resources.getStringArray(R.array.filter_status).forEach { key ->
                val filterKey = binding.filterComponent.statusFilter.filterTitle.text.toString()
                val chipView = layoutInflater.inflate(R.layout.alarm_list_item_chip, null) as Chip
                chipView.text = key
                chipView.setOnCheckedChangeListener { _, isChecked ->
                    toast("Faça o que quiser")
                }
                addView(chipView)
            }
        }

        with(binding.filterComponent.timeFilter.chipGroup) {
            resources.getStringArray(R.array.filter_time).forEach { key ->
                val filterKey = binding.filterComponent.timeFilter.filterTitle.text.toString()
                val chipView = layoutInflater.inflate(R.layout.alarm_list_item_chip, null) as Chip
                chipView.text = key
                chipView.setOnCheckedChangeListener { _, isChecked ->
                    toast("Faça o que quiser")
                }
                addView(chipView)
            }
        }
    }

    private fun resetFilters() {
        // reset os filtros se quiser
    }
}