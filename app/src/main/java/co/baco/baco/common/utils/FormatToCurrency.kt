package co.baco.baco.common.utils

import java.text.NumberFormat
import java.util.Locale

fun formatToCurrency(value: Double): String {
    val formattedNumber =
        NumberFormat.getCurrencyInstance(Locale("es", "CO")).format(value)
    return formattedNumber
}