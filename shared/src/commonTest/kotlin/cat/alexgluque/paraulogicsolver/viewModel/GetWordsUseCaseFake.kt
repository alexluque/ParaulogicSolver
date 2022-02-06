package cat.alexgluque.paraulogicsolver.viewModel

import cat.alexgluque.paraulogicsolver.usecase.GetWordsUseCase

object GetWordsUseCaseFake : GetWordsUseCase {

    val clickButtonMatch = listOf("abc", "acb")

    override suspend fun invoke(
        red: String,
        blue: List<String>
    ): Pair<Int, String> = Pair(
        clickButtonMatch.size,
        clickButtonMatch.joinToString()
    )
}