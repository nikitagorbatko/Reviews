package nikitagorbatko.fojin.test.reviews.domain

import nikitagorbatko.fojin.test.reviews.data.ReviewsDbRepository
import nikitagorbatko.fojin.test.reviews.database.ReviewsDao

class GetReviewsDbUseCase(private val reviewsDbRepository: ReviewsDbRepository) {
    suspend fun execute() = reviewsDbRepository.getReviewsFromDb()
}