package cat.alexgluque.paraulogicsolver.viewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.cancel

actual open class BaseViewModel {

    actual open var baseViewModelScope: CoroutineScope = CoroutineScope(Main)

    protected actual open fun onCleared() {
        baseViewModelScope.cancel()
    }
}