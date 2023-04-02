package nikitagorbatko.fojin.test.reviews.data

import nikitagorbatko.fojin.test.reviews.api.CriticsResponseDto

interface CriticsRepository {
    suspend fun getCritics(): CriticsResponseDto?
}