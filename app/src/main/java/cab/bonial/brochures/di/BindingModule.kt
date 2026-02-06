package cab.bonial.brochures.di

import cab.bonial.brochures.data.mapper.BannerCarouselMapper
import cab.bonial.brochures.data.mapper.BrochureMapper
import cab.bonial.brochures.data.mapper.ShelfContentMapper
import cab.bonial.brochures.data.repository.BrochureRepositoryImpl
import cab.bonial.brochures.domain.repository.BrochureRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {

    @Binds
    @Singleton
    abstract fun bindBrochureRepository(
        repository: BrochureRepositoryImpl
    ): BrochureRepository

    @Binds
    @IntoSet
    abstract fun bindBrochureMapper(mapper: BrochureMapper): ShelfContentMapper

    @Binds
    @IntoSet
    abstract fun bindBannerCarouselMapper(mapper: BannerCarouselMapper): ShelfContentMapper
}