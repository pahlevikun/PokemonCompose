package id.pahlevikun.compose.presentation.navigation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.android.parcel.Parcelize

sealed class Destination : Parcelable {

    @Parcelize
    object Home : Destination()

    @Immutable
    @Parcelize
    data class PokemonDetails(val pokemonId: Long) : Destination()
}

class Actions(navigator: Navigator<Destination>) {

    val onSelectPokemon: (Long) -> Unit = { pokemonId: Long ->
        navigator.navigate(Destination.PokemonDetails(pokemonId))
    }

    val pressOnBack: () -> Unit = {
        navigator.back()
    }
}
