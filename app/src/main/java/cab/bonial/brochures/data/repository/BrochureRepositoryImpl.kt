package cab.bonial.brochures.data.repository

import cab.bonial.brochures.data.api.BrochureApiService
import cab.bonial.brochures.data.model.BrochureContent
import cab.bonial.brochures.data.model.ShelfContentItem
import cab.bonial.brochures.domain.model.Brochure
import cab.bonial.brochures.domain.repository.BrochureRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import javax.inject.Inject

class BrochureRepositoryImpl @Inject constructor(
    private val apiService: BrochureApiService,
    private val json: Json
) : BrochureRepository {
    override suspend fun getBrochures(): Result<List<Brochure>> {
        return try {
            val response = apiService.getBrochures()
            val contents = response.embedded.contents.mapNotNull {
                map(it)
            }

            Result.success(contents)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun map(item: ShelfContentItem): Brochure? {
        if (item.contentType != "brochure") return null

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
        )
    }
}