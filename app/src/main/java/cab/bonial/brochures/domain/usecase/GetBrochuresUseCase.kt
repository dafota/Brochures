package cab.bonial.brochures.domain.usecase

import cab.bonial.brochures.domain.model.Brochure
import cab.bonial.brochures.domain.model.ShelfContent
import cab.bonial.brochures.domain.repository.BrochureRepository
import javax.inject.Inject

class GetBrochuresUseCase @Inject constructor(
    private val repository: BrochureRepository
) {
    suspend operator fun invoke(): Result<List<Brochure>> {
        return repository.getBrochures().map {
            it.filterIsInstance<Brochure>()
        }
    }
}