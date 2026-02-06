package cab.bonial.brochures.di

import cab.bonial.brochures.data.repository.BrochureRepositoryImpl
import cab.bonial.brochures.domain.repository.BrochureRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {

    @Binds
    @Singleton
    abstract fun bindBrochureRepository(
        repository: BrochureRepositoryImpl
    ): BrochureRepository
}