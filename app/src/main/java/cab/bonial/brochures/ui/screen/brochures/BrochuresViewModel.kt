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

    private val _state = MutableStateFlow(1)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            while (true) {
                _state.update { it + 1 }
                delay(2000)
            }
        }
    }

}