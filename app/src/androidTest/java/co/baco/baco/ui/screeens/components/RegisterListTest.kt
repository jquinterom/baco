package co.baco.baco.ui.screeens.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import co.baco.baco.ui.screens.components.RegisterList
import org.junit.Rule
import org.junit.Test

class RegisterListTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun testRegisterList() {
        composeTestRule.setContent {
            RegisterList()
        }

        composeTestRule.onNodeWithTag("registerList").assertIsDisplayed()
    }

}