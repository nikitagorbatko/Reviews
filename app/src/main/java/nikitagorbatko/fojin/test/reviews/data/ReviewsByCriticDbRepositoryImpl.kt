package nikitagorbatko.fojin.test.reviews.data

import nikitagorbatko.fojin.test.reviews.api.RetrofitReviews
import nikitagorbatko.fojin.test.reviews.database.ReviewsDao
import nikitagorbatko.fojin.test.reviews.ui.entities.ReviewUi
import nikitagorbatko.fojin.test.reviews.utils.ReviewsDboToUiMapper

class ReviewsByCriticDbRepositoryImpl private constructor(private val reviewsDao: ReviewsDao): ReviewsByCriticDbRepository {
    companion object {
        @JvmStatic
        private var INSTANCE: ReviewsByCriticDbRepository? = null

        fun getInstance(reviewsDao: ReviewsDao): ReviewsByCriticDbRepository {
            return if (INSTANCE == null) {
                INSTANCE = ReviewsByCriticDbRepositoryImpl(reviewsDao)
                INSTANCE!!
            } else {
                INSTANCE!!
            }
        }
    }

    override suspend fun getReviewsByCriticFromDb(reviewer: String): List<ReviewUi> {
        val reviews = reviewsDao.getReviewsByCritic(reviewer)
        return ReviewsDboToUiMapper.convert(reviews)
    }
}