package co.baco.baco.ui.screens.homeScreen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.baco.baco.ui.screens.commonComponents.DepositOrExpense
import co.baco.baco.ui.screens.commonComponents.Input
import co.baco.baco.ui.screens.commonComponents.RegisterList
import co.baco.baco.ui.screens.commonComponents.SubmitButton
import co.baco.baco.ui.theme.BacoTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onNavigateToItemListScreen: () -> Unit) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Input()
        DepositOrExpense()
        SubmitButton()
        SeeAll(onNavigateToItemListScreen)
        RegisterList()
    }
}

@Composable
fun SeeAll(onNavigateToItemListScreen: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        TextButton(onClick = { onNavigateToItemListScreen() }) {
            Text(text = "Ver todo", color = MaterialTheme.colorScheme.tertiary)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPrevDark() {
    BacoTheme {
        HomeScreen(modifier = Modifier) {}
    }
}



@Preview(showBackground = true)
@Composable
private fun HomeScreenPrevLight() {
    BacoTheme {
        HomeScreen(modifier = Modifier) {}
    }
}