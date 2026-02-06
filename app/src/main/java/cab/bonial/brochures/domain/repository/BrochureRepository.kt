package cab.bonial.brochures.domain.repository

interface BrochureRepository {
    suspend fun getBrochures(): Result<List<String>>
}