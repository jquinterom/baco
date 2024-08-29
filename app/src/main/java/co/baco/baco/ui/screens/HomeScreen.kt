package co.baco.baco.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.baco.baco.ui.screens.components.DepositOrExpense
import co.baco.baco.ui.screens.components.Input
import co.baco.baco.ui.theme.BacoTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Input()
        DepositOrExpense()
    }
}

@Preview
@Composable
private fun HomeScreenPrev() {
    BacoTheme {
        HomeScreen()
    }
}