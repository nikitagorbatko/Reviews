package nikitagorbatko.fojin.test.reviews.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReviewsDao {
    @Query("SELECT * FROM review_table")
    suspend fun getReviews(): List<ReviewDbo>

    @Query("DELETE FROM review_table")
    suspend fun deleteAllReviews()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviews(reviews: List<ReviewDbo>)
}