package nikitagorbatko.fojin.test.reviews.data

import nikitagorbatko.fojin.test.reviews.ui.entities.ReviewUi

interface ReviewsByCriticDbRepository {
    suspend fun getReviewsByCriticFromDb(reviewer: String): List<ReviewUi>
}
