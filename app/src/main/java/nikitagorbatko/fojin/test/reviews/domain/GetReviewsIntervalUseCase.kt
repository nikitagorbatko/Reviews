package nikitagorbatko.fojin.test.reviews.domain

import nikitagorbatko.fojin.test.reviews.data.ReviewsRepository

class GetReviewsIntervalUseCase(private val reviewsRepository: ReviewsRepository) {
    suspend fun execute(offset: Int, dateInterval: String) =
        reviewsRepository.getIntervalReviews(offset, dateInterval)
}