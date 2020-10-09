package id.pahlevikun.compose.presentation.ui.home

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import id.pahlevikun.compose.common.utils.ProvideDisplayInsets
import id.pahlevikun.compose.presentation.navigation.Actions
import id.pahlevikun.compose.presentation.navigation.BackDispatcherAmbient
import id.pahlevikun.compose.presentation.navigation.Destination
import id.pahlevikun.compose.presentation.navigation.Navigator
import id.pahlevikun.compose.presentation.ui.tab.PokemonHomeTab
import id.pahlevikun.compose.presentation.ui.tab.Pokemons

@Composable
fun PokemonMain(context: Context, viewModel: HomeViewModel, backDispatcher: OnBackPressedDispatcher) {
    val navigator: Navigator<Destination> = rememberSavedInstanceState(
        saver = Navigator.saver(backDispatcher)
    ) {
        Navigator(Destination.Home, backDispatcher)
    }
    val actions = remember(navigator) { Actions(navigator) }
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(PokemonHomeTab.GRID) }

    Providers(BackDispatcherAmbient provides backDispatcher) {
        ProvideDisplayInsets {
            Crossfade(navigator.current) { destination ->
                when (destination) {
                    Destination.Home -> Pokemons(
                        viewModel = viewModel,
                        onSelect = { name, _ ->
                            Toast.makeText(context, "This is $name",Toast.LENGTH_SHORT).apply {
                                setGravity(Gravity.TOP,0,0)
                            }.show()
                        },
                        selectedTab = selectedTab,
                        setSelectedTab = setSelectedTab
                    )
                    is Destination.PokemonDetails -> Unit
                }
            }
        }
    }
}
