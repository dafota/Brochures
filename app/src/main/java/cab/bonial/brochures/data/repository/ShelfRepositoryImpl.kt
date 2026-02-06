package cab.bonial.brochures.data.repository

import android.util.Log
import cab.bonial.brochures.data.api.ShelfApiService
import cab.bonial.brochures.data.mapper.ShelfContentMapper
import cab.bonial.brochures.domain.model.ShelfContent
import cab.bonial.brochures.domain.repository.ShelfRepository
import javax.inject.Inject

class ShelfRepositoryImpl @Inject constructor(
    private val apiService: ShelfApiService,
    private val mappers: Set<@JvmSuppressWildcards ShelfContentMapper>,
) : ShelfRepository {
    override suspend fun getShelfContents(): Result<List<ShelfContent>> = try {
        val response = apiService.getShelf()
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