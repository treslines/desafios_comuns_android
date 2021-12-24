package com.progdeelite.dca.language

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.progdeelite.dca.MainActivity
import com.progdeelite.dca.R
import com.progdeelite.dca.util.getSharedPrefs
import com.progdeelite.dca.util.setColor
import timber.log.Timber

/**
 * Base class for all fragments with a generic view binding and an activity callback,
 * which is cleared and assigned in the lifecycle methods of this base fragment.
 * The generic view binding avoids boilerplate code and the activity callback allows
 * any extending fragment to call methods of the parent activity without casting it to a
 * specific type.
 **/

// 1) Como criar uma classe base para seus fragments
// 2) Como automatizar e simplificar o desenvolvimento reusando código
// 3) Como aplicar/usar essa classe na prática
abstract class BaseFragment<viewBinding : ViewBinding> : Fragment() {

    private var _binding: viewBinding? = null
    private var activityCallBack: ActivityCallback? = null

    // 1) Como mudar de idioma sem re-iniciar seu app (Model View Language)
    // 2) Como tornar uma simples mudança de idioma em uma experiência divertida/difernciada para o usuário
    // 3) Onde definir os recursos de tradução neste modelo (raw)
    // 3) Como usar na prática e como animar a troca de idiomas (total liberdade de customização)
    private val translationModel: LanguageViewModel by viewModels()

    protected val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        translationModel.translate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // DETALHE IMPORTANTE
        translationModel.observableLanguageResource.observe(viewLifecycleOwner) { translateUi(it) }
        translationModel.initAppLanguage(requireContext(), requireContext().getSharedPrefs())
        initializeUi()
        setHasOptionsMenu(showActionBarOptionMenu())
    }

    protected open fun showActionBarOptionMenu(): Boolean = false

    /** One of those: AppLanguagesSettings */
    protected fun setLanguage(appLanguage: String){
        translationModel.setAppLanguage(requireContext(), appLanguage, requireContext().getSharedPrefs())
    }

    /** Used to translate UI dynamically without re-starting/re-loading the app possibly loosing data/states/flows */
    protected abstract fun translateUi(resource: LanguageResource)

    /** Inflates and returns the fragment's corresponding view binding.
     * Ex:YourClassNameBinding.inflate(layoutInflater) */
    protected abstract fun getViewBinding(): viewBinding

    /** fragment method to initialize UI components. */
    protected abstract fun initializeUi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return _binding?.root
    }

    /**
     * Cast the context to the interface implemented in the host activity,
     * so we can access activity methods from all fragments in that activity.
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            activityCallBack = context as ActivityCallback
        } catch (classCastException: ClassCastException) {
            Timber.d(classCastException, "The activity does not implement the activity callback")
        }
    }

    /** Clear the activity callback binding to avoid memory leaks. */
    override fun onDetach() {
        super.onDetach()
        activityCallBack = null
    }

    /** Clear the view binding to avoid memory leaks. */
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /**
     * Allow custom back pressed actions in fragments,
     * which get executed before the default behavior
     * of the activity´s onBackPressed
     */
    protected fun setOnBackPressedCallback(backPressAction: () -> Unit = {}) {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    backPressAction()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    protected fun log(msg: String) = Timber.d(msg)

    // 1) como e onde definir o progressbar da aplicação
    // 2) Como exibir ou esconder o progressbar
    // 3) como chamar os metodos partindo do fragment
    fun startLoading() = activityCallBack?.showLoadingSpinner()
    fun stopLoading() = activityCallBack?.hideLoadingSpinner()

    // 1) Como se comunicar com a main activity
    // 2) como definir callbacks
    // 3) como chamar os metodos partindo do fragment
    fun showAppBarBackButton(show: Boolean = true) {
        if (show) {
            val backArrow = ContextCompat.getDrawable(requireContext(), R.drawable.ic_back)
            backArrow?.setColor(requireContext(), R.color.image_button)
            (activityCallBack as MainActivity).supportActionBar?.setHomeAsUpIndicator(backArrow)
            activityCallBack?.showAppBarBackButton(show)
        } else {
            activityCallBack?.showAppBarBackButton(false)
        }
    }

    fun showAppBarTitle() = activityCallBack?.showAppBarBackButton(true)
    fun hideAppBarTitle() = activityCallBack?.showAppBarBackButton(false)
    fun showActionBar() = activityCallBack?.showActionBar(true)
    fun hideActionBar() = activityCallBack?.showActionBar(false)
}