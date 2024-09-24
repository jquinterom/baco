package co.baco.baco.ui.screens.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import co.baco.baco.R
import co.baco.baco.ui.theme.BacoTheme

@Composable
fun Input(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    amount: String
) {
    OutlinedTextField(
        value = amount,
        onValueChange = { newAmount ->
            if (newAmount.all { it.isDigit() }) {
                onValueChange(newAmount)
            }
        },
        modifier = modifier.fillMaxWidth(),
        trailingIcon = {
            if (amount.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable {
                        onValueChange("")
                    },
                    painter = painterResource(id = R.drawable.cancel),
                    contentDescription = "Cancel"
                )
            }
        },
        label = { Text("Monto") },
        placeholder = { Text("$2.000") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InputPrevDark() {
    BacoTheme {
        Input(onValueChange = {}, amount = "2000")
    }
}


@Preview
@Composable
fun InputPrevLight() {
    BacoTheme {
        Input(onValueChange = {}, amount = "")
    }
}