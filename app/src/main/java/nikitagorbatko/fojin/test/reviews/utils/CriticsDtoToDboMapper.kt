package nikitagorbatko.fojin.test.reviews.utils

import nikitagorbatko.fojin.test.reviews.api.CriticDto
import nikitagorbatko.fojin.test.reviews.database.CriticDbo

object CriticsDtoToDboMapper {
    fun convert(dtoCritics: List<CriticDto>): List<CriticDbo> {
        val dboCritics = mutableListOf<CriticDbo>()
        dtoCritics.forEach {
            dboCritics.add(
                CriticDbo(
                    displayName = it.displayName!!,
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
        return dboCritics
    }
}