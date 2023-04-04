package nikitagorbatko.fojin.test.reviews.data

import nikitagorbatko.fojin.test.reviews.ui.entities.ReviewUi

interface ReviewsDbRepository {
    suspend fun getReviewsFromDb(): List<ReviewUi>
}