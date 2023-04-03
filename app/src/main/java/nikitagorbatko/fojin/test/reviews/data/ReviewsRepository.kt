package nikitagorbatko.fojin.test.reviews.data

import nikitagorbatko.fojin.test.reviews.api.ReviewsResponseDto

interface ReviewsRepository {
    suspend fun getReviews(offset: Int): ReviewsResponseDto?

    suspend fun getIntervalReviews(offset: Int, interval: String): ReviewsResponseDto?

    suspend fun getReviewsKeyWord(offset: Int, keyWord: String): ReviewsResponseDto?
}