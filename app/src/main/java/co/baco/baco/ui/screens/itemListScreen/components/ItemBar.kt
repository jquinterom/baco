package co.baco.baco.ui.screens.itemListScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import co.baco.baco.common.utils.Constants

@Composable
fun ItemBar(
    process: Constants.RegisterType,
    percentage: Float,
    value: String
) {
    val processText: String = when (process) {
        Constants.RegisterType.DEPOSIT -> "Ingresos"
        Constants.RegisterType.WITHDRAWAL -> "Egresos"
        else -> ""
    }

    val valueText: String = when (process) {
        Constants.RegisterType.DEPOSIT -> value
        Constants.RegisterType.WITHDRAWAL -> value
        else -> ""
    }


    val color: Color = when (process) {
        Constants.RegisterType.DEPOSIT -> co.baco.baco.ui.theme.Color.Danube
        Constants.RegisterType.WITHDRAWAL -> co.baco.baco.ui.theme.Color.Amethist
        else -> MaterialTheme.colorScheme.tertiary
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = processText, color = MaterialTheme.colorScheme.tertiary, fontSize = 14.sp)

            Text(
                text = valueText,
                fontSize = 14.sp,
                color = color
            )

        }

        StaticProgressBar(percentage, color = color)
    }
}