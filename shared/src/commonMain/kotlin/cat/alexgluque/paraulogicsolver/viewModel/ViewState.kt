package cat.alexgluque.paraulogicsolver.viewModel

data class ViewState(
    val isButtonEnabled: Boolean = false,
    val red: String = "",
    val blue: List<String> = List(size = 6) { "" },
    val wordAmount: Int = 0,
    val words: String = "",
    val warningMessage: String = ""
)