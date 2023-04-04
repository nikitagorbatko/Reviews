package nikitagorbatko.fojin.test.reviews

import android.app.Application
import nikitagorbatko.fojin.test.reviews.database.ReviewsDatabase

class App: Application() {
    //lateinit var database: ReviewsDatabase
    override fun onCreate() {
        super.onCreate()
        //database = ReviewsDatabase.getDatabase(this)
    }
}