package cat.alexgluque.paraulogicsolver.usecase

import cat.alexgluque.paraulogicsolver.data.localSource.WordsLocalSource
import cat.alexgluque.paraulogicsolver.usecase.fakes.insert.WordsLocalSourceFake.insertIsCalled
import cat.alexgluque.paraulogicsolver.usecase.fakes.insert.WordsLocalSourceEmptyFake
import cat.alexgluque.paraulogicsolver.usecase.fakes.insert.WordsLocalSourceEmptyFake.wordList
import cat.alexgluque.paraulogicsolver.usecase.fakes.insert.WordsLocalSourceFake
import cat.alexgluque.paraulogicsolver.util.runBlockingTest
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals

class InsertWordsUseCaseImpTest {

    private lateinit var wordsLocalSource: WordsLocalSource
    private lateinit var useCase: InsertWordsUseCase

    @Test
    fun invoke_DbIsEmpty_WordsFromAToZInserted() {
        runBlockingTest {
            wordsLocalSource = WordsLocalSourceEmptyFake
            useCase = InsertWordsUseCaseImp(WordsLocalSourceEmptyFake)

            useCase.invoke()

            ('a'..'z').forEach { letter ->
                assertNotEquals(
                    wordsLocalSource.getWordsStartingWith(letter.toString()),
                    emptyList()
                )
            }
        }
    }

    @Test
    fun invoke_DbIsNotEmpty_NothingIsInserted() {
        runBlockingTest {
            wordsLocalSource = WordsLocalSourceFake
            useCase = InsertWordsUseCaseImp(WordsLocalSourceFake)

            useCase.invoke()

            assertFalse(insertIsCalled)

            ('a'..'z').forEach { letter ->
                assertNotEquals(
                    wordsLocalSource.getWordsStartingWith(letter.toString()),
                    emptyList()
                )
            }
        }
    }

    @AfterTest
    fun tearDown() {
        runBlockingTest {
            wordList = mutableListOf()
            insertIsCalled = false
        }
    }
}