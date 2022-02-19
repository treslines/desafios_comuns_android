package com.progdeelite.dca.util_extension

import android.animation.ObjectAnimator
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.*
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.progdeelite.dca.BuildConfig
import com.progdeelite.dca.R
import com.progdeelite.dca.item_decoration.HeadlineItemDecorator
import com.progdeelite.dca.item_decoration.ItemDecoratorWithText

fun View.startAnimation(anim: Animation, onEnd: () -> Unit) {
    anim.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationEnd(anim: Animation?) = onEnd()
        override fun onAnimationRepeat(anim: Animation?) = Unit
        override fun onAnimationStart(anim: Animation?) = Unit
    })
    this.startAnimation(anim)
}

fun View.setVisible(show: Boolean) {
    if (show) this.visibility = View.VISIBLE else this.visibility = View.GONE
}

@RequiresApi(Build.VERSION_CODES.R)
/** ESCONDE O TECLADO */
fun View.showKeyboard(view: View) {
    windowInsetsController?.show(WindowInsets.Type.ime())
    view.requestFocus()
}

@RequiresApi(Build.VERSION_CODES.R)
/** EXIBE O TECLADO */
fun View.hideKeyboard(view: View) {
    windowInsetsController?.hide(WindowInsets.Type.ime())
    view.clearFocus()
}

// PRÁTICO PARA USAR APÓS VERIFICACÕES DE ENTRADAS
fun TextInputLayout.shake(onEndAction: () -> Unit = {}) {
    val startX = 0f
    val translationX = 35f
    val bounceDuration = 700L

    ObjectAnimator.ofFloat(
        this,
        "translationX",
        startX,
        translationX,
        startX
    ).apply {
        interpolator = BounceInterpolator()
        duration = bounceDuration
        start()
    }.doOnEnd { onEndAction() }
}

// Video: Como reagir ao teclado ENTER: xxxxxxx
fun EditText.setOnEnterKeyListener(action: () -> Unit = {}) {
    setOnKeyListener { _, keyCode, event ->
        if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
            action()
            return@setOnKeyListener true
        }
        return@setOnKeyListener false
    }
}

@Suppress("DEPRECATION")
fun Button.setupButtonBackgroundColor(@ColorRes colorId: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        setBackgroundColor(resources.getColor(colorId, null))
    } else {
        setBackgroundColor(resources.getColor(colorId))
    }
}

@Suppress("DEPRECATION")
fun TextView.setTextColorById(@ColorRes colorId: Int){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        setTextColor(resources.getColor(colorId, null))
    } else {
        setTextColor(resources.getColor(colorId))
    }
}

// 1) Como animar troca de idioma
// 2) Como balançar componentes pra cima e pra baixo
fun TextView.animateTranslation(translation: String){
    var index = 0
    val mHandler = Handler(Looper.getMainLooper())
    val characterAdder: Runnable = object : Runnable {
        override fun run() {
            text = translation.subSequence(0, index++)
            if (index <= translation.length) {
                mHandler.postDelayed(this, 10)
            }
        }
    }
    text = ""
    mHandler.removeCallbacks(characterAdder)
    mHandler.postDelayed(characterAdder, 10)
}

fun TextInputLayout.animateTranslation(translation: String){
    var index = 0
    val mHandler = Handler(Looper.getMainLooper())
    val characterAdder: Runnable = object : Runnable {
        override fun run() {
            hint = translation.subSequence(0, index++)
            if (index <= translation.length) {
                mHandler.postDelayed(this, 10)
            }
        }
    }
    hint = ""
    mHandler.removeCallbacks(characterAdder)
    mHandler.postDelayed(characterAdder, 10)
}

fun View.bounceUpAndDown(){
    val startX = 0f
    val translationY = 25f
    val bounceDuration = 450L
    ObjectAnimator.ofFloat(
        this,
        "translationY",
        startX,
        translationY,
        startX
    ).apply {
        interpolator = BounceInterpolator()
        duration = bounceDuration
        start()
    }
}

// USANDO SHAPES - DIVISÓRIA SIMPLES
fun RecyclerView.setSimpleDefaultDivider(@DrawableRes drawableRes: Int) {
    val divider = DividerItemDecoration(this.context,DividerItemDecoration.VERTICAL)
    val drawable = ContextCompat.getDrawable(this.context,drawableRes)
    drawable?.let {
        divider.setDrawable(it)
        addItemDecoration(divider)
    }
}

fun RecyclerView.setCustomHeadlineDivider() = addItemDecoration(HeadlineItemDecorator())

// USANDO LAYOUT DE TEXTO - DIVISÕRIAS CUSTOMIZADAS
fun RecyclerView.attachHeader(str: String) = addItemDecoration(ItemDecoratorWithText(this, str))


// +-----------------------------------------------------------------------------------+
// | VIDEO: EFEITO FADE-IN FADE-OU EM VIEWS: https://youtu.be/XXXXXXXXX                |
// +-----------------------------------------------------------------------------------+
fun View.fadeOut(duration: Long = 0) = animate()
    .also { clearAnimation() }
    .setDuration(duration)
    .alphaBy(-1f)
    .withStartAction { alpha = 1f }
    .withEndAction {
        visibility = View.INVISIBLE
    }
    .start()

fun View.fadeIn(duration: Long = 0, endAction: () -> Unit = {}) = animate()
    .also { clearAnimation() }
    .setDuration(duration)
    .alpha(1f)
    .withStartAction {
        visibility = View.VISIBLE
        alpha = 0f
    }
    .withEndAction {
        setVisible(true)
        endAction()
    }
    .start()

fun ViewGroup.inflate(@LayoutRes layoutResId: Int, attachToRoot: Boolean = false) =
    LayoutInflater.from(context).inflate(layoutResId, this, attachToRoot)

const val PREF_KEY_RATING_DONE_BOOL = "RATING_DONE"
const val PREF_KEY_RATING_INTERVAL_INT = "RATING_INTERVAL"
lateinit var sharedPrefs: SharedPreferences

fun View.feedback() {
    sharedPrefs = context.getSharedPreferences(GLOBAL_SHARED_PREFS, Context.MODE_PRIVATE)
    val ratingDone = sharedPrefs.getBoolean(PREF_KEY_RATING_DONE_BOOL, false)
    // SE JA VOTOU, NÃO ENCHE MAIS O SACO DO TEU USUÁRIO KKKKKK
    if (!ratingDone) {
        var ratingInterval = sharedPrefs.getInt(PREF_KEY_RATING_INTERVAL_INT, 1)
        // VOCE QUE DEFINE O INTERVALO QUE DESEJA, AQUI BOTEI 5 ITERAçÕES DE >>>> SUCESSO!
        if (ratingInterval == 5) {
            val alert: AlertDialog.Builder = AlertDialog.Builder(context)
            val view = LayoutInflater.from(context).inflate(R.layout.dialog_feedback, null)
            val randomTitle = mutableListOf(
                context.getString(R.string.feedbak_rating_title),
                context.getString(R.string.feedbak_rating_title0),
                context.getString(R.string.feedbak_rating_title1),
                context.getString(R.string.feedbak_rating_title2),
                context.getString(R.string.feedbak_rating_title3)
            )
            randomTitle.shuffle() // PRA FICAR LEGAL, DESCONTRAIDO E AUMENTAR A CHANCE DA GALERA VOTAR
            view.findViewById<TextView>(R.id.feedbackTitle).text = randomTitle[0]
            alert.setView(view)
            alert.setPositiveButton("Votar") { _, _ ->
                sharedPrefs.putAny(PREF_KEY_RATING_DONE_BOOL, true)
                navigateToGooglePlayStore()
            }
            alert.setNegativeButton("Mais tarde") { dialog, _ ->
                sharedPrefs.putAny(PREF_KEY_RATING_INTERVAL_INT, 1)
                dialog.dismiss()
            }
            alert.setCancelable(false) // PARA QUE O USUÁRIO VEJA O ALERTA E DECIDA O QUE FAZER
            alert.show()
        } else {
            // DO CONTRARIO SIGO CONTANDO...
            ratingInterval++
            sharedPrefs.putAny(PREF_KEY_RATING_DONE_BOOL, false)
            sharedPrefs.putAny(PREF_KEY_RATING_INTERVAL_INT, ratingInterval)
        }
    }
}

/** NAVEGAR PARA PLAYSTORE DE MANEIRA FACIL */
fun View.navigateToGooglePlayStore() {
    Intent(Intent.ACTION_VIEW).apply {
        data =
            Uri.parse("https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
        setPackage("com.android.vending")
    }.let { context.startActivity(it) }
}

fun SharedPreferences.putAny(name: String, any: Any) {
    when (any) {
        is String -> edit().putString(name, any).apply()
        is Int -> edit().putInt(name, any).apply()
        is Boolean -> edit().putBoolean(name, any).apply()
        // also accepts Float, Long & StringSet
    }
}

fun SharedPreferences.remove(name: String) {
    edit().remove(name).apply()
}