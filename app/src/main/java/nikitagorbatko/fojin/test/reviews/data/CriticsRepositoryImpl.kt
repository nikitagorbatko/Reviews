package nikitagorbatko.fojin.test.reviews.data

import android.os.Parcel
import android.os.Parcelable
import nikitagorbatko.fojin.test.reviews.api.CriticsResponseDto
import nikitagorbatko.fojin.test.reviews.api.RetrofitReviews
import nikitagorbatko.fojin.test.reviews.api.ReviewsResponseDto
import nikitagorbatko.fojin.test.reviews.database.CriticsDao
import nikitagorbatko.fojin.test.reviews.database.ReviewsDao
import nikitagorbatko.fojin.test.reviews.ui.entities.CriticUi
import nikitagorbatko.fojin.test.reviews.utils.CriticsDtoToDboMapper
import nikitagorbatko.fojin.test.reviews.utils.CriticsDtoToUiMapper
import nikitagorbatko.fojin.test.reviews.utils.ReviewsDtoToDboMapper
import nikitagorbatko.fojin.test.reviews.utils.ReviewsDtoToUiMapper


class CriticsRepositoryImpl private constructor(
    private val retrofit: RetrofitReviews,
    private val criticsDao: CriticsDao
) : CriticsRepository {
    companion object {
        @JvmStatic
        private var INSTANCE: CriticsRepository? = null

        fun getInstance(retrofit: RetrofitReviews, criticsDao: CriticsDao): CriticsRepository {
            return if (INSTANCE == null) {
                INSTANCE = CriticsRepositoryImpl(retrofit, criticsDao)
                INSTANCE!!
            } else {
                INSTANCE!!
            }
        }
    }

    override suspend fun getCritics(): List<CriticUi> {
        val critics = retrofit.reviewsApi.getCritics()
        convertAndSaveToDb(critics)
        return CriticsDtoToUiMapper.convert(critics?.results)
    }

    suspend fun convertAndSaveToDb(criticsResponseDto: CriticsResponseDto?) {
        if (criticsResponseDto?.results?.isNotEmpty() == true) {
            val dboReviews = CriticsDtoToDboMapper.convert(criticsResponseDto.results)
            criticsDao.insertCritic(dboReviews)
        }
    }
}