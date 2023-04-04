package nikitagorbatko.fojin.test.reviews.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [ReviewDbo::class, CriticDbo::class]
)
abstract class ReviewsDatabase : RoomDatabase() {
    abstract fun getReviewsDao(): ReviewsDao
    abstract fun getCriticsDao(): CriticsDao

    companion object {
        @Volatile
        private var INSTANCE: ReviewsDatabase? = null

        fun getDatabase(context: Context): ReviewsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReviewsDatabase::class.java,
                    "reviews_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

