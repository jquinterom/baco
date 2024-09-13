package co.baco.baco.ui.screens.components

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.baco.baco.common.entities.Constants
import co.baco.baco.common.entities.Register
import co.baco.baco.ui.theme.BacoTheme

@SuppressLint("DefaultLocale")
@Composable
fun RegisterItem(register: Register) {
    var showComment by rememberSaveable {
        mutableStateOf(false)
    }
    val icon: ImageVector =
        if (showComment) Icons.Filled.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.onSecondary,
                shape = RoundedCornerShape(4.dp)
            ),
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
        ) {
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val textColor = when (register.type) {
                    Constants.RegisterType.DEPOSIT -> co.baco.baco.ui.theme.Color.Danube
                    Constants.RegisterType.EXPENSE -> co.baco.baco.ui.theme.Color.Amethist
                }
                Text(
                    text = String.format("%.0f", register.amount),
                    color = textColor
                )

                if (register.comment.isNullOrEmpty()) {
                    IconButton(
                        onClick = { showComment = !showComment },
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(20.dp)
                            .testTag(
                                tag = "showComment",
                            ),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = textColor
                        ),


                        ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Favorite"
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = showComment,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    modifier = Modifier.testTag(
                        tag = "commentTest"
                    ),
                    text = "Comment",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W300,
                        fontStyle = FontStyle.Italic
                    )
                )
            }
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RegisterItemPrevDark() {
    BacoTheme {
        RegisterItem(Register(amount = 3000f, type = Constants.RegisterType.DEPOSIT))
    }
}


@Preview
@Composable
private fun RegisterItemPrevLight() {
    BacoTheme {
        RegisterItem(Register(amount = 3000f, type = Constants.RegisterType.DEPOSIT))
    }
}