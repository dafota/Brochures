package cab.bonial.brochures.data.mapper

import cab.bonial.brochures.data.model.ShelfContentItem
import cab.bonial.brochures.domain.model.ShelfContent

interface ShelfContentMapper {
    val supportedTypes: Set<String>
    fun supports(item: ShelfContentItem): Boolean = item.contentType in supportedTypes
    fun map(item: ShelfContentItem): ShelfContent?
}