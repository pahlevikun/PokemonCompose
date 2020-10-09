package id.pahlevikun.compose.presentation.ui.home

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import id.pahlevikun.compose.common.base.LiveCoroutinesViewModel
import id.pahlevikun.compose.repository.server.MainRepository
import id.pahlevikun.compose.repository.server.model.Pokemon
import timber.log.Timber

class HomeViewModel @ViewModelInject constructor(
  private val mainRepository: MainRepository
) : LiveCoroutinesViewModel() {

    private var _pokemonList: MutableLiveData<Boolean> = MutableLiveData()
    val pokemonList: LiveData<List<Pokemon>>

    private var _pokemonDetails: LiveData<Pokemon> = MutableLiveData()
    val pokemonDetails: LiveData<Pokemon> get() = _pokemonDetails

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _toast: MutableLiveData<String> = MutableLiveData()
    val toast: LiveData<String> get() = _toast

    init {
        Timber.d("injection MainViewModel")

        pokemonList = _pokemonList.switchMap {
            _isLoading.postValue(true)
            launchOnViewModelScope {
                this.mainRepository.loadPokemons(
                  onSuccess = { _isLoading.postValue(false) },
                  onError = { _toast.postValue(it) }
                ).asLiveData()
            }
        }
    }

    @MainThread
    fun fetchPokemons() {
        _pokemonList.value = true
    }

    @WorkerThread
    fun getPokemonDetails(id: Long) {
        _pokemonDetails = MutableLiveData(pokemonList.value?.find { it.id == id })
    }
}
