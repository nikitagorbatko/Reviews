package nikitagorbatko.fojin.test.reviews.ui.reviews

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
import nikitagorbatko.fojin.test.reviews.data.ReviewsDbRepositoryImpl
import nikitagorbatko.fojin.test.reviews.data.ReviewsRepositoryImpl
import nikitagorbatko.fojin.test.reviews.database.ReviewsDao
import nikitagorbatko.fojin.test.reviews.domain.GetReviewsDbUseCase
import nikitagorbatko.fojin.test.reviews.domain.GetReviewsUseCase


class ReviewsFragmentViewModel(reviewsDao: ReviewsDao) : ViewModel() {
    private val retrofit = RetrofitReviews
    private val reviewsRepository = ReviewsRepositoryImpl.getInstance(retrofit, reviewsDao)
    private val reviewsDbRepository = ReviewsDbRepositoryImpl.getInstance(reviewsDao)
    private val getReviewsUseCase = GetReviewsUseCase(reviewsRepository)
    private val getReviewsDbUseCase = GetReviewsDbUseCase(reviewsDbRepository)

    private val _errorChannel = Channel<String>()
    val errorChannel = _errorChannel.receiveAsFlow()

    fun pagedReviews(interval: Pair<String, String>? = null, keyWord: String? = null) = Pager(
        config = PagingConfig(
            20
            /**,prefetchDistance = 1*/
        ),
        pagingSourceFactory = {
            ReviewsPagingSource(
                getReviewsUseCase,
                getReviewsDbUseCase,
                interval,
                keyWord,
                onErrorCrutch = {
                    _errorChannel.trySendBlocking(it)
                }
            )
        }
    ).flow.cachedIn(viewModelScope)

    companion object {
        class ReviewsViewModelFactory(private val dao: ReviewsDao) :
            ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(ReviewsFragmentViewModel::class.java)) {
                    return ReviewsFragmentViewModel(dao) as T
                }
                throw java.lang.IllegalArgumentException("Illegal argument in View Model Factory")
            }
        }
    }
}

