package nikitagorbatko.fojin.test.reviews.data

import nikitagorbatko.fojin.test.reviews.ui.entities.CriticUi

interface CriticsDbRepository {
    suspend fun getCriticsFromDb(): List<CriticUi>
}