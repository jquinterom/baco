package co.baco.baco.ui.screens.commonComponents

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.baco.baco.common.entities.Constants
import co.baco.baco.common.entities.Register
import co.baco.baco.ui.theme.BacoTheme

@Composable
fun RegisterList() {
    val newRegister = Register(amount = 4000f, type = Constants.RegisterType.EXPENSE)
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(20, key = {it}) {
            RegisterItem(newRegister)
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RegisterListPrevDark() {
    BacoTheme {
        RegisterList()
    }
}

@Preview
@Composable
private fun RegisterListPrevLight() {
    BacoTheme {
        RegisterList()
    }
}