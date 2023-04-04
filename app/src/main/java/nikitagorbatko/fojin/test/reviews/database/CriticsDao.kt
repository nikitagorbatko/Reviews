package nikitagorbatko.fojin.test.reviews.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CriticsDao {
    @Query("SELECT * FROM critic_table")
    fun getCritics(): List<CriticDbo>

    @Query("DELETE FROM critic_table")
    fun deleteAllCritics()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCritic(vararg critic: CriticDbo)
}