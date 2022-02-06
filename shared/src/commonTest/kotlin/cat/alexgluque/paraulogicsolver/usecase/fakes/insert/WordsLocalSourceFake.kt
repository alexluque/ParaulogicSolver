package cat.alexgluque.paraulogicsolver.usecase.fakes.insert

import cat.alexgluque.paraulogicsolver.data.localSource.WordsLocalSource
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object WordsLocalSourceFake : WordsLocalSource {

    var insertIsCalled = false

    override suspend fun getWordsStartingWith(
        letter: String
    ): List<String> = listOf(letter)

    override suspend fun insert(
        words: List<String>
    ) {
        insertIsCalled = true
    }

}
