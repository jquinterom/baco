package co.baco.baco.ui.screens.homeScreen

import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.baco.baco.common.components.CircularLoading
import co.baco.baco.common.entities.RegisterItem
import co.baco.baco.common.utils.Constants
import co.baco.baco.ui.screens.components.DepositOrExpense
import co.baco.baco.ui.screens.components.Input
import co.baco.baco.ui.screens.components.RegisterList
import co.baco.baco.ui.screens.components.SubmitButton
import co.baco.baco.ui.screens.homeScreen.viewModel.HomeViewModel
import co.baco.baco.ui.theme.BacoTheme

@SuppressLint("DefaultLocale")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToItemListScreen: () -> Unit,
) {
    val homeViewModel: HomeViewModel = hiltViewModel()

    val register: RegisterItem? = homeViewModel.registerItem.value

    val submitEnabled =
        register?.let { it.amount.toDouble() > 0 && it.type != Constants.RegisterType.NONE } ?: false

    var showComment by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Input(
            onValueChange = { newAmount ->
                homeViewModel.updateAmount(newAmount)
            },
            amount = register?.amount ?: ""
        )

        DepositOrExpense(
            onValueChange = { newType ->
                homeViewModel.updateType(newType)
            },
            onCommentChange = { newComment ->
                homeViewModel.updateComment(newComment)
            },
            register = register,
            showComment = showComment,
            setShowComment = { showComment = it }
        )

        SubmitButton(
            isEnabled = submitEnabled,
            onClick = {
                submitRegister(register, homeViewModel)
                showComment = !showComment
            }
        )

        SeeAll(onNavigateToItemListScreen)

        RegisterList()
    }

    if (homeViewModel.isLoading.value == true) {
        CircularLoading()
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

fun submitRegister(register: RegisterItem?, homeViewModel: HomeViewModel) {
    register?.let { item ->
        val newItem = item.copy(
            createdAt = java.util.Date(),
            updatedAt = java.util.Date()
        )
        homeViewModel.insertRegister(registerItem = newItem)
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