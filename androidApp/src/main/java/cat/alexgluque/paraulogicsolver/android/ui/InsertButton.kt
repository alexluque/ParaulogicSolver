package cat.alexgluque.paraulogicsolver.android.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp

@Composable
fun InsertButton(
    onClick: () -> Unit,
    enabled: Boolean
) = OutlinedButton(
    onClick = { onClick() },
    border = BorderStroke(1.dp, LightGray),
    shape = RoundedCornerShape(50),
    colors = ButtonDefaults.outlinedButtonColors(
        contentColor = if (isSystemInDarkTheme()) White else Black
    ),
    contentPadding = PaddingValues(15.dp),
    modifier = Modifier.padding(bottom = 25.dp),
    enabled = enabled
) {
    Text(text = "Introdueix")
}