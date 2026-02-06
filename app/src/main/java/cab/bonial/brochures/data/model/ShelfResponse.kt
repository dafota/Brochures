package cab.bonial.brochures.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive

@Serializable
data class ShelfResponse(
    @SerialName("_embedded")
    val embedded: ShelfEmbedded
)

@Serializable
data class ShelfEmbedded(
    val contents: List<ShelfContentItem>
)

@Serializable
data class ShelfContentItem(
    val contentType: String,
    val content: JsonElement? = null
)


@Serializable
data class BrochureContent(
    // id can be either a number or string in the API
    val id: JsonPrimitive? = null,
    val brochureImage: String? = null,
    val publisher: BrochurePublisher? = null,
    val distance: Double? = null
) {
    fun getIdAsString(): String? = id?.content
}

@Serializable
data class BrochurePublisher(
    val name: String? = null
)
