package co.baco.baco.ui.screens.components

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.baco.baco.common.utils.Constants
import co.baco.baco.common.entities.RegisterItem
import co.baco.baco.ui.theme.BacoTheme

@Composable
fun DepositOrExpense(
    onValueChange: (Constants.RegisterType) -> Unit = {},
    onCommentChange: (String) -> Unit = {},
    register: RegisterItem? = null,
    showComment: Boolean,
    setShowComment: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            Arrangement.SpaceAround,

            ) {
            ItemRadioButton(
                text = "Ingreso",
                selected = register?.type == Constants.RegisterType.DEPOSIT,
                onClick = {
                    onValueChange(Constants.RegisterType.DEPOSIT)
                },
            )

            ItemRadioButton(
                text = "Egreso",
                selected = register?.type == Constants.RegisterType.WITHDRAWAL,
                onClick = {
                    onValueChange(Constants.RegisterType.WITHDRAWAL)
                })
        }

        Comment(showComment, setShowComment)

        AnimatedVisibility(
            visible = showComment,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            CommentInput(comment = register?.comment ?: "", onCommentChange)
        }
    }
}

@Composable
fun ItemRadioButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier,
        Arrangement.Start,
        Alignment.CenterVertically
    )
    {
        RadioButton(
            modifier = Modifier.testTag(tag = text),
            selected = selected,
            onClick = { onClick() },
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.secondary,
                unselectedColor = MaterialTheme.colorScheme.tertiary
            )
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, color = MaterialTheme.colorScheme.tertiary)
    }
}

@Composable
fun Comment(isChecked: Boolean, setChecked: (e: Boolean) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            modifier = Modifier.testTag("checkboxComment"),
            checked = isChecked,
            onCheckedChange = { setChecked(!isChecked) },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.secondary,
                uncheckedColor = MaterialTheme.colorScheme.tertiary,
                checkmarkColor = MaterialTheme.colorScheme.tertiary
            )
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = "Comentario",
            modifier = Modifier.clickable { setChecked(!isChecked) },
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DepositOrExpensePrev() {
    BacoTheme {
        DepositOrExpense(register = null, showComment = false, setShowComment = {})
    }

}