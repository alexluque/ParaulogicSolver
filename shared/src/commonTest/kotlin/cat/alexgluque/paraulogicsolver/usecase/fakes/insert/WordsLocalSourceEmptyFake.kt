package cat.alexgluque.paraulogicsolver.usecase.fakes.insert

import cat.alexgluque.paraulogicsolver.data.localSource.WordsLocalSource
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object WordsLocalSourceEmptyFake : WordsLocalSource {

    var wordList = mutableListOf<String>()

    override suspend fun getWordsStartingWith(
        letter: String
    ): List<String> = wordList.filter {
        it.startsWith(letter)
    }

    override suspend fun insert(
        words: List<String>
    ) {
        wordList.add(words.first())
    }

}
