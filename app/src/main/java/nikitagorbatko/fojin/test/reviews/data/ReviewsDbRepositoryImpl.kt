package nikitagorbatko.fojin.test.reviews.data

import nikitagorbatko.fojin.test.reviews.api.RetrofitReviews
import nikitagorbatko.fojin.test.reviews.database.ReviewsDao
import nikitagorbatko.fojin.test.reviews.ui.entities.ReviewUi
import nikitagorbatko.fojin.test.reviews.utils.ReviewsDboToUiMapper

class ReviewsDbRepositoryImpl private constructor(private val reviewsDao: ReviewsDao): ReviewsDbRepository {
    companion object {
        @JvmStatic
        private var INSTANCE: ReviewsDbRepository? = null

        fun getInstance(reviewsDao: ReviewsDao): ReviewsDbRepository {
            return if (INSTANCE == null) {
                INSTANCE = ReviewsDbRepositoryImpl(reviewsDao)
                INSTANCE!!
            } else {
                INSTANCE!!
            }
        }
    }

    override suspend fun getReviewsFromDb(): List<ReviewUi> {
        val reviews = reviewsDao.getReviews()
        return ReviewsDboToUiMapper.convert(reviews)
    }
}