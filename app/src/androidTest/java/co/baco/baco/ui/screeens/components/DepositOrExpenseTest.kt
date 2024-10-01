package co.baco.baco.ui.screeens.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import co.baco.baco.common.entities.RegisterItem
import co.baco.baco.common.utils.Constants
import co.baco.baco.ui.screens.components.DepositOrExpense
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DepositOrExpenseTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testDepositOrExpense() {
        var showComment by mutableStateOf(false)

        var register: RegisterItem by mutableStateOf(
            RegisterItem(
                amount = "3000",
                comment = "",
                type = Constants.RegisterType.DEPOSIT
            )
        )

        composeTestRule.setContent {
            DepositOrExpense(
                onValueChange = {register = register.copy(type = it)},
                onCommentChange = {},
                register = register,
                showComment = showComment,
                setShowComment = {showComment = it}
            )
        }

        // Initial state
        val deposit = composeTestRule.onNodeWithTag("Ingreso")
        deposit.assertIsDisplayed()

        val expense = composeTestRule.onNodeWithTag("Egreso")
        expense.assertIsDisplayed()

        val checkboxComment = composeTestRule.onNodeWithTag("checkboxComment")
        checkboxComment.assertIsDisplayed()

        // Clicks
        deposit.performClick()
        deposit.assertIsSelected()
        expense.assertIsNotSelected()

        expense.performClick()
        expense.assertIsSelected()
        deposit.assertIsNotSelected()

        // Comment Checkbox
        val commentInput = composeTestRule.onNodeWithTag("commentInput")
        commentInput.assertIsNotDisplayed()

        checkboxComment.performClick()
        commentInput.assertIsDisplayed()
    }
}