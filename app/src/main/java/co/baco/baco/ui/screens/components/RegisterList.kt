package co.baco.baco.ui.screens.components

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListPrefetchStrategy
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.baco.baco.common.entities.RegisterItem
import co.baco.baco.ui.screens.viewModel.SharedViewModel
import co.baco.baco.ui.theme.BacoTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegisterList() {
    val sharedViewModel: SharedViewModel = hiltViewModel()

    var registerItemList by rememberSaveable {
        mutableStateOf(emptyList<RegisterItem>())
    }

    sharedViewModel.items.observeForever {
        registerItemList = it
    }
    val state = rememberLazyListState(
        prefetchStrategy = LazyListPrefetchStrategy(3),
    )

    LazyColumn(
        modifier = Modifier.testTag("registerList"),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = state
    ) {
        items(registerItemList, key = {it.id}) { item ->
            RegisterItem(item)
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