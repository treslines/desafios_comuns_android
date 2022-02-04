package com.progdeelite.dca.custom_components

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class SliderUtil {

    companion object {

        /**
         * searches the list or the first value equal or higher the given value (bottom to top)
         * if no value can be found the highest value in the list is returned
         *
         * @param value the value to search for
         * @return the first value equal or higher the given value.
         */
        fun List<BigDecimal>.firstValueEqualOrAbove(value: BigDecimal): BigDecimal {
            var targetValue = BigDecimal(0)
            for (i in indices) {
                targetValue = get(i)
                if (targetValue >= value) {
                    break
                }
            }
            return targetValue
        }

        /**
         * searches the list for the first value equal or lower the given value (top to bottom)
         * if no value can be found the lowest value in the list is returned
         *
         * @param value the value to search for
         * @return the first value equal or lower the given value.
         */
        fun List<BigDecimal>.firstValueEqualOrBelow(value: BigDecimal): BigDecimal {
            var targetValue = BigDecimal(0)
            for (i in size - 1 downTo 0) {
                targetValue = get(i)
                if (get(i) <= value) {
                    break
                }
            }
            return targetValue
        }

        /**
         * formats a value to locale de-CH
         * Example: 1641.45 to 1'641.45
         */
        fun formatValue(value: BigDecimal): String {
            val symbols = DecimalFormatSymbols(Locale(Locale.GERMAN.language, "CH"))
            //we need to define a specific groupingSeparator because it's dependant on the
            //users settings which apostrophe is used (['] or [’])
            symbols.groupingSeparator = '\''
            val formatter = DecimalFormat("#,###,##0.00", symbols)
            return formatter.format(value)
        }

        /**
         * gets max amount of sliders scale
         */
        fun getMaxAmount(scale: List<BigDecimal>): String {
            val formatted = formatValue(scale.last())
            return "max. $formatted"
        }
    }
}