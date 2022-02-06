package cat.alexgluque.paraulogicsolver.usecase

import cat.alexgluque.paraulogicsolver.usecase.fakes.get.WordsLocalSourceFake
import cat.alexgluque.paraulogicsolver.usecase.fakes.get.WordsLocalSourceFake.wordsIn
import cat.alexgluque.paraulogicsolver.util.runBlockingTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetWordsUseCaseImpTest {

    private val red = "p"
    private val blue = listOf("a", "i", "l", "m", "s", "t")
    private val useCase = GetWordsUseCaseImp(WordsLocalSourceFake)

    @Test
    fun invoke_wordsMatchingLetters_WordsInRetrieved() {
        runBlockingTest {
            val expected = Pair(wordsIn.size, wordsIn.joinToString())
            val result = useCase.invoke(red = red, blue = blue)

            assertEquals(expected.first, result.first)
            assertEquals(expected.second, result.second)
        }
    }
}