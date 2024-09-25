package co.baco.baco.ui.screeens.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import co.baco.baco.ui.screens.components.CommentInput
import org.junit.Rule
import org.junit.Test

class CommentInputTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCommentInput() {
        composeTestRule.setContent {
            CommentInput(register?.comment, onCommentChange)
        }

        val commentInput = composeTestRule.onNodeWithTag("commentInput")
        commentInput.assertIsDisplayed()
        commentInput.performTextInput("This is a comment")
        commentInput.assertTextEquals("This is a comment")
    }
}