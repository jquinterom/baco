package co.baco.baco.ui.screens.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.baco.baco.ui.theme.BacoTheme

@Composable
fun CommentInput(comment: String, onCommentChange: (String) -> Unit) {
    val minMaxLines = 4

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(8.dp)
            ),
    ) {
        BasicTextField(
            value = comment,
            onValueChange = onCommentChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag(tag = "commentInput"),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            maxLines = minMaxLines,
            minLines = minMaxLines
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CommentInputPrevDark() {
    BacoTheme {
        CommentInput(comment = "") {}
    }
}

@Preview
@Composable
private fun CommentInputPrevLight() {
    BacoTheme {
        CommentInput(comment = "") {}
    }
}