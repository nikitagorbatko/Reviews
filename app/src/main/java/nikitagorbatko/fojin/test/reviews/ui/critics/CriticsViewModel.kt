package nikitagorbatko.fojin.test.reviews.ui.critics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nikitagorbatko.fojin.test.reviews.api.CriticsResponseDto
import nikitagorbatko.fojin.test.reviews.api.ResultDto
import nikitagorbatko.fojin.test.reviews.domain.GetCriticsUseCase
import nikitagorbatko.fojin.test.reviews.ui.reviews.ReviewsViewModel

class CriticsViewModel(private val getCriticsUseCase: GetCriticsUseCase) : ViewModel() {
    private val _critics = MutableStateFlow(listOf<ResultDto?>())
    val critics = _critics.asStateFlow()

    init {
        viewModelScope.launch {
            val result = getCriticsUseCase.execute()
            result?.results?.let { _critics.emit(it) }
        }
    }

    companion object {
        class CriticsViewModelFactory(private val getCriticsUseCase: GetCriticsUseCase) :
            ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(CriticsViewModel::class.java)) {
                    return CriticsViewModel(getCriticsUseCase) as T
                }
                throw java.lang.IllegalArgumentException("Illegal argument in View Model Factory")
            }
        }
    }
}