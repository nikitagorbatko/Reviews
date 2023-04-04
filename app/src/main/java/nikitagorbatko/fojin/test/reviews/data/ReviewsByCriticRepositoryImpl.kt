package nikitagorbatko.fojin.test.reviews.data

import nikitagorbatko.fojin.test.reviews.api.RetrofitReviews
import nikitagorbatko.fojin.test.reviews.api.ReviewsResponseDto
import nikitagorbatko.fojin.test.reviews.database.ReviewsDao
import nikitagorbatko.fojin.test.reviews.ui.entities.ReviewUi
import nikitagorbatko.fojin.test.reviews.utils.ReviewsDtoToDboMapper
import nikitagorbatko.fojin.test.reviews.utils.ReviewsDtoToUiMapper

class ReviewsByCriticRepositoryImpl private constructor(private val retrofit: RetrofitReviews, private val reviewsDao: ReviewsDao): ReviewsByCriticRepository {
    companion object {
        @JvmStatic
        private var INSTANCE: ReviewsByCriticRepository? = null

        fun getInstance(retrofit: RetrofitReviews, reviewsDao: ReviewsDao): ReviewsByCriticRepository {
            return if (INSTANCE == null) {
                INSTANCE = ReviewsByCriticRepositoryImpl(retrofit, reviewsDao)
                INSTANCE!!
            } else {
                INSTANCE!!
            }
        }
    }


    override suspend fun getReviewsByCritic(offset: Int, reviewer: String): List<ReviewUi> {
        val reviews = retrofit.reviewsApi.getReviewsByCritic(offset, reviewer)
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