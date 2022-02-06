package cat.alexgluque.paraulogicsolver.data.localSource

import cat.alexgluque.paraulogicsolver.ParaulogicDB

class WordsLocalSourceImp : WordsLocalSource {

    private val queries = ParaulogicDB(createDriver()).wordsQueries

    override suspend fun getWordsStartingWith(
        letter: String
    ): List<String> = queries
        .getWordsStartingWith(letter)
        .executeAsList()
        .map { it.word }

    override suspend fun insert(
        words: List<String>
    ) {
        queries.transaction {
            words.forEach {
                queries.insert(it)
            }
        }
    }
}