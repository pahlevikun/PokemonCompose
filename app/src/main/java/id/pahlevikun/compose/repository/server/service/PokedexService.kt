package id.pahlevikun.compose.repository.server.service

import com.skydoves.sandwich.ApiResponse
import id.pahlevikun.compose.repository.server.model.Pokemon
import retrofit2.http.GET

interface PokedexService {

    @GET("pokedex-lite.json")
    suspend fun fetchPokemons(): ApiResponse<List<Pokemon>>
}
