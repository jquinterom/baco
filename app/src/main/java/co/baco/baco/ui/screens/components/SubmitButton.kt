package co.baco.baco.ui.screens.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.baco.baco.ui.theme.BacoTheme

@Composable
fun SubmitButton(enabled: Boolean = false) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary),
        enabled = enabled
    ) {
        Text(text = "Guardar", color = MaterialTheme.colorScheme.tertiary)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SubmitButtonPrevDark() {
    BacoTheme {
        SubmitButton()
    }
}

@Preview
@Composable
private fun SubmitButtonPrevLight() {
    BacoTheme {
        SubmitButton()
    }
}