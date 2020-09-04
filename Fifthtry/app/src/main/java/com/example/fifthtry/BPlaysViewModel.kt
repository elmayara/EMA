package com.example.fifthtry
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class BPlaysViewModel(app: Application): AndroidViewModel(app) {

    var db = playDatabase.getDatabase(app)
    var BPlays: LiveData<List<BestPlays>>

    init {
        BPlays = db.BestPlaysDao().getAllBPlays()
    }

    fun insert(BestPlays: BestPlays) {
        db.BestPlaysDao().insert(BestPlays)
    }

    fun getAllBPlays(): LiveData<List<BestPlays>> {
        return BPlays
    }
}