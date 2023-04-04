package nikitagorbatko.fojin.test.reviews.data

import nikitagorbatko.fojin.test.reviews.database.ReviewDbo
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

    override suspend fun getReviewsFromDb(interval: Pair<String, String>?, keyWord: String?): List<ReviewUi> {
        var reviews: List<ReviewDbo>
        if (interval != null) {
            reviews = reviewsDao.getReviewsInterval(interval.first, interval.second)
            return ReviewsDboToUiMapper.convert(reviews)
        }
        if (keyWord != null) {
            reviews = reviewsDao.getReviewsKeyWord(keyWord)
            return ReviewsDboToUiMapper.convert(reviews)
        }
        reviews = reviewsDao.getReviews()
        return ReviewsDboToUiMapper.convert(reviews)
    }
}