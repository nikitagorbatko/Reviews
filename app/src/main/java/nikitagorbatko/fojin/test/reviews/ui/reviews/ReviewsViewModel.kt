package nikitagorbatko.fojin.test.reviews.ui.reviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import nikitagorbatko.fojin.test.reviews.api.RetrofitReviews
import nikitagorbatko.fojin.test.reviews.data.ReviewsRepositoryImpl
import nikitagorbatko.fojin.test.reviews.domain.GetReviewsUseCase

class ReviewsViewModel : ViewModel() {
    private val retrofit = RetrofitReviews
    private val reviewsRepository = ReviewsRepositoryImpl.getInstance(retrofit)
    private val getReviewsUseCase = GetReviewsUseCase(reviewsRepository)

    val pagedReviews = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = { ReviewsPagingSource(getReviewsUseCase) }
    ).flow.cachedIn(viewModelScope)

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

