package co.baco.baco.ui.screens.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
fun Input(modifier: Modifier = Modifier) {
    var amount by rememberSaveable {
        mutableStateOf("")
    }

    TextField(
        value = amount,
        onValueChange = { newAmount ->
            if (newAmount.all { it.isDigit() }) {
                amount = newAmount
            }
        },
        modifier = modifier.fillMaxWidth(),
        label = { Text("Monto") },
        trailingIcon = { Icon(painter = painterResource(id = R.drawable.cancel), contentDescription = null) },
        placeholder = { Text("$2.000") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview(showBackground = true)
@Composable
fun InputPrev() {
    BacoTheme {
        Input()
    }
}