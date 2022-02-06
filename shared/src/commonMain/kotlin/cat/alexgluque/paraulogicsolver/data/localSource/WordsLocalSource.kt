package cat.alexgluque.paraulogicsolver.data.localSource

interface WordsLocalSource {

    suspend fun getWordsStartingWith(letter: String): List<String>

    suspend fun insert(words: List<String>)
}