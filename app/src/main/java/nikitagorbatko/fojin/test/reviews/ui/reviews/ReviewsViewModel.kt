package nikitagorbatko.fojin.test.reviews.ui.reviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import nikitagorbatko.fojin.test.reviews.api.RetrofitReviews
import nikitagorbatko.fojin.test.reviews.data.ReviewsDbRepositoryImpl
import nikitagorbatko.fojin.test.reviews.data.ReviewsRepositoryImpl
import nikitagorbatko.fojin.test.reviews.database.ReviewsDao
import nikitagorbatko.fojin.test.reviews.domain.GetReviewsDbUseCase
import nikitagorbatko.fojin.test.reviews.domain.GetReviewsUseCase


class ReviewsViewModel(reviewsDao: ReviewsDao) : ViewModel() {
    private val retrofit = RetrofitReviews
    private val reviewsRepository = ReviewsRepositoryImpl.getInstance(retrofit, reviewsDao)
    private val reviewsDbRepository = ReviewsDbRepositoryImpl.getInstance(reviewsDao)
    private val getReviewsUseCase = GetReviewsUseCase(reviewsRepository)
    private val getReviewsDbUseCase = GetReviewsDbUseCase(reviewsDbRepository)

    fun pagedReviews(interval: String? = null, keyWord: String? = null) = Pager(
        config = PagingConfig(
            20
            /**,prefetchDistance = 1*/
        ),
        pagingSourceFactory = { ReviewsPagingSource(getReviewsUseCase, getReviewsDbUseCase, interval, keyWord) }
    ).flow.cachedIn(viewModelScope)

    companion object {
        class ReviewsViewModelFactory(private val dao: ReviewsDao) :
            ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(ReviewsViewModel::class.java)) {
                    return ReviewsViewModel(dao) as T
                }
                throw java.lang.IllegalArgumentException("Illegal argument in View Model Factory")
            }
        }
    }
}

