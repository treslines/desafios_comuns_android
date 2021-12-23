package com.progdeelite.dca.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import androidx.annotation.LayoutRes
import androidx.annotation.StyleableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import com.progdeelite.dca.R
import com.progdeelite.dca.databinding.CustomMenuItemBinding
import kotlin.properties.Delegates

// https://stackoverflow.com/questions/3014089/maintain-save-restore-scroll-position-when-returning-to-a-listview
//xmlns:custom="http://schemas.android.com/apk/res-auto"
//meuItem.mainMenuItemButton.setOnClickListener { toast("Hello") }

// <com.progdeelite.dca.custom_view.MainMenuItem
// android:id="@+id/meu_item"
// android:layout_width="match_parent"
// android:layout_height="wrap_content"
// custom:mainMenuItemLabelText="Meu Texto"
// custom:mainMenuItemButtonBackgroundTint="@color/design_default_color_primary"
// custom:mainMenuItemButtonTextColor="@color/white"
// custom:mainMenuItemButtonText="Click me"
// custom:mainMenuItemButtonAllCaps="true"/>

class CustomMainMenuItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
    @LayoutRes layoutRes: Int = R.layout.custom_menu_item,
    @StyleableRes styleableRes: IntArray = R.styleable.MainMenuItem
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val emptyString = ""
    private lateinit var binding: CustomMenuItemBinding

    var mainMenuItemButtonAllCaps: Boolean by Delegates.observable(false) { _, _, new ->
        binding.mainMenuItemButton.isAllCaps = new
    }

    var mainMenuItemLabelText: String by Delegates.observable(initialValue = emptyString) { _, _, new ->
        binding.mainMenuItemTitle.text = new
    }

    var mainMenuItemButtonText: String by Delegates.observable(initialValue = emptyString) { _, _, new ->
        binding.mainMenuItemButton.text = new
    }

    var mainMenuItemButtonBackgroundTint: Int by Delegates.observable(initialValue = -1) { _, _, new ->
        binding.mainMenuItemButton.setBackgroundColor(new)
    }

    var mainMenuItemButtonTextColor: Int by Delegates.observable(initialValue = -1) { _, _, new ->
        binding.mainMenuItemButton.setTextColor(new)
    }

    val mainMenuItemButton: MaterialButton
        get() = binding.mainMenuItemButton

    init {
        val view: View = inflate(context, layoutRes, this)
        binding = CustomMenuItemBinding.bind(view)
        context.theme.obtainStyledAttributes(
            attrs,
            styleableRes,
            defStyleAttr,
            defStyleRes
        ).apply {
            try {
                val defaultTintColor: Int =
                    resources.getColor(R.color.design_default_color_primary, null)
                val defaultTextColor = resources.getColor(R.color.white, null)
                mainMenuItemLabelText =
                    getString(R.styleable.MainMenuItem_mainMenuItemLabelText) ?: emptyString
                mainMenuItemButtonAllCaps =
                    getBoolean(R.styleable.MainMenuItem_mainMenuItemButtonAllCaps, false)
                mainMenuItemButtonText =
                    getString(R.styleable.MainMenuItem_mainMenuItemButtonText) ?: emptyString
                mainMenuItemButtonTextColor =
                    getColor(R.styleable.MainMenuItem_mainMenuItemButtonTextColor, defaultTextColor)
                mainMenuItemButtonBackgroundTint = getColor(
                    R.styleable.MainMenuItem_mainMenuItemButtonBackgroundTint,
                    defaultTintColor
                )
            } finally {
                recycle()
            }
        }
    }
}