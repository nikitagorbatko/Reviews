package nikitagorbatko.fojin.test.reviews.ui.reviews

import androidx.annotation.MainThread
import androidx.annotation.Nullable
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import nikitagorbatko.fojin.test.reviews.api.RetrofitReviews
import nikitagorbatko.fojin.test.reviews.api.ReviewDto
import nikitagorbatko.fojin.test.reviews.data.ReviewsRepositoryImpl
import nikitagorbatko.fojin.test.reviews.domain.GetReviewsUseCase
import java.util.concurrent.atomic.AtomicBoolean


class ReviewsViewModel : ViewModel() {
    private lateinit var reviewsPagingSource: ReviewsPagingSource
    private var pager = Pager(
        config = PagingConfig(20/**,prefetchDistance = 1*/),
        pagingSourceFactory = {
            val retrofit = RetrofitReviews
            val reviewsRepository = ReviewsRepositoryImpl.getInstance(retrofit)
            val getReviewsUseCase = GetReviewsUseCase(reviewsRepository)
            reviewsPagingSource = ReviewsPagingSource(getReviewsUseCase)
            reviewsPagingSource
        }
    )

    fun getPagedReviews() = pager.flow.cachedIn(viewModelScope)

    fun invalidate() {
        pager = Pager(
            config = PagingConfig(20/**,prefetchDistance = 1*/),
            pagingSourceFactory = {
                val retrofit = RetrofitReviews
                val reviewsRepository = ReviewsRepositoryImpl.getInstance(retrofit)
                val getReviewsUseCase = GetReviewsUseCase(reviewsRepository)
                reviewsPagingSource = ReviewsPagingSource(getReviewsUseCase)
                reviewsPagingSource
            }
        )
        //reviewsPagingSource.invalidate()
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

