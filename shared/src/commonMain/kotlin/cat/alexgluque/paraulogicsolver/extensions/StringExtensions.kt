package cat.alexgluque.paraulogicsolver.extensions

import doist.x.normalize.Form
import doist.x.normalize.normalize
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
val accentRegex = "\\p{InCombiningDiacriticalMarks}+".toRegex()

fun String.normalize(): String = accentRegex
    .replace(this.normalize(Form.NFD), "")

fun String.getFirstLetter() = if (this.isBlank()) this else this.substring(0, 1)