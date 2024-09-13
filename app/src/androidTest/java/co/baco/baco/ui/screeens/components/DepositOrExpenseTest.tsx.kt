package co.baco.baco.ui.screeens.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import co.baco.baco.ui.screens.components.DepositOrExpense
import org.junit.Rule
import org.junit.Test

class DepositOrExpenseTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testDepositOrExpense() {
        composeTestRule.setContent {
            DepositOrExpense()
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