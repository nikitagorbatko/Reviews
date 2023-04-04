package nikitagorbatko.fojin.test.reviews.data

import nikitagorbatko.fojin.test.reviews.api.ReviewsResponseDto
import nikitagorbatko.fojin.test.reviews.ui.entities.ReviewUi

interface ReviewsRepository {
    suspend fun getReviews(offset: Int): List<ReviewUi>

    suspend fun getIntervalReviews(offset: Int, interval: Pair<String, String>): List<ReviewUi>

    suspend fun getReviewsKeyWord(offset: Int, keyWord: String): List<ReviewUi>
}