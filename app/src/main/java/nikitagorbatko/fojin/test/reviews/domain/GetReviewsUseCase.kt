package nikitagorbatko.fojin.test.reviews.domain

import nikitagorbatko.fojin.test.reviews.data.ReviewsRepository

class GetReviewsUseCase(private val reviewsRepository: ReviewsRepository) {
    suspend fun execute(offset: Int) = reviewsRepository.getReviews(offset)
}