package co.baco.baco.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.baco.baco.common.entities.Register
import co.baco.baco.common.entities.RegisterType
import co.baco.baco.ui.screens.components.RegisterItem
import co.baco.baco.ui.theme.BacoTheme

@Composable
fun ItemListScreen() {
    val newRegister = Register(amount = 4000f, type = RegisterType.EXPENSE)
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(20, key = {it}) {
            RegisterItem(newRegister)
        }
    }
}

@Preview
@Composable
private fun ItemListScreenPrev() {
    BacoTheme {
        ItemListScreen()
    }
}