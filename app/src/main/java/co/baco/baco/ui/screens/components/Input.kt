package co.baco.baco.ui.screens.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import co.baco.baco.R
import co.baco.baco.ui.theme.BacoTheme

@Composable
fun Input(modifier: Modifier = Modifier, submitEnabled: (Boolean) -> Unit) {
    var amount by rememberSaveable {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = amount,
        onValueChange = { newAmount ->
            if (newAmount.all { it.isDigit() }) {
                amount = newAmount
                submitEnabled(newAmount.isNotEmpty())
            }
        },
        modifier = modifier.fillMaxWidth(),
        trailingIcon = {
            if (amount.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable {
                        amount = ""
                        submitEnabled(false)
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
        Input(submitEnabled = {})
    }
}


@Preview
@Composable
fun InputPrevLight() {
    BacoTheme {
        Input(submitEnabled = {})
    }
}