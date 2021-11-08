package com.progdeelite.dca.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentTicketSelectionBinding
import com.progdeelite.dca.util.toast

// Video bottom sheet dialog: https://youtu.be/vku9pMNHT9o
class TicketSelectionFragment(private val showAddItem: Boolean) : BottomSheetDialogFragment(),
    OnItemClickListener<TicketSelectionElement> {
    private val binding by viewBinding(FragmentTicketSelectionBinding::bind)

    private val bottomSheetElements = ArrayList<TicketSelectionElement>()
    private lateinit var ticketAdapter: TicketSelectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ticket_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Apenas para fins didáticos, poderia pegar isso de
        // algum banco de dados ou repository local e popular
        mockSomeTickets()

        val recyclerView = binding.tickets
        // IMPORTANTE: vc tem que entender isso aqui (esse "this" é o listener)
        ticketAdapter = TicketSelectionAdapter(bottomSheetElements, this)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ticketAdapter
    }

    override fun onItemClicked(item: TicketSelectionElement) {
        when (item.type) {
            TicketSelectionType.SELECT -> onTicketSelected(item.title)
            TicketSelectionType.ADD -> onAdd()
            TicketSelectionType.ENTER_MANUAL -> onEnterManual()
        }
        dismiss()
    }

    private fun onTicketSelected(ticketNumber: String) {
        toast("Ticket selecionado: $ticketNumber")
    }

    private fun onAdd() {
        toast("Adicionar")
    }

    private fun onEnterManual() {
        toast("Adicionar manualmente")
    }

    private fun mockSomeTickets() {
        bottomSheetElements.add(
            TicketSelectionElement(
                R.drawable.ic_error,
                "Ticket Expirado",
                true,
                TicketSelectionType.SELECT
            )
        )
        bottomSheetElements.add(
            TicketSelectionElement(
                R.drawable.ic_success,
                "Ticket Valido",
                false,
                TicketSelectionType.SELECT
            )
        )
        bottomSheetElements.add(
            TicketSelectionElement(
                R.drawable.ic_success,
                "Ticket Valido",
                false,
                TicketSelectionType.ENTER_MANUAL
            )
        )
        bottomSheetElements.add(
            TicketSelectionElement(
                R.drawable.ic_success,
                "Ticket Valido",
                false,
                TicketSelectionType.ENTER_MANUAL
            )
        )
    }
}