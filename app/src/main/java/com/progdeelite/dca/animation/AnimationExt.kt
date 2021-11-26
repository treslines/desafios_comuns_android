package com.progdeelite.dca.animation

import android.view.View

fun View.pulseAnimation(fromScale: Float = 1f, toScale: Float = .9f, duration: Long = 1500L): View =
    apply {
        animate()
            .scaleX(toScale)
            .scaleY(toScale)
            .setDuration(duration)
            .withEndAction { pulseAnimation(toScale, fromScale, duration) }
            .start()
    }

fun View.setExplodeAnimationClickListener(
    toScale: Float = 5f,
    startAction: () -> Unit = {},
    clickAction: () -> Unit
): View = apply {
    setOnClickListener {
        startAction()
        explodeAnimation(toScale = toScale, endAction = clickAction)
    }
}

fun View.explodeAnimation(toScale: Float = 5f, duration: Long = 150L, endAction: () -> Unit): View =
    apply {
        animate()
            .scaleX(toScale)
            .scaleY(toScale)
            .setDuration(duration)
            .withEndAction { endAction() }
            .start()
    }

fun View.hideAnimation(toAlpha: Float = 0f, duration: Long = 50L): View = apply {
    animate()
        .alpha(toAlpha)
        .setDuration(duration)
        .start()
}

fun View.animateExplosionInverted(
    labelView: View,
    startAction: () -> Unit = {},
    clickAction: () -> Unit = {},
    explode: Float = 5f,
    startAlpha: Float = 0f,
    endAlpha: Float = 1f,
    scaleDown: Float = 0.3f,
    scaleUp: Float = 1f,
    scaleDownDuration: Long = 400L,
    scaleUpDuration: Long = 550L,
    textDuration: Long = 550L
): View = apply {
    setOnClickListener(null)
    scaleX = explode
    scaleY = explode
    labelView.alpha = startAlpha
    animate()
        .scaleX(scaleDown)
        .scaleY(scaleDown)
        .setDuration(scaleUpDuration)
        .withEndAction {
            kotlin.run {
                animate()
                    .scaleX(scaleUp)
                    .scaleY(scaleUp)
                    .setDuration(scaleDownDuration).withEndAction {
                        labelView.animate().alpha(endAlpha).setDuration(textDuration)
                            .withEndAction {
                                pulseAnimation()
                                    .setExplodeAnimationClickListener(
                                        startAction = startAction,
                                        clickAction = clickAction,
                                    )
                            }.start()
                    }.start()
            }
        }.start()
}
