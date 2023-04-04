package nikitagorbatko.fojin.test.reviews.data

import nikitagorbatko.fojin.test.reviews.api.RetrofitReviews
import nikitagorbatko.fojin.test.reviews.api.ReviewsResponseDto
import nikitagorbatko.fojin.test.reviews.database.ReviewsDao
import nikitagorbatko.fojin.test.reviews.ui.entities.ReviewUi
import nikitagorbatko.fojin.test.reviews.utils.ReviewsDtoToDboMapper
import nikitagorbatko.fojin.test.reviews.utils.ReviewsDtoToUiMapper

class ReviewsRepositoryImpl private constructor(
    private val retrofit: RetrofitReviews,
    private val reviewsDao: ReviewsDao
) : ReviewsRepository {
    companion object {
        @JvmStatic
        private var INSTANCE: ReviewsRepository? = null

        fun getInstance(retrofit: RetrofitReviews, reviewsDao: ReviewsDao): ReviewsRepository {
            return if (INSTANCE == null) {
                INSTANCE = ReviewsRepositoryImpl(retrofit, reviewsDao)
                INSTANCE!!
            } else {
                INSTANCE!!
            }
        }
    }

    override suspend fun getReviews(offset: Int): List<ReviewUi> {
        val reviews = retrofit.reviewsApi.getReviews(offset)
        convertAndSaveToDb(reviews)
        return ReviewsDtoToUiMapper.convert(reviews?.results)
    }

    override suspend fun getIntervalReviews(offset: Int, interval: Pair<String, String>): List<ReviewUi> {
        val reviews = retrofit.reviewsApi.getReviewsInterval(offset, "${interval.first}:${interval.second}")
        convertAndSaveToDb(reviews)
        return ReviewsDtoToUiMapper.convert(reviews?.results)
    }

    override suspend fun getReviewsKeyWord(offset: Int, keyWord: String): List<ReviewUi> {
        val reviews = retrofit.reviewsApi.getReviewsByKeyWord(offset, keyWord)
        convertAndSaveToDb(reviews)
        return ReviewsDtoToUiMapper.convert(reviews?.results)
    }

    suspend fun convertAndSaveToDb(reviewsResponseDto: ReviewsResponseDto?) {
        if (reviewsResponseDto?.results?.isNotEmpty() == true) {
            val dboReviews = ReviewsDtoToDboMapper.convert(reviewsResponseDto.results!!)
            reviewsDao.insertReviews(dboReviews)
        }
    }
}