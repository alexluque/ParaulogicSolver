package cat.alexgluque.paraulogicsolver.viewModel

import app.cash.turbine.test
import cat.alexgluque.paraulogicsolver.util.runBlockingTest
import cat.alexgluque.paraulogicsolver.viewModel.GetWordsUseCaseFake.clickButtonMatch
import cat.alexgluque.paraulogicsolver.viewModel.InsertWordsUseCaseFake.insertWordsIsCalled
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ParaulogicViewModelTest {

    private val red = "a"
    private val blue = listOf("b", "c", "d", "e", "f", "g")
    private lateinit var viewModel: ParaulogicViewModel

    private fun initViewModel() {
        viewModel = ParaulogicViewModel(
            context = Unconfined,
            localSource = WordsLocalSourceEmptyFake,
            insertWordsUseCase = InsertWordsUseCaseFake
        )
    }

    @Test
    fun init_InsertWordsUseCaseIsCalled() {
        initViewModel()

        assertTrue(insertWordsIsCalled)
    }

    @Test
    fun onStateChange_AllLettersFilled_ButtonEnabled() {
        runBlockingTest {
            initViewModel()

            viewModel.state.test {
                viewModel.onStateChange(red = red, blue = blue)

                assertEquals(true, awaitItem().isButtonEnabled)

                cancel()
            }
        }
    }

    @Test
    fun onStateChange_RedLetterMissing_ButtonDisabled() {
        runBlockingTest {
            initViewModel()

            viewModel.state.test {
                viewModel.onStateChange(red = "", blue = blue)

                assertEquals(false, awaitItem().isButtonEnabled)

                cancel()
            }
        }
    }

    @Test
    fun onStateChange_BlueLetterMissing_ButtonDisabled() {
        runBlockingTest {
            initViewModel()

            viewModel.state.test {
                viewModel.onStateChange(red = red, blue = blue.minus(blue.first()))

                assertEquals(false, awaitItem().isButtonEnabled)

                cancel()
            }
        }
    }

    @Test
    fun onStateChange_IncorrectBlueSize_ButtonDisabled() {
        runBlockingTest {
            initViewModel()

            viewModel.state.test {
                viewModel.onStateChange(red = red, blue = blue.plus("h"))

                assertEquals(false, awaitItem().isButtonEnabled)

                cancel()
            }
        }
    }

    @Test
    fun onStateChange_DuplicatedChar_DuplicatesErrorMessageAndButtonDisabled() {
        runBlockingTest {
            initViewModel()

            viewModel.state.test {
                viewModel.onStateChange(
                    red = red,
                    blue = listOf("", "", red, red, "b", "c")
                )
                val item = awaitItem()

                assertEquals(duplicatesWarning, item.warningMessage)
                assertEquals(false, item.isButtonEnabled)

                cancel()
            }
        }
    }

    @Test
    fun onStateChange_MultipleCharsInField_DiscardAllButFirst() {
        runBlockingTest {
            initViewModel()

            viewModel.state.test {
                viewModel.onStateChange(
                    red = red + red,
                    blue = listOf(blue.first().repeat(2))
                )
                val item = awaitItem()

                assertEquals(red, item.red)
                assertEquals(listOf(blue.first()), item.blue)

                cancel()
            }
        }
    }

    @Test
    fun onClickButton_WordsRetrieved() {
        runBlockingTest {
            viewModel = ParaulogicViewModel(
                context = Unconfined,
                localSource = WordsLocalSourceEmptyFake,
                insertWordsUseCase = InsertWordsUseCaseFake,
                getWordsUseCase = GetWordsUseCaseFake
            )
            viewModel.onStateChange(red = red, blue = blue)

            viewModel.state.test {
                viewModel.onClickButton(red = red, blue = blue)
                val onClickItem = expectMostRecentItem()

                assertEquals(clickButtonMatch.size, onClickItem.wordAmount)
                assertEquals(clickButtonMatch.joinToString(), onClickItem.words)

                cancel()
            }
        }
    }

    @Test
    fun onClickButton_NoWordsRetrieved_EmptyWordsMessage() {
        runBlockingTest {
            initViewModel()
            val red = "8"
            val noResultsBlue = List(6) { red }
            viewModel.onStateChange(red = red, blue = noResultsBlue)

            viewModel.state.test {
                viewModel.onClickButton(red = red, blue = noResultsBlue)
                val onClickItem = expectMostRecentItem()

                assertEquals(0, onClickItem.wordAmount)
                assertEquals(noWordsRetrievedMessage, onClickItem.words)

                cancel()
            }
        }
    }

    @AfterTest
    fun tearDown() {
        insertWordsIsCalled = false
    }
}