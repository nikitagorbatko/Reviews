package nikitagorbatko.fojin.test.reviews.data

import nikitagorbatko.fojin.test.reviews.ui.entities.ReviewUi


interface ReviewsByCriticRepository {
    suspend fun getReviewsByCritic(offset: Int, reviewer: String): List<ReviewUi>
}