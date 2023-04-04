package nikitagorbatko.fojin.test.reviews.domain

import nikitagorbatko.fojin.test.reviews.api.ReviewsResponseDto
import nikitagorbatko.fojin.test.reviews.data.ReviewsRepository
import nikitagorbatko.fojin.test.reviews.ui.entities.ReviewUi

class GetReviewsUseCase(private val reviewsRepository: ReviewsRepository) {
    suspend fun execute(offset: Int, interval: Pair<String, String>? = null, keyWord: String? = null): List<ReviewUi> {
        if (interval != null) {
            return reviewsRepository.getIntervalReviews(offset, interval)
        }
        if (keyWord != null) {
            return reviewsRepository.getReviewsKeyWord(offset, keyWord)
        }
        return reviewsRepository.getReviews(offset)
    }
}