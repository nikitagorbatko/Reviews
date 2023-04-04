package nikitagorbatko.fojin.test.reviews.ui.single_critic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.receiveAsFlow
import nikitagorbatko.fojin.test.reviews.api.RetrofitReviews
import nikitagorbatko.fojin.test.reviews.data.ReviewsByCriticDbRepositoryImpl
import nikitagorbatko.fojin.test.reviews.data.ReviewsByCriticRepositoryImpl
import nikitagorbatko.fojin.test.reviews.database.ReviewsDao
import nikitagorbatko.fojin.test.reviews.domain.GetReviewsByCriticDbUseCase
import nikitagorbatko.fojin.test.reviews.domain.GetReviewsByCriticUseCase


class SingleCriticFragmentViewModel(reviewsDao: ReviewsDao) : ViewModel() {
    private val retrofit = RetrofitReviews
    private val reviewsByCriticRepository = ReviewsByCriticRepositoryImpl.getInstance(retrofit, reviewsDao)
    private val reviewsByCriticDbRepository = ReviewsByCriticDbRepositoryImpl.getInstance(reviewsDao)
    private val getReviewsByCriticUseCase = GetReviewsByCriticUseCase(reviewsByCriticRepository)
    private val getReviewsByCriticDbUseCase = GetReviewsByCriticDbUseCase(reviewsByCriticDbRepository)

    private val _errorChannel = Channel<String>()
    val errorChannel = _errorChannel.receiveAsFlow()

    fun pagedReviews(reviewer: String) = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = {
            ReviewsByCriticPagingSource(
                getReviewsByCriticUseCase,
                getReviewsByCriticDbUseCase,
                reviewer,
                onErrorCrutch = {
                    _errorChannel.trySendBlocking(it)
                }
            )
        }
    ).flow.cachedIn(viewModelScope)

    companion object {
        class SingleCriticFragmentViewModelFactory(private val dao: ReviewsDao) :
            ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(SingleCriticFragmentViewModel::class.java)) {
                    return SingleCriticFragmentViewModel(dao) as T
                }
                throw java.lang.IllegalArgumentException("Illegal argument in View Model Factory")
            }
        }
    }
}
