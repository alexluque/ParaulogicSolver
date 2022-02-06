package cat.alexgluque.paraulogicsolver.viewModel

import cat.alexgluque.paraulogicsolver.data.localSource.WordsLocalSource

object WordsLocalSourceEmptyFake : WordsLocalSource {

    override suspend fun getWordsStartingWith(
        letter: String
    ): List<String> = emptyList()

    override suspend fun insert(
        words: List<String>) {

    }
}