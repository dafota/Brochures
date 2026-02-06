package cab.bonial.brochures.domain.repository

import cab.bonial.brochures.domain.model.ShelfContent

interface ShelfRepository {
    suspend fun getShelfContents(): Result<List<ShelfContent>>
}