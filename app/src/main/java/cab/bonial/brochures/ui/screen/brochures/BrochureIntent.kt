package cab.bonial.brochures.ui.screen.brochures

sealed interface BrochureIntent {
    data object LoadBrochures : BrochureIntent
}
