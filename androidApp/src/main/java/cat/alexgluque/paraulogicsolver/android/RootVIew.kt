package cat.alexgluque.paraulogicsolver.android

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cat.alexgluque.paraulogicsolver.android.ui.HexagonField
import cat.alexgluque.paraulogicsolver.android.ui.InsertButton
import cat.alexgluque.paraulogicsolver.android.ui.SolutionText
import cat.alexgluque.paraulogicsolver.android.ui.theme.ParaulogicSolverTheme
import cat.alexgluque.paraulogicsolver.android.ui.theme.hexagonRed
import cat.alexgluque.paraulogicsolver.extensions.getFirstLetter
import cat.alexgluque.paraulogicsolver.viewModel.ParaulogicViewModel
import cat.alexgluque.paraulogicsolver.viewModel.ViewState

@Composable
fun RootScreen(viewModel: ParaulogicViewModel) {
    val state: ViewState by viewModel.state.collectAsState(
        initial = ViewState()
    )
    ParaulogicSolverTheme {
        RootContent(
            state = state,
            onStateChange = { red, blue ->
                viewModel.onStateChange(red, blue)
            },
            onClickButton = { red, blue ->
                viewModel.onClickButton(red, blue)
            }
        )
    }
}

@Composable
fun RootContent(
    state: ViewState,
    onStateChange: (String, List<String>) -> Unit,
    onClickButton: (String, List<String>) -> Unit
) {
    var blueUpperLeft by rememberSaveable { mutableStateOf("") }
    var blueUpperRight by rememberSaveable { mutableStateOf("") }
    var blueCenterLeft by rememberSaveable { mutableStateOf("") }
    var blueCenterRight by rememberSaveable { mutableStateOf("") }
    var blueLowerLeft by rememberSaveable { mutableStateOf("") }
    var blueLowerRight by rememberSaveable { mutableStateOf("") }
    var red by rememberSaveable { mutableStateOf("") }

    fun blue(): List<String> = listOf(
        blueUpperLeft,
        blueUpperRight,
        blueCenterLeft,
        blueCenterRight,
        blueLowerLeft,
        blueLowerRight
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 25.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Center,
        horizontalAlignment = CenterHorizontally,
    ) {
        Row {
            HexagonField(
                value = blueUpperLeft,
                modifier = Modifier
                    .width(50.dp)
                    .padding(end = 2.dp),
                onValueChange = {
                    blueUpperLeft = it.getFirstLetter()
                    onStateChange(red, blue())
                }
            )
            HexagonField(
                value = blueUpperRight,
                modifier = Modifier
                    .width(50.dp)
                    .padding(start = 2.dp),
                onValueChange = {
                    blueUpperRight = it.getFirstLetter()
                    onStateChange(red, blue())
                }
            )
        }
        Row(
            modifier = Modifier.offset(y = (-12).dp)
        ) {
            HexagonField(
                value = blueCenterLeft,
                modifier = Modifier
                    .width(50.dp)
                    .padding(end = 3.dp),
                onValueChange = {
                    blueCenterLeft = it.getFirstLetter()
                    onStateChange(red, blue())
                }
            )
            HexagonField(
                value = red,
                modifier = Modifier
                    .width(50.dp),
                color = hexagonRed,
                onValueChange = {
                    red = it.getFirstLetter()
                    onStateChange(red, blue())
                }
            )
            HexagonField(
                value = blueCenterRight,
                modifier = Modifier
                    .width(50.dp)
                    .padding(start = 3.dp),
                onValueChange = {
                    blueCenterRight = it.getFirstLetter()
                    onStateChange(red, blue())
                }
            )
        }
        Row(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .offset(y = (-24).dp)
        ) {
            HexagonField(
                value = blueLowerLeft,
                modifier = Modifier
                    .width(50.dp)
                    .padding(end = 2.dp),
                onValueChange = {
                    blueLowerLeft = it.getFirstLetter()
                    onStateChange(red, blue())
                }
            )
            HexagonField(
                value = blueLowerRight,
                modifier = Modifier
                    .width(50.dp)
                    .padding(start = 2.dp),
                onValueChange = {
                    blueLowerRight = it.getFirstLetter()
                    onStateChange(red, blue())
                }
            )
        }

        if (state.warningMessage.isNotBlank()) {
            Text(
                text = state.warningMessage,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 25.dp)
            )
        }

        InsertButton(
            onClick = { onClickButton(red, blue()) },
            enabled = state.isButtonEnabled
        )

        if (state.words.isNotBlank()) {
            SolutionText(
                wordAmount = state.wordAmount,
                words = state.words
            )
        }

        Spacer(modifier = Modifier.weight(1.0f))
    }
}

@Preview(showBackground = true)
@Composable
fun RootPreview() {
    val state = ViewState(
        red = "A",
        blue = listOf("B", "C", "D", "E", "F", "G"),
        wordAmount = 2,
        words = "ace, beca, boca, nas, orella",
        warningMessage = "Alerta!"
    )
    ParaulogicSolverTheme {
        RootContent(
            state = state,
            onStateChange = { _, _ -> },
            onClickButton = { _, _ -> }
        )
    }
}