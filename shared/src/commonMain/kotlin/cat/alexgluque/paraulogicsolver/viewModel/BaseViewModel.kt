package cat.alexgluque.paraulogicsolver.viewModel

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {

    open var baseViewModelScope: CoroutineScope

    protected fun onCleared()
}