package nikitagorbatko.fojin.test.reviews.domain

import nikitagorbatko.fojin.test.reviews.data.ReviewsDbRepository
import nikitagorbatko.fojin.test.reviews.ui.entities.ReviewUi

class GetReviewsDbUseCase(private val reviewsDbRepository: ReviewsDbRepository) {
    suspend fun execute(
        interval: Pair<String, String>? = null,
        keyWord: String? = null
    ): List<ReviewUi> {
        if (interval != null) {
            return reviewsDbRepository.getReviewsFromDb(interval = interval)
        }
        if (keyWord != null) {
            return reviewsDbRepository.getReviewsFromDb(keyWord = keyWord)
        }
        return reviewsDbRepository.getReviewsFromDb()
    }
}

