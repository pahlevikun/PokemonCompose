package id.pahlevikun.compose.presentation.ui.tab

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
fun CardPokemons(
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
            maxColumnWidth = 330.dp,
            modifier = Modifier.padding(4.dp)
        ) {
            pokemons.forEach { pokemon ->
                CardPokemon(pokemon = pokemon, onSelect = onSelect)
            }
        }
    }
}

@Composable
fun CardPokemon(
    pokemon: Pokemon,
    onSelect: (name: String, id: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        color = yellow,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.clickable(
                onClick = { onSelect(pokemon.name, pokemon.id) },
                indication = RippleIndication(color = orange)
            ).padding(16.dp)
        ) {
            val (image, title, content, type) = createRefs()
            Box(
                modifier = Modifier.constrainAs(image) {
                    centerHorizontallyTo(parent)
                    top.linkTo(parent.top)
                }.preferredHeight(72.dp),
                shape = CircleShape
            ) {
                NetworkImage(
                    url = pokemon.picture,
                    modifier = Modifier
                        .aspectRatio(1.0f)
                        .fillMaxSize()
                )
            }
            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.h5,
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
                modifier = Modifier.constrainAs(type) {
                    centerHorizontallyTo(parent)
                    top.linkTo(title.bottom)
                }.padding(horizontal = 8.dp)
            )
            Text(
                text = pokemon.description,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(content) {
                    centerHorizontallyTo(parent)
                    top.linkTo(type.bottom)
                }.padding(horizontal = 4.dp)
            )
        }
    }
}

@Preview
@Composable
fun CardPokemonPreviewLight() {
    ComposeTheme(darkTheme = false) {
        CardPokemon(
            pokemon = Pokemon.mock(),
            onSelect = { _, _ -> }
        )
    }
}

@Preview
@Composable
fun CardPokemonPreviewDark() {
    ComposeTheme(darkTheme = true) {
        CardPokemon(
            pokemon = Pokemon.mock(),
            onSelect = { _, _ -> }
        )
    }
}
