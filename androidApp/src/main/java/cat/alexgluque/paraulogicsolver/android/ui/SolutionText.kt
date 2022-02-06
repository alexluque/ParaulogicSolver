package cat.alexgluque.paraulogicsolver.android.ui

import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun SolutionText(
    wordAmount: Int,
    words: String
) = Row(
    horizontalArrangement = Center,
    modifier = Modifier.padding(
        start = 25.dp,
        end = 25.dp
    )
) {
    Text(
        buildAnnotatedString {
            append(
                "Solució del joc amb $wordAmount paraules possibles: "
            )
            withStyle(
                style = SpanStyle(fontWeight = Bold)
            ) {
                append(words)
            }
        }
    )
}