package cat.alexgluque.paraulogicsolver.extensions

import kotlin.test.Test
import kotlin.test.assertEquals

class StringExtensions {

    @Test
    fun normalize_WordWithAccent_MatchWordWithoutAccent() {
        val accent = "demà"
        val noAccent = "dema"
        val normalization = accent.normalize()

        assertEquals(normalization, noAccent)
    }
}