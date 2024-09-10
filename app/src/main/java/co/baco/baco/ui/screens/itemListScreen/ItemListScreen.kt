package co.baco.baco.ui.screens.itemListScreen

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListPrefetchStrategy
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.baco.baco.common.entities.Constants
import co.baco.baco.common.entities.Register
import co.baco.baco.ui.screens.commonComponents.RegisterItem
import co.baco.baco.ui.screens.itemListScreen.components.ItemBar
import co.baco.baco.ui.theme.BacoTheme
import co.baco.baco.ui.theme.Color

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemListScreen() {
    val newRegister = Register(amount = 5000f, type = Constants.RegisterType.EXPENSE)
    val state = rememberLazyListState(
        prefetchStrategy = LazyListPrefetchStrategy(3),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        ItemsHeader()

        Spacer(modifier = Modifier.height(24.dp))

        ItemsBody(state, newRegister)
    }
}

@Composable
fun ItemsHeader() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary
        ),
        shape = RoundedCornerShape(4.dp)

    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp)) {

            ItemBar(process = Constants.RegisterType.DEPOSIT, percentage = .7f)

            Spacer(modifier = Modifier.height(16.dp))

            ItemBar(process = Constants.RegisterType.EXPENSE, percentage = .5f)
        }
    }
}

@Composable
fun ItemsBody(state: LazyListState, newRegister: Register) {
    LazyColumn(
        state = state,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(20, key = { it }) {
            RegisterItem(newRegister)
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ItemListScreenPrevDark() {
    BacoTheme {
        ItemListScreen()
    }
}


@Preview(showBackground = true)
@Composable
private fun ItemListScreenPrevLight() {
    BacoTheme {
        ItemListScreen()
    }
}