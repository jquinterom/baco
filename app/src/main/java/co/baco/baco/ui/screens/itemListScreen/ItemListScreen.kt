package co.baco.baco.ui.screens.itemListScreen

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListPrefetchStrategy
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.baco.baco.common.entities.RegisterItem
import co.baco.baco.common.utils.Constants
import co.baco.baco.common.utils.formatToCurrency
import co.baco.baco.ui.screens.components.RegisterItem
import co.baco.baco.ui.screens.itemListScreen.components.ItemBar
import co.baco.baco.ui.screens.viewModel.SharedViewModel
import co.baco.baco.ui.theme.BacoTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemListScreen(sharedViewModel: SharedViewModel = hiltViewModel()) {

    val state = rememberLazyListState(
        prefetchStrategy = LazyListPrefetchStrategy(3),
    )

    val registerItemList: List<RegisterItem> by sharedViewModel.items.observeAsState(emptyList())

    val deposits: Double  by sharedViewModel.deposits.observeAsState(0.0)
    val withDraws : Double by sharedViewModel.withDraws.observeAsState(0.0)

    val depositsPercentage: Float by sharedViewModel.depositsPercentage.observeAsState(.0f)
    val withDrawsPercentage: Float by sharedViewModel.withDrawsPercentage.observeAsState(.0f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        ItemsHeader(
            depositPercentage = depositsPercentage,
            withDrawPercentage = withDrawsPercentage,
            depositValue = formatToCurrency(deposits),
            withDrawValue = formatToCurrency(withDraws)
        )

        Spacer(modifier = Modifier.height(24.dp))

        ItemsBody(state = state, registerItemList = registerItemList)
    }
}

@Composable
fun ItemsHeader(
    depositPercentage: Float,
    withDrawPercentage: Float,
    depositValue: String,
    withDrawValue: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 4.dp)
        ) {

            ItemBar(
                process = Constants.RegisterType.DEPOSIT,
                percentage = depositPercentage,
                value = depositValue
            )

            Spacer(modifier = Modifier.height(16.dp))

            ItemBar(
                process = Constants.RegisterType.WITHDRAWAL,
                percentage = withDrawPercentage,
                value = withDrawValue
            )
        }
    }
}

@Composable
fun ItemsBody(state: LazyListState, registerItemList: List<RegisterItem>) {
    LazyColumn(
        state = state,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(registerItemList, key = { it.id }) { registerItem ->
            RegisterItem(registerItem = registerItem)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ItemListScreenPrevDark() {
    BacoTheme {
        ItemListScreen(sharedViewModel = hiltViewModel())
    }
}


@Preview(showBackground = true)
@Composable
private fun ItemListScreenPrevLight() {
    BacoTheme {
        ItemListScreen(sharedViewModel = hiltViewModel())
    }
}