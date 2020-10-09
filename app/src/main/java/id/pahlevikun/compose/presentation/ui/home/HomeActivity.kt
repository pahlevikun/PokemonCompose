package id.pahlevikun.compose.presentation.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import dagger.hilt.android.AndroidEntryPoint
import id.pahlevikun.compose.presentation.theme.ComposeTheme

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.toast.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.fetchPokemons()
        setContent {
            ComposeTheme {
                PokemonMain(
                    context = this,
                    viewModel = viewModel,
                    backDispatcher = onBackPressedDispatcher
                )
            }
        }
    }
}
