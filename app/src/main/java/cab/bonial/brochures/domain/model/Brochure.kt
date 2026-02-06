package cab.bonial.brochures.domain.model

sealed interface ShelfContent {
    val id: String
    val contentType: String
}

data class Brochure(
    override val id: String,
    override val contentType: String,
    val imageUrl: String?,
    val retailerName: String,
    val distance: Double,
    val isPremium: Boolean,
) : ShelfContent

data class BannerCarousel(
    override val id: String,
    override val contentType: String,
    val banners: List<Banner> = emptyList()
) : ShelfContent

data class Banner(
    val id: String,
    val imageUrl: String?,
    val linkUrl: String?
)