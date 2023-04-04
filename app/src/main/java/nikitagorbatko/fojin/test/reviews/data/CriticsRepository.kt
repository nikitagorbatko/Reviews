package nikitagorbatko.fojin.test.reviews.data

import nikitagorbatko.fojin.test.reviews.api.CriticsResponseDto
import nikitagorbatko.fojin.test.reviews.ui.entities.CriticUi

interface CriticsRepository {
    suspend fun getCritics(): List<CriticUi>
}