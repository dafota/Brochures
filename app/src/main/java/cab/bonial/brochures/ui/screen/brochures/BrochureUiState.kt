package cab.bonial.brochures.ui.screen.brochures

import androidx.compose.runtime.Immutable

@Immutable
data class BrochureUiState(
    val isLoading: Boolean = false,
    val brochures: List<String> = emptyList(),
    val isError: Boolean = false
)
