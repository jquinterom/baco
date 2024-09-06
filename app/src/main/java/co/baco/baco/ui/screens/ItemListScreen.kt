package co.baco.baco.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListPrefetchStrategy
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import co.baco.baco.common.entities.Register
import co.baco.baco.common.entities.RegisterType
import co.baco.baco.ui.screens.components.RegisterItem
import co.baco.baco.ui.theme.BacoTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemListScreen() {
    val newRegister = Register(amount = 4000f, type = RegisterType.EXPENSE)
    val state = rememberLazyListState(
        prefetchStrategy = LazyListPrefetchStrategy(3),
    )

    LazyColumn(
        state = state,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(20, key = { it }) {
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