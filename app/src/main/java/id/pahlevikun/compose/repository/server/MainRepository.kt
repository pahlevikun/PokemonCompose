package id.pahlevikun.compose.repository.server

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import id.pahlevikun.compose.repository.server.service.PokedexService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val pokedexService: PokedexService
) {

    init {
        Timber.d("Injection MainRepository")
    }

    @WorkerThread
    suspend fun loadPokemons(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        pokedexService.fetchPokemons().apply {
            this.suspendOnSuccess {
                data?.let {
                    Timber.d(it.toString())
                    emit(it)
                    onSuccess()
                }
            }.onError {
                onError(message())
            }.onException {
                onError(message())
            }
        }
    }.flowOn(Dispatchers.IO)
}
