package nikitagorbatko.fojin.test.reviews.domain

import nikitagorbatko.fojin.test.reviews.data.CriticsDbRepository
import nikitagorbatko.fojin.test.reviews.data.ReviewsDbRepository
import nikitagorbatko.fojin.test.reviews.ui.entities.CriticUi
import nikitagorbatko.fojin.test.reviews.ui.entities.ReviewUi

class GetCriticsDbUseCase(private val criticsDbRepository: CriticsDbRepository) {
    suspend fun execute(): List<CriticUi> {
        return criticsDbRepository.getCriticsFromDb()
    }
}