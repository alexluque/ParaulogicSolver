package cat.alexgluque.paraulogicsolver.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.ViewModelProvider
import cat.alexgluque.paraulogicsolver.android.ui.theme.ParaulogicSolverTheme
import cat.alexgluque.paraulogicsolver.viewModel.ParaulogicViewModel

class RootActivity : ComponentActivity() {

    private lateinit var viewModel: ParaulogicViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModel()
        setContent {
            ParaulogicSolverTheme {
                Surface(color = MaterialTheme.colors.background) {
                    RootScreen(viewModel)
                }
            }
        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)
            .get(ParaulogicViewModel::class.java)
    }
}