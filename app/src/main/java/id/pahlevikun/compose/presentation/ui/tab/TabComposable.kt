package id.pahlevikun.compose.presentation.ui.tab

import androidx.annotation.StringRes
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.contentColor
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardMembership
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.pahlevikun.compose.R
import id.pahlevikun.compose.common.extensions.visible
import id.pahlevikun.compose.common.utils.navigationBarsHeightPlus
import id.pahlevikun.compose.common.utils.navigationBarsPadding
import id.pahlevikun.compose.presentation.theme.orange
import id.pahlevikun.compose.presentation.theme.red
import id.pahlevikun.compose.presentation.theme.yellow
import id.pahlevikun.compose.presentation.ui.home.HomeViewModel
import id.pahlevikun.compose.repository.server.model.Pokemon

@Composable
fun Pokemons(
    viewModel: HomeViewModel,
    onSelect: (name: String, id: Long) -> Unit,
    selectedTab: PokemonHomeTab,
    setSelectedTab: (PokemonHomeTab) -> Unit
) {
    val pokemons: List<Pokemon> by viewModel.pokemonList.observeAsState(listOf())
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)
    val tabs = PokemonHomeTab.values()
    ConstraintLayout {
        val (body, progress) = createRefs()
        Scaffold(
            backgroundColor = yellow,
            topBar = { pokemonAppBar() },
            modifier = Modifier.constrainAs(body) {
                top.linkTo(parent.top)
            },
            bottomBar = {
                BottomNavigation(
                    backgroundColor = orange,
                    modifier = Modifier
                        .navigationBarsHeightPlus(56.dp)
                ) {
                    tabs.forEach { tab ->
                        BottomNavigationItem(
                            icon = { Icon(asset = tab.icon) },
                            label = { Text(text = stringResource(tab.title), color = Color.White) },
                            selected = tab == selectedTab,
                            onClick = { setSelectedTab(tab) },
                            alwaysShowLabels = false,
                            selectedContentColor = contentColor(),
                            unselectedContentColor = contentColor(),
                            modifier = Modifier.navigationBarsPadding()
                        )
                    }
                }
            }
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            when (selectedTab) {
                PokemonHomeTab.GRID -> GridPokemons(pokemons, onSelect, modifier)
                PokemonHomeTab.CARD -> CardPokemons(pokemons, onSelect, modifier)
            }
        }
        CircularProgressIndicator(
            modifier = Modifier.constrainAs(progress) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.visible(isLoading)
        )
    }
}

@Composable
fun pokemonAppBar() {
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = red,
        modifier = Modifier.preferredHeight(58.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            text = stringResource(R.string.app_name),
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

enum class PokemonHomeTab(
    @StringRes val title: Int,
    val icon: VectorAsset
) {
    GRID(R.string.menu_grid, Icons.Filled.GridOn),
    CARD(R.string.menu_card, Icons.Filled.CardMembership),
}
