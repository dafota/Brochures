package cab.bonial.brochures.data.mapper

import cab.bonial.brochures.data.model.BrochureContent
import cab.bonial.brochures.data.model.ShelfContentItem
import cab.bonial.brochures.domain.model.Brochure
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import javax.inject.Inject

class BrochureMapper @Inject constructor(
    private val json: Json
) : ShelfContentMapper {

    override val supportedTypes: Set<String> = setOf("brochure", "brochurePremium")

    override fun map(item: ShelfContentItem): Brochure? {
        val contentElement = item.content
        if (contentElement == null || contentElement !is JsonObject) return null

        val brochureContent = try {
            json.decodeFromJsonElement<BrochureContent>(contentElement)
        } catch (e: Exception) {
            return null
        }

        val id = brochureContent.getIdAsString() ?: return null
        val retailerName = brochureContent.publisher?.name ?: "Unknown Retailer"

        return Brochure(
            id = id,
            contentType = item.contentType,
            imageUrl = brochureContent.brochureImage,
            retailerName = retailerName,
            distance = brochureContent.distance ?: 0.0,
            isPremium = item.contentType == "brochurePremium"
        )
    }
}