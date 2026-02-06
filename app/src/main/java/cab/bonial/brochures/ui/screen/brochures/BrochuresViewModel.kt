package cab.bonial.brochures.ui.screen.brochures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BrochuresViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(BrochureUiState())
    val state = _state.asStateFlow()

    init {
        loadBrochures()
    }

    fun processIntent(intent: BrochureIntent) {
        when (intent) {
            BrochureIntent.LoadBrochures -> loadBrochures()
        }
    }

    private fun loadBrochures() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, isError = false) }
            delay(4000)
            val fakeData = listOf("Brochure1", "Brochure2", "Brochure3")
            _state.update { it.copy(isLoading = false, isError = false, brochures = fakeData) }
        }
    }
}
