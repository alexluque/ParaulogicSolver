package cat.alexgluque.paraulogicsolver.android.ui

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable

@Composable
fun hexagonShape(): GenericShape = GenericShape { size, _ ->
    // UPPER TRIANGLE
    // situa el punto en la mitad del ancho de la parte superior
    moveTo(size.width / 2, 0f)
    // traza línea del final del ancho a 1/4 del alto
    lineTo(size.width, size.height / 4)
    // traza línea del offset width hasta el alto indicado
    relativeLineTo(-size.width, 0f)

    // MIDDLE LEFT
    moveTo(0f, size.height / 4)
    lineTo(size.width, size.height / 4)
    relativeLineTo(-size.width, size.height / 2)

    // MIDDLE RIGHT
    moveTo(size.width, size.height / 4)
    lineTo(size.width, size.height - (size.height / 4))
    relativeLineTo(-size.width, 0f)

    // LOWER TRIANGLE
    moveTo(size.width / 2, size.height)
    lineTo(size.width, size.height - (size.height / 4))
    relativeLineTo(-size.width, 0f)
}