package cat.alexgluque.paraulogicsolver.usecase

import cat.alexgluque.paraulogicsolver.data.localSource.WordsLocalSource
import cat.alexgluque.paraulogicsolver.data.words.*

interface InsertWordsUseCase {
    suspend fun invoke()
}

class InsertWordsUseCaseImp(
    private val localSource: WordsLocalSource
) : InsertWordsUseCase {

    override suspend fun invoke() {
        if (localSource.getWordsStartingWith("a").isEmpty()) {
            localSource.insert(wordsA)
            localSource.insert(wordsB)
            localSource.insert(wordsC)
            localSource.insert(wordsD)
            localSource.insert(wordsE)
            localSource.insert(wordsF)
            localSource.insert(wordsG)
            localSource.insert(wordsH)
            localSource.insert(wordsI)
            localSource.insert(wordsJ)
            localSource.insert(wordsK)
            localSource.insert(wordsL)
            localSource.insert(wordsM)
            localSource.insert(wordsN)
            localSource.insert(wordsO)
            localSource.insert(wordsP)
            localSource.insert(wordsQ)
            localSource.insert(wordsR)
            localSource.insert(wordsS)
            localSource.insert(wordsT)
            localSource.insert(wordsU)
            localSource.insert(wordsV)
            localSource.insert(wordsW)
            localSource.insert(wordsX)
            localSource.insert(wordsY)
            localSource.insert(wordsZ)
        }
    }
}