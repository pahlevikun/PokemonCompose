package id.pahlevikun.compose.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import id.pahlevikun.compose.repository.server.MainRepository
import id.pahlevikun.compose.repository.server.service.PokedexService

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideMainRepository(
        pokedexService: PokedexService
    ): MainRepository {
        return MainRepository(pokedexService)
    }
}
