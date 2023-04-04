package nikitagorbatko.fojin.test.reviews.utils

import nikitagorbatko.fojin.test.reviews.api.CriticDto
import nikitagorbatko.fojin.test.reviews.database.ReviewDbo
import nikitagorbatko.fojin.test.reviews.ui.entities.CriticUi
import nikitagorbatko.fojin.test.reviews.ui.entities.ReviewUi

object CriticsDtoToUiMapper {
    fun convert(dtoCritics: List<CriticDto>?): List<CriticUi> {
        val uiCritics = mutableListOf<CriticUi>()
        dtoCritics?.forEach {
            uiCritics.add(
                CriticUi(
                    displayName = it.displayName,
                    sortName = it.sortName,
                    status = it.status,
                    bio = it.bio,
                    seoName = it.seoName,
                    resourceType = it.multimedia?.resource?.type,
                    resourceSrc = it.multimedia?.resource?.src,
                    resourceHeight = it.multimedia?.resource?.height,
                    resourceWidth = it.multimedia?.resource?.width,
                    resourceCredit = it.multimedia?.resource?.credit
                )
            )
        }
        return uiCritics
    }
}