package cab.bonial.brochures.domain.repository

import cab.bonial.brochures.domain.model.Brochure

interface BrochureRepository {
    suspend fun getBrochures(): Result<List<Brochure>>
}