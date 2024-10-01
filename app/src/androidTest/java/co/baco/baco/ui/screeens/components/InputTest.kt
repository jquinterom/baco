package co.baco.baco.ui.screeens.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import co.baco.baco.ui.screens.components.Input
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InputTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testInput() {
        var submitEnabled = false
        var amount: String by mutableStateOf("")

        composeTestRule.setContent {
            Input(
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    submitEnabled = it.isNotEmpty()
                    amount = it
                },
                amount = amount
            )
        }

        // Check initial state
        val inputField = composeTestRule.onNodeWithText("Monto")
        inputField.assertIsDisplayed()
        inputField.performClick()
        composeTestRule.onNodeWithText("$2.000").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Cancel").assertIsNotDisplayed()
        assert(!submitEnabled)

        // Enter valid input
        composeTestRule.onNodeWithText("Monto").performTextInput("123")
        composeTestRule.onNodeWithText("123").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Cancel").assertIsDisplayed()
        assert(submitEnabled) // Submit should be enabled

        // Click the trailing icon to clear input
        composeTestRule.onNodeWithContentDescription("Cancel").performClick()
        composeTestRule.onNodeWithText("123").assertDoesNotExist()
        composeTestRule.onNodeWithContentDescription("Cancel").assertIsNotDisplayed()
        assert(!submitEnabled) // Submit should be disabled again
    }
}