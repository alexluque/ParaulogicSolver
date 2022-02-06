package cat.alexgluque.paraulogicsolver.provider

import kotlinx.coroutines.Dispatchers.Main
import kotlin.coroutines.CoroutineContext

class ServiceProvider {

    fun getCoroutineMainContext(): CoroutineContext = Main
}