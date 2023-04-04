package nikitagorbatko.fojin.test.reviews.ui.critics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import nikitagorbatko.fojin.test.reviews.domain.GetCriticsDbUseCase
import nikitagorbatko.fojin.test.reviews.domain.GetCriticsUseCase
import nikitagorbatko.fojin.test.reviews.ui.entities.CriticUi

class CriticsViewModel(
    private val getCriticsUseCase: GetCriticsUseCase,
    private val getCriticsDbUseCase: GetCriticsDbUseCase
) : ViewModel() {
    private val _critics = MutableStateFlow(listOf<CriticUi>())
    val critics = _critics.asStateFlow()

    private val _errorChannel = Channel<String>()
    val errorChannel = _errorChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            var result = getCriticsUseCase.execute()
            if (result.isEmpty()) {
                _errorChannel.trySendBlocking("No internet connection")
                result = getCriticsDbUseCase.execute()
            }
            _critics.emit(result)
        }
    }

    companion object {
        class CriticsViewModelFactory(
            private val getCriticsUseCase: GetCriticsUseCase,
            private val getCriticsDbUseCase: GetCriticsDbUseCase
        ) :
            ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(CriticsViewModel::class.java)) {
                    return CriticsViewModel(getCriticsUseCase, getCriticsDbUseCase) as T
                }
                throw java.lang.IllegalArgumentException("Illegal argument in View Model Factory")
            }
        }
    }
}