package id.pahlevikun.compose.repository.server.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Immutable
@Parcelize
data class Pokemon(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("introduce")
    val introduce: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("picture")
    val picture: String
) : Parcelable {

    companion object {
        fun mock() = Pokemon(
            id = 10033,
            name = "Mega Venusaur",
            introduce = "Pokemon XY",
            type = "Mega Evolution",
            description = "With Venusaurite, Venusaur can Mega Evolve into Mega Venusaur.",
            picture = "https://raw.githubusercontent.com/Pokeapi/sprites/master/sprites/pokemon/10033.png"
        )
    }
}