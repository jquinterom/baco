package co.baco.baco.ui.screeens.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import co.baco.baco.common.utils.Constants
import co.baco.baco.common.entities.RegisterItem
import co.baco.baco.ui.screens.components.RegisterItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterItemItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRegisterItem() {
        val registerItem = RegisterItem(
            amount = "3",
            type = Constants.RegisterType.DEPOSIT,
            comment = "This is a comment"
        )

        composeTestRule.setContent {
            RegisterItem(registerItem = registerItem)
        }

        composeTestRule.onNodeWithText("3").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("ShowComment").assertIsDisplayed()

        val iconButton = composeTestRule.onNodeWithTag(testTag = "showComment")
        iconButton.assertIsDisplayed()

        iconButton.performClick()
        composeTestRule.onNodeWithTag("commentTest").assertIsDisplayed()

        iconButton.performClick()
        composeTestRule.onNodeWithTag("commentTest").assertDoesNotExist()
    }
}