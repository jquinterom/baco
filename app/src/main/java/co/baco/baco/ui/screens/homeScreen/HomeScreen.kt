package co.baco.baco.ui.screens.homeScreen

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
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
import co.baco.baco.common.entities.Constants
import co.baco.baco.common.entities.RegisterItem
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

    var register: RegisterItem? by rememberSaveable {
        mutableStateOf(null)
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
                val newValue = if (newAmount.isNotEmpty()) newAmount.toFloat() else 0f

                register = updateAmount(newValue, register)
            },
            amount = if (register?.amount == 0f) "" else String.format("%.0f", register?.amount)
        )

        DepositOrExpense(
            onValueChange = { newType ->
                register = updateType(newType, register)
            },
            onCommentChange = { newComment ->
                register = updateComment(newComment, register)
            },
            defaultValue =  register?.type ?: Constants.RegisterType.NONE
        )

        SubmitButton(
            isEnabled = submitEnabled,
            onClick = {
                submitRegister(register, homeViewModel)
                register = register?.copy(
                    amount = 0f,
                    type = Constants.RegisterType.NONE,
                    comment = null
                )
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