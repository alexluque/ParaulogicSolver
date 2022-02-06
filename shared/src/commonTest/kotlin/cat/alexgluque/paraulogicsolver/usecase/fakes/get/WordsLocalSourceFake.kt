package cat.alexgluque.paraulogicsolver.usecase.fakes.get

import cat.alexgluque.paraulogicsolver.data.localSource.WordsLocalSource

object WordsLocalSourceFake : WordsLocalSource {

    val wordsIn = listOf("ampit", "apa", "alpí -ina", "altiplà")

    private val wordsOut = listOf("sol", "pop", "womb", "a contrapel")
    private val words = wordsIn + wordsOut

    override suspend fun getWordsStartingWith(
        letter: String
    ): List<String> = words
        .filter {
            it.startsWith(letter)
        }

    override suspend fun insert(
        words: List<String>
    ) {

    }
}