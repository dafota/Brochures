package cab.bonial.brochures.data.repository

import android.util.Log
import cab.bonial.brochures.data.api.BrochureApiService
import cab.bonial.brochures.data.mapper.ShelfContentMapper
import cab.bonial.brochures.domain.model.ShelfContent
import cab.bonial.brochures.domain.repository.BrochureRepository
import javax.inject.Inject

class BrochureRepositoryImpl @Inject constructor(
    private val apiService: BrochureApiService,
    private val mappers: Set<@JvmSuppressWildcards ShelfContentMapper>,
) : BrochureRepository {
    override suspend fun getBrochures(): Result<List<ShelfContent>> = try {
        val response = apiService.getBrochures()
        val contents = response.embedded.contents
            .mapNotNull { item ->
                // Find the mapper that supports this item, then map
                mappers
                    .find { it.supports(item) }
                    ?.map(item)
            }

        Result.success(contents)
    } catch (e: Exception) {
        Log.e("ShelfRepository", "Error fetching contents", e)
        Result.failure(e)
    }
}