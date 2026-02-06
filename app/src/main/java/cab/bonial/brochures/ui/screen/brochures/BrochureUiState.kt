package cab.bonial.brochures.ui.screen.brochures

import androidx.compose.runtime.Immutable
import cab.bonial.brochures.domain.model.Brochure

@Immutable
data class BrochureUiState(
    val isLoading: Boolean = false,
    val brochures: List<Brochure> = emptyList(),
    val isError: Boolean = false
)
