package id.pahlevikun.compose.presentation.ui.tab

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.ripple.RippleIndication
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import id.pahlevikun.compose.common.views.NetworkImage
import id.pahlevikun.compose.common.utils.statusBarsPadding
import id.pahlevikun.compose.common.views.StaggeredVerticalGrid
import id.pahlevikun.compose.presentation.theme.ComposeTheme
import id.pahlevikun.compose.presentation.theme.orange
import id.pahlevikun.compose.presentation.theme.yellow
import id.pahlevikun.compose.repository.server.model.Pokemon

@Composable
fun GridPokemons(
    pokemons: List<Pokemon>,
    onSelect: (name: String, id: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    ScrollableColumn(
        modifier = modifier
            .statusBarsPadding()
            .background(MaterialTheme.colors.background)
    ) {
        StaggeredVerticalGrid(
            maxColumnWidth = 220.dp,
            modifier = Modifier.padding(4.dp)
        ) {
            pokemons.forEach { pokemon ->
                GridPokemon(pokemon = pokemon, onSelect = onSelect)
            }
        }
    }
}

@Composable
fun GridPokemon(
    pokemon: Pokemon,
    onSelect: (name: String, id: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.padding(8.dp),
        color = yellow,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.clickable(
                onClick = { onSelect(pokemon.name, pokemon.id) },
                indication = RippleIndication(color = orange)
            )
        ) {
            val (image, title, content) = createRefs()
            NetworkImage(
                url = pokemon.picture,
                modifier = Modifier.constrainAs(image) {
                    centerHorizontallyTo(parent)
                    top.linkTo(parent.top)
                }.aspectRatio(0.8f)
            )
            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(title) {
                    centerHorizontallyTo(parent)
                    top.linkTo(image.bottom)
                }.padding(8.dp)
            )
            Text(
                text = pokemon.type,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(content) {
                    centerHorizontallyTo(parent)
                    top.linkTo(title.bottom)
                }.padding(horizontal = 8.dp)
                    .padding(bottom = 12.dp)
            )
        }
    }
}

@Preview
@Composable
fun GridPokemonPreviewLight() {
    ComposeTheme(darkTheme = false) {
        GridPokemon(
            pokemon = Pokemon.mock(),
            onSelect = { _, _ -> }
        )
    }
}

@Preview
@Composable
fun GridPokemonPreviewDark() {
    ComposeTheme(darkTheme = true) {
        GridPokemon(
            pokemon = Pokemon.mock(),
            onSelect = { _, _ -> }
        )
    }
}
