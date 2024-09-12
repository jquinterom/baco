package co.baco.baco.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import co.baco.baco.ui.screens.homeScreen.HomeScreen
import co.baco.baco.ui.screens.itemListScreen.ItemListScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = BacoGraph,
        modifier = modifier
    ) {
        homeGraph(navController = navController)
    }

}

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation<BacoGraph>(startDestination = Home) {
        composable<Home> {
            HomeScreen(onNavigateToItemListScreen = { navController.navigate(route = ItemListScreen) })
        }

        composable<ItemListScreen> {
            ItemListScreen()
        }
    }
}