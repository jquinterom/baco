package co.baco.baco.ui.screeens.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import co.baco.baco.common.entities.RegisterItem
import co.baco.baco.common.utils.Constants
import co.baco.baco.ui.screens.components.CommentInput
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CommentInputTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCommentInput() {
        var register: RegisterItem by mutableStateOf(
            RegisterItem(
                amount = "3000",
                comment = "",
                type = Constants.RegisterType.DEPOSIT
            )
        )

        composeTestRule.setContent {
            CommentInput(
                register.comment ?: "",
                onCommentChange = { register = register.copy(comment = it) })
        }

        val commentInput = composeTestRule.onNodeWithTag("commentInput")
        commentInput.assertIsDisplayed()
        commentInput.performTextInput("This is a comment")
        commentInput.assertTextEquals("This is a comment")

    }
}