package cab.bonial.brochures.domain.usecase

import cab.bonial.brochures.domain.model.Brochure
import cab.bonial.brochures.domain.repository.ShelfRepository
import javax.inject.Inject

class GetBrochuresUseCase @Inject constructor(
    private val repository: ShelfRepository
) {
    suspend operator fun invoke(
        filter: BrochureFilter = BrochureFilter.DEFAULT,
    ): Result<List<Brochure>> {
        return repository.getShelfContents().map {
            it.filterIsInstance<Brochure>()
                .applyFilter(filter)
        }
    }

    private fun List<Brochure>.applyFilter(filter: BrochureFilter): List<Brochure> {
        var result = this

        // Apply distance filter if specified
        filter.maxDistanceKm?.let { maxDistance ->
            result = result.filter { it.distance < maxDistance }
        }

        // Apply premium filter if specified
        if (filter.onlyPremium) {
            result = result.filter { it.isPremium }
        }

        return result
    }
}

data class BrochureFilter(
    val maxDistanceKm: Double?,
    val onlyPremium: Boolean
) {
    companion object {
        val DEFAULT = BrochureFilter(maxDistanceKm = 5.0, false)
    }
}