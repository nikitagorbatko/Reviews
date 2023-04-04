package nikitagorbatko.fojin.test.reviews.domain

import nikitagorbatko.fojin.test.reviews.data.ReviewsByCriticRepository
import nikitagorbatko.fojin.test.reviews.data.ReviewsRepository
import nikitagorbatko.fojin.test.reviews.ui.entities.ReviewUi

class GetReviewsByCriticUseCase(private val reviewsByCriticRepository: ReviewsByCriticRepository) {
    suspend fun execute(offset: Int, reviewer: String): List<ReviewUi> {
        return reviewsByCriticRepository.getReviewsByCritic(offset, reviewer)
    }
}