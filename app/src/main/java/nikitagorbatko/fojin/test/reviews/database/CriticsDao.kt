package nikitagorbatko.fojin.test.reviews.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CriticsDao {
    @Query("SELECT * FROM critic_table")
    suspend fun getCritics(): List<CriticDbo>

    @Query("DELETE FROM critic_table")
    suspend fun deleteAllCritics()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCritic(critics: List<CriticDbo>)
}