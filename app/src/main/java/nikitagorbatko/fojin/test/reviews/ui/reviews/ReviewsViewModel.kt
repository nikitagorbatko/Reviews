package nikitagorbatko.fojin.test.reviews.ui.reviews

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import nikitagorbatko.fojin.test.reviews.api.RetrofitReviews
import nikitagorbatko.fojin.test.reviews.api.ReviewDto
import nikitagorbatko.fojin.test.reviews.data.ReviewsRepositoryImpl
import nikitagorbatko.fojin.test.reviews.domain.GetReviewsIntervalUseCase
import nikitagorbatko.fojin.test.reviews.domain.GetReviewsKeyWordUseCase
import nikitagorbatko.fojin.test.reviews.domain.GetReviewsUseCase


class ReviewsViewModel : ViewModel() {
    private val retrofit = RetrofitReviews
    private val reviewsRepository = ReviewsRepositoryImpl.getInstance(retrofit)
    private val getReviewsUseCase = GetReviewsUseCase(reviewsRepository)
    private val getReviewsIntervalUseCase = GetReviewsIntervalUseCase(reviewsRepository)
    private val getReviewsKeyWordUseCase = GetReviewsKeyWordUseCase(reviewsRepository)

//    var pagedReviews = Pager(
//        config = PagingConfig(
//            20
//            /**,prefetchDistance = 1*/
//        ),
//        pagingSourceFactory = { ReviewsPagingSource(getReviewsUseCase) }
//    ).flow.cachedIn(viewModelScope)

    fun pagedReviews() = Pager(
        config = PagingConfig(
            20
            /**,prefetchDistance = 1*/
        ),
        pagingSourceFactory = { ReviewsPagingSource(getReviewsUseCase) }
    ).flow.cachedIn(viewModelScope)

    fun pagedReviewsInterval(interval: String): Flow<PagingData<ReviewDto>> {
        return Pager(
            config = PagingConfig(
                20
                /**,prefetchDistance = 1*/
            ),
            pagingSourceFactory = { ReviewsIntervalPagingSource(getReviewsIntervalUseCase, interval) }
        ).flow.cachedIn(viewModelScope)
    }

    fun pagedReviewsKeyWord(keyWord: String): Flow<PagingData<ReviewDto>> {
        return Pager(
            config = PagingConfig(
                20
                /**,prefetchDistance = 1*/
            ),
            pagingSourceFactory = { ReviewsKeyWordPagingSource(getReviewsKeyWordUseCase, keyWord) }
        ).flow.cachedIn(viewModelScope)
    }

    companion object {
        class ReviewsViewModelFactory() :
            ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(ReviewsViewModel::class.java)) {
                    return ReviewsViewModel() as T
                }
                throw java.lang.IllegalArgumentException("Illegal argument in View Model Factory")
            }
        }
    }
}

