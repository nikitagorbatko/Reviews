package nikitagorbatko.fojin.test.reviews.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReviewsDao {
    @Query("SELECT * FROM review_table")
    suspend fun getReviews(): List<ReviewDbo>

    @Query("SELECT * FROM review_table WHERE publication_date BETWEEN :startDate AND :endDate")
    suspend fun getReviewsInterval(startDate: String, endDate: String): List<ReviewDbo>

    @Query("SELECT * FROM review_table WHERE byline LIKE :reviewer")
    suspend fun getReviewsByCritic(reviewer: String): List<ReviewDbo>

    @Query(
        "SELECT * FROM review_table WHERE " +
                "display_title LIKE '%' || :keyWord || '%' OR " +
                "byline LIKE '%' || :keyWord || '%' OR " +
                "headline LIKE '%' || :keyWord || '%' OR " +
                "summary_short LIKE '%' || :keyWord || '%'"
    )
    suspend fun getReviewsKeyWord(keyWord: String): List<ReviewDbo>

    @Query("DELETE FROM review_table")
    suspend fun deleteAllReviews()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviews(reviews: List<ReviewDbo>)
}