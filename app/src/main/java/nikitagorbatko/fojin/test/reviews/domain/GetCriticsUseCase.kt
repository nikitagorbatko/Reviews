package nikitagorbatko.fojin.test.reviews.domain

import nikitagorbatko.fojin.test.reviews.data.CriticsRepository

class GetCriticsUseCase(private val criticsRepository: CriticsRepository) {
    suspend fun execute() = criticsRepository.getCritics()
}