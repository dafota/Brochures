package cab.bonial.brochures.domain.repository

import cab.bonial.brochures.domain.model.Brochure
import cab.bonial.brochures.domain.model.ShelfContent

interface BrochureRepository {
    suspend fun getBrochures(): Result<List<ShelfContent>>
}