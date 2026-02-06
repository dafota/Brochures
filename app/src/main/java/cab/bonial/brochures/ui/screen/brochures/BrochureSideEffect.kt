package cab.bonial.brochures.ui.screen.brochures

sealed interface BrochureSideEffect {
    data class ShowToast(val message: String) : BrochureSideEffect
}