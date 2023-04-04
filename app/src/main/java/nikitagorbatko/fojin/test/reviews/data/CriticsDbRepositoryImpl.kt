package nikitagorbatko.fojin.test.reviews.data

import nikitagorbatko.fojin.test.reviews.api.RetrofitReviews
import nikitagorbatko.fojin.test.reviews.database.CriticsDao
import nikitagorbatko.fojin.test.reviews.database.ReviewsDao
import nikitagorbatko.fojin.test.reviews.ui.entities.CriticUi
import nikitagorbatko.fojin.test.reviews.utils.CriticsDboToUiMapper
import nikitagorbatko.fojin.test.reviews.utils.CriticsDtoToDboMapper

class CriticsDbRepositoryImpl private constructor(private val criticsDao: CriticsDao): CriticsDbRepository {
    companion object {
        @JvmStatic
        private var INSTANCE: CriticsDbRepository? = null

        fun getInstance(criticsDao: CriticsDao): CriticsDbRepository {
            return if (INSTANCE == null) {
                INSTANCE = CriticsDbRepositoryImpl(criticsDao)
                INSTANCE!!
            } else {
                INSTANCE!!
            }
        }
    }

    override suspend fun getCriticsFromDb(): List<CriticUi> {
        val critics = criticsDao.getCritics()
        return CriticsDboToUiMapper.convert(critics)
    }
}