package cab.bonial.brochures.data.repository

import cab.bonial.brochures.data.api.BrochureApiService
import cab.bonial.brochures.domain.repository.BrochureRepository
import javax.inject.Inject

class BrochureRepositoryImpl @Inject constructor(
    private val apiService: BrochureApiService,
) : BrochureRepository {
    override suspend fun getBrochures(): Result<List<String>> {
        return try {
            Result.success(apiService.getBrochures())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}