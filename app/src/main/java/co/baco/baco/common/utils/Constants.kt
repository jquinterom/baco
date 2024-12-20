package co.baco.baco.common.utils

import java.text.DecimalFormat

class Constants {
    enum class RegisterType {
        NONE, DEPOSIT, WITHDRAWAL
    }
}

//val REGEX_NUMBER_AND_DOT = Regex("^[0-9]+\\.?[0-9]{0,2}\$")
val REGEX_NUMBER_AND_DOT = Regex("^\\s*[0-9]+\\.?[0-9]{0,2}\\s*$")

val DECIMAL_FORMAT = DecimalFormat("#.00")