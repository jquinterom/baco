package co.baco.baco.ui.screeens.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import co.baco.baco.common.entities.Constants
import co.baco.baco.common.entities.Register
import co.baco.baco.ui.screens.components.RegisterItem
import org.junit.Rule
import org.junit.Test

class RegisterItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRegisterItem() {
        val register = Register(amount = 3000f, type = Constants.RegisterType.DEPOSIT)

        composeTestRule.setContent {
            RegisterItem(register = register)
        }

        composeTestRule.onNodeWithText("3000").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Favorite").assertIsDisplayed()

        val iconButton = composeTestRule.onNodeWithTag(testTag = "showComment")
        iconButton.assertIsDisplayed()

        iconButton.performClick()
        composeTestRule.onNodeWithTag("commentTest").assertIsDisplayed()

        iconButton.performClick()
        composeTestRule.onNodeWithTag("commentTest").assertDoesNotExist()
    }
}