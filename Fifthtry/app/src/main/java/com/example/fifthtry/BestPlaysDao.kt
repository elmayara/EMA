package com.example.fifthtry
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Delete


@Dao
interface BestPlaysDao {

    @Query("SELECT * FROM BPlays where id=:id")
    fun getHitByID(id: Long): BestPlays?

    @Query("SELECT * FROM BPlays")
    fun getAllBPlays(): LiveData<List<BestPlays>>

    @Insert
    fun insert(BestPlays: BestPlays) : Long
    //it retrieves the id of the song

}