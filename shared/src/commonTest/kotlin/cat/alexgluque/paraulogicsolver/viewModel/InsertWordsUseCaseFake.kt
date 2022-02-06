package cat.alexgluque.paraulogicsolver.viewModel

import cat.alexgluque.paraulogicsolver.usecase.InsertWordsUseCase
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object InsertWordsUseCaseFake : InsertWordsUseCase {

    var insertWordsIsCalled = false

    override suspend fun invoke() {
        insertWordsIsCalled = true
    }
}