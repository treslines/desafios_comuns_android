package com.progdeelite.dca.custom_components;

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.progdeelite.dca.R
import com.progdeelite.dca.databinding.CustomSliderViewBinding
import java.math.BigDecimal
import kotlin.properties.Delegates

class CustomSliderView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = R.style.CustomComponents
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

        val binding: CustomSliderViewBinding = CustomSliderViewBinding.inflate(LayoutInflater.from(context), this, true)

        var sliderItemOnValueChangeListener: SliderItemOnValueChangeListener? = null

        var valueList: MutableList<BigDecimal> by Delegates.observable(mutableListOf()) { _, old, new ->
                if (old != new) {
                        binding.run {
                                slider.valueFrom = 0f
                                slider.valueTo = new.size - 1f
                                max.text = SliderUtil.getMaxAmount(new) // VOCE PODERIA FORMATAR ANTES DE ATRIBUIR
                        }
                }
        }

        var sliderValue: BigDecimal by Delegates.observable(BigDecimal(0)) { _, old, new ->
                if (old != new) {
                        binding.run {
                                value.text = SliderUtil.formatValue(new) // VOCE PODERIA FORMATAR ANTES DE ATRIBUIR
                                slider.clearOnChangeListeners()
                                slider.value = valueList.indexOf(new).toFloat()
                                slider.addOnChangeListener { _, sliderValue, _ ->
                                        this@CustomSliderView.sliderValue = valueList[sliderValue.toInt()]
                                        sliderItemOnValueChangeListener?.onValueChange(this@CustomSliderView.sliderValue)
                                }
                        }
                }
        }

        private var valueTextArray: Array<CharSequence> by Delegates.observable(emptyArray()) { _, _, new ->
                val temporaryValueList: MutableList<BigDecimal> = mutableListOf()
                for (i in new) {
                        temporaryValueList.add(BigDecimal(i.toString()))
                }
                valueList = temporaryValueList
        }

        init {
                with(context.theme.obtainStyledAttributes(attrs, R.styleable.SliderItem, defStyleAttr, defStyleRes)) {
                        binding.title.text = getString(R.styleable.SliderItem_title) ?: ""
                        valueTextArray = getTextArray(R.styleable.SliderItem_valueList) ?: emptyArray()
                        sliderValue = BigDecimal((getInt(R.styleable.SliderItem_android_value, 0)))
                        recycle()
                }
        }

        interface SliderItemOnValueChangeListener {
                fun onValueChange(value: BigDecimal)
        }
}
