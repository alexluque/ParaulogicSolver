package cat.alexgluque.paraulogicsolver.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

actual fun runBlockingTest(
    block: suspend CoroutineScope.() -> Unit
) = runBlocking {
    block()
}