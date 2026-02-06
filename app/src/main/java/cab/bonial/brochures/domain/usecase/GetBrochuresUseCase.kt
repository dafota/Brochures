package cab.bonial.brochures.domain.usecase

import cab.bonial.brochures.domain.model.Brochure
import cab.bonial.brochures.domain.repository.ShelfRepository
import javax.inject.Inject

class GetBrochuresUseCase @Inject constructor(
    private val repository: ShelfRepository
) {
    suspend operator fun invoke(): Result<List<Brochure>> {
        return repository.getShelfContents().map {
            it.filterIsInstance<Brochure>()
        }
    }
}