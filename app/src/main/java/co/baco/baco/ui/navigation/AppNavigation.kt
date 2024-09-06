package co.baco.baco.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.baco.baco.ui.screens.HomeScreen
import co.baco.baco.ui.screens.ItemListScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home,
        modifier = modifier
    ) {

        composable<Home> {
            HomeScreen(onNavigateToItemListScreen = { navController.navigate(route = ItemListScreen) })
        }

        composable<ItemListScreen> {
            ItemListScreen()
        }
    }

}