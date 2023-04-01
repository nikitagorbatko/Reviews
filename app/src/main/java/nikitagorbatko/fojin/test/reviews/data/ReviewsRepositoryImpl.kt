package nikitagorbatko.fojin.test.reviews.data

import nikitagorbatko.fojin.test.reviews.api.RetrofitReviews
import nikitagorbatko.fojin.test.reviews.api.ReviewsResponseDto

class ReviewsRepositoryImpl private constructor(private val retrofit: RetrofitReviews) :
    ReviewsRepository {
    companion object {
        @JvmStatic
        private var INSTANCE: ReviewsRepositoryImpl? = null

        fun getInstance(retrofit: RetrofitReviews): ReviewsRepositoryImpl {
            return if (INSTANCE == null) {
                INSTANCE = ReviewsRepositoryImpl(retrofit)
                INSTANCE!!
            } else {
                INSTANCE!!
            }
        }
    }

    override suspend fun getReviews(offset: Int): ReviewsResponseDto? {
        return retrofit.reviewsApi.getReviews(offset)
    }
}