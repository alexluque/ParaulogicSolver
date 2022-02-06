package cat.alexgluque.paraulogicsolver.android.ui

import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import cat.alexgluque.paraulogicsolver.android.ui.theme.hexagonBlue

@Composable
fun HexagonField(
    value: String,
    modifier: Modifier,
    color: Color = hexagonBlue,
    onValueChange: (String) -> Unit
) = TextField(
    value = value,
    singleLine = true,
    modifier = modifier,
    colors = TextFieldDefaults.textFieldColors(
        textColor = Black,
        backgroundColor = color,
        focusedIndicatorColor = Transparent,
        unfocusedIndicatorColor = Transparent,
        disabledIndicatorColor = Transparent
    ),
    textStyle = TextStyle(
        textAlign = Center,
        color = if (color == hexagonBlue) Black else White
    ),
    shape = hexagonShape(),
    onValueChange = { onValueChange(it) }
)