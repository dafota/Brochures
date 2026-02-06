package cab.bonial.brochures.domain.model

data class Brochure(
    val id: String,
    val contentType: String,
    val imageUrl: String?,
    val retailerName: String,
    val distance: Double,
    val isPremium: Boolean,
)