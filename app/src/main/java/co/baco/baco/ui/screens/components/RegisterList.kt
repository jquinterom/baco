package co.baco.baco.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import co.baco.baco.common.entities.Register
import co.baco.baco.common.entities.RegisterType

@Composable
fun RegisterList() {
    val newRegister = Register(amount = 4000f, type = RegisterType.EXPENSE)
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(20, key = {it}) {
            RegisterItem(newRegister)
        }
    }
}