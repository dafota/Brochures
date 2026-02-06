package cab.bonial.brochures.data.mapper

import cab.bonial.brochures.data.model.ShelfContentItem
import cab.bonial.brochures.domain.model.BannerCarousel
import javax.inject.Inject

class BannerCarouselMapper @Inject constructor() : ShelfContentMapper {

    override val supportedTypes: Set<String> = setOf("superBannerCarousel")

    override fun map(item: ShelfContentItem): BannerCarousel? {
        // TODO: Implement when needed
        return null
    }
}
