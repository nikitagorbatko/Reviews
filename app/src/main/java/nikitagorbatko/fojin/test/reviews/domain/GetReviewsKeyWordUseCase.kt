package nikitagorbatko.fojin.test.reviews.domain

import nikitagorbatko.fojin.test.reviews.data.ReviewsRepository

class GetReviewsKeyWordUseCase(private val reviewsRepository: ReviewsRepository) {
    suspend fun execute(offset: Int, keyWord: String) =
        reviewsRepository.getReviewsKeyWord(offset, keyWord)
}