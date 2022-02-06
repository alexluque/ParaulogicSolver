package cat.alexgluque.paraulogicsolver.usecase

import cat.alexgluque.paraulogicsolver.data.localSource.WordsLocalSource
import cat.alexgluque.paraulogicsolver.extensions.normalize

interface GetWordsUseCase {

    suspend fun invoke(
        red: String,
        blue: List<String>
    ): Pair<Int, String>
}

class GetWordsUseCaseImp(
    private val localSource: WordsLocalSource
) : GetWordsUseCase {

    override suspend fun invoke(
        red: String,
        blue: List<String>
    ): Pair<Int, String> {
        val result = mutableListOf<String>()

        ('a'..'z').forEach { letter ->
            result.addAll(
                localSource
                    .getWordsStartingWith(letter.toString())
                    .asSequence()
                    .filter { it.length > 2 }
                    .filter { it.contains(red.normalize()) }
                    .filter { word ->
                        var isMatch = true
                        val normalization = word
                            .substringBeforeLast(" -")
                            .replace("-","")
                            .replace(" ","")
                            .replace(red, "")
                            .normalize()

                        for (w in normalization) {
                            if (!blue.contains(w.toString())) {
                                isMatch = false
                                break
                            }
                        }

                        isMatch
                    }.toList()
            )
        }

        return Pair(result.size, result.joinToString())
    }
}