package cat.alexgluque.paraulogicsolver.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.cancel

actual open class BaseViewModel: ViewModel() {

    actual open var baseViewModelScope = CoroutineScope(Main)

    actual override fun onCleared() {
        baseViewModelScope.cancel()
        super.onCleared()
    }
}