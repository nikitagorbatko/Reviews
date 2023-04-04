package nikitagorbatko.fojin.test.reviews.domain

import nikitagorbatko.fojin.test.reviews.data.ReviewsByCriticDbRepository

class GetReviewsByCriticDbUseCase(private val reviewsByCriticDbRepository: ReviewsByCriticDbRepository) {
    suspend fun execute(reviewer: String) =
        reviewsByCriticDbRepository.getReviewsByCriticFromDb(reviewer)
}