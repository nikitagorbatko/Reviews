package nikitagorbatko.fojin.test.reviews.utils

import nikitagorbatko.fojin.test.reviews.api.ReviewDto
import nikitagorbatko.fojin.test.reviews.database.CriticDbo
import nikitagorbatko.fojin.test.reviews.ui.entities.CriticUi
import nikitagorbatko.fojin.test.reviews.ui.entities.ReviewUi

object CriticsDboToUiMapper {
    fun convert(dboCritics: List<CriticDbo>?): List<CriticUi> {
        val uiCritics = mutableListOf<CriticUi>()
        dboCritics?.forEach {
            uiCritics.add(
                CriticUi(
                    displayName = it.displayName,
                    sortName = it.sortName,
                    status = it.status,
                    bio = it.bio,
                    seoName = it.seoName,
                    resourceType = it.resourceType,
                    resourceSrc = it.resourceSrc,
                    resourceHeight = it.resourceHeight,
                    resourceWidth = it.resourceWidth,
                    resourceCredit = it.resourceCredit
                )
            )
        }
        return uiCritics
    }
}