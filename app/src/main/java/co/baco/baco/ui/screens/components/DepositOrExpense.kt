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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.baco.baco.ui.theme.BacoTheme

enum class Process {
    NONE,
    DEPOSIT,
    EXPENSE
}

@Composable
fun DepositOrExpense() {
    var itemSelected by rememberSaveable {
        mutableStateOf(Process.NONE)
    }

    var isChecked by rememberSaveable { mutableStateOf(false) }


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
                selected = itemSelected == Process.DEPOSIT,
                onClick = { itemSelected = Process.DEPOSIT },
            )

            ItemRadioButton(
                text = "Egreso",
                selected = itemSelected == Process.EXPENSE,
                onClick = { itemSelected = Process.EXPENSE })
        }

        Comment(isChecked) { isChecked = it }

        AnimatedVisibility(
            visible = isChecked,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            CommentInput()
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
        DepositOrExpense()
    }

}