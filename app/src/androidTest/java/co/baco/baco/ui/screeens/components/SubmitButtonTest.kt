package co.baco.baco.ui.screeens.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import co.baco.baco.ui.screens.components.SubmitButton
import org.junit.Rule
import org.junit.Test

class SubmitButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSubmitButtonIsEnabled(){
        composeTestRule.setContent {
            SubmitButton(isEnabled = true)
        }
        composeTestRule.onNodeWithText("Guardar").assertIsDisplayed()

        val button = composeTestRule.onNodeWithContentDescription("saveButton")
        button.assertIsDisplayed()
        button.assertIsEnabled()
    }

    @Test
    fun testSubmitButtonIsNotEnabled(){
        composeTestRule.setContent {
            SubmitButton()
        }

        val button = composeTestRule.onNodeWithContentDescription("saveButton")
        button.assertIsDisplayed()
        button.assertIsNotEnabled()
    }
}