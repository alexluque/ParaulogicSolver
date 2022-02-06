package cat.alexgluque.paraulogicsolver.viewModel

import cat.alexgluque.paraulogicsolver.data.localSource.WordsLocalSource
import cat.alexgluque.paraulogicsolver.data.localSource.WordsLocalSourceImp
import cat.alexgluque.paraulogicsolver.extensions.getFirstLetter
import cat.alexgluque.paraulogicsolver.usecase.GetWordsUseCase
import cat.alexgluque.paraulogicsolver.usecase.GetWordsUseCaseImp
import cat.alexgluque.paraulogicsolver.usecase.InsertWordsUseCase
import cat.alexgluque.paraulogicsolver.usecase.InsertWordsUseCaseImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ParaulogicViewModel(
    context: CoroutineContext = Main,
    private val localSource: WordsLocalSource = WordsLocalSourceImp(),
    private val insertWordsUseCase: InsertWordsUseCase = InsertWordsUseCaseImp(localSource),
    private val getWordsUseCase: GetWordsUseCase = GetWordsUseCaseImp(localSource)
) : BaseViewModel() {

    private val _state = MutableSharedFlow<ViewState>(replay = 1)
    val state = _state.asSharedFlow()

    init {
        baseViewModelScope = CoroutineScope(context)
        insertWords()
    }

    private fun insertWords() {
        baseViewModelScope.launch {
            insertWordsUseCase.invoke()
        }
    }

    private fun postState(
        transform: (ViewState) -> ViewState
    ) {
        val oldState = state.replayCache.firstOrNull() ?: ViewState()
        val newState = transform(oldState.copy())

        baseViewModelScope.launch {
            _state.emit(newState)
        }
    }

    private fun hasValidLetters(
        red: String,
        blue: List<String>
    ): Boolean = red.isNotBlank()
            && blue.size == 6
            && blue.none { it.isBlank() }

    private fun hasDuplicates(
        red: String,
        blue: List<String>
    ): String {
        val hasDuplicates = blue.plus(red)
            .filter { it.isNotBlank() }
            .groupingBy { it }
            .eachCount()
            .entries.any { it.value > 1 }
        return if (hasDuplicates) duplicatesWarning else ""
    }

    fun onStateChange(
        red: String,
        blue: List<String>
    ) {
        val hasDuplicatesMessage = hasDuplicates(red = red, blue = blue)
        val hasValidLetters = hasValidLetters(red = red, blue = blue)

        postState {
            it.copy(
                isButtonEnabled = hasValidLetters && hasDuplicatesMessage.isBlank(),
                warningMessage = hasDuplicatesMessage,
                red = red.getFirstLetter(),
                blue = blue.map { letter ->
                    letter.getFirstLetter()
                }
            )
        }
    }

    fun onClickButton(
        red: String,
        blue: List<String>
    ) {
        baseViewModelScope.launch {
            val wordList = getWordsUseCase.invoke(
                red = red.lowercase(),
                blue = blue.map { it.lowercase() }
            )

            postState {
                it.copy(
                    wordAmount = wordList.first,
                    words = if (wordList.first == 0) noWordsRetrievedMessage else wordList.second
                )
            }
        }
    }

    /**
     * Emits changes so they can be captured on iOS.
     */
    fun observeState(onChange: (ViewState) -> Unit) {
        state.onEach {
            onChange(it)
        }.launchIn(baseViewModelScope)
    }
}