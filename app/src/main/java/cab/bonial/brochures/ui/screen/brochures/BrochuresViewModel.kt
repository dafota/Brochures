package cab.bonial.brochures.ui.screen.brochures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cab.bonial.brochures.domain.usecase.GetBrochuresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BrochuresViewModel @Inject constructor(
    private val getBrochuresUseCase: GetBrochuresUseCase
) : ViewModel() {

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

            getBrochuresUseCase()
                .onSuccess { brochures ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            brochures = brochures,
                            isError = false
                        )
                    }
                }
                .onFailure {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = true
                        )
                    }
                }
        }
    }
}
