package co.baco.baco.ui.screens.homeScreen

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import co.baco.baco.common.entities.Constants
import co.baco.baco.common.entities.RegisterItem
import co.baco.baco.ui.screens.components.DepositOrExpense
import co.baco.baco.ui.screens.components.Input
import co.baco.baco.ui.screens.components.RegisterList
import co.baco.baco.ui.screens.components.SubmitButton
import co.baco.baco.ui.screens.homeScreen.viewModel.HomeViewModel
import co.baco.baco.ui.theme.BacoTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToItemListScreen: () -> Unit,
) {
    val homeViewModel: HomeViewModel = hiltViewModel()

    var register by rememberSaveable {
        mutableStateOf<RegisterItem?>(null)
    }

    val submitEnabled =
        register?.let { it.amount > 0f && it.type != Constants.RegisterType.NONE } ?: false



    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Input(
            onValueChange = { newAmount ->
                register = updateAmount(newAmount, register)
            }
        )

        DepositOrExpense(
            onValueChange = { newType ->
                register = updateType(newType, register)
            },
            onCommentChange = { newComment ->
                register = updateComment(newComment, register)
            },
            defaultValue = register.let { it?.type ?: Constants.RegisterType.NONE }
        )

        SubmitButton(isEnabled = submitEnabled, onClick = {
            register?.let { item ->
                homeViewModel.run { insertRegister(registerItem = item) }
            }
        })

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


fun updateComment(newComment: String, register: RegisterItem?): RegisterItem {
    val comment = newComment.ifEmpty { null }

    return register?.copy(comment = comment)
        ?: RegisterItem(
            amount = 0f,
            type = Constants.RegisterType.NONE,
            comment = comment
        )
}

fun updateType(newType: Constants.RegisterType, register: RegisterItem?): RegisterItem {
    return register?.copy(type = newType)
        ?: RegisterItem(amount = 0f, type = newType)
}

fun updateAmount(newAmount: Float, register: RegisterItem?): RegisterItem {
    return register?.copy(amount = newAmount)
        ?: RegisterItem(
            amount = newAmount,
            type = Constants.RegisterType.NONE
        )
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