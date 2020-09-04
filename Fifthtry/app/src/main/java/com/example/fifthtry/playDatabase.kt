package com.example.fifthtry

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BestPlays::class), version = 1, exportSchema = false)
public abstract class playDatabase: RoomDatabase() {


    abstract fun BestPlaysDao(): BestPlaysDao

    companion object {
        private var instance: playDatabase? = null

        fun getDatabase(ctx: Context) : playDatabase {
            var tmpInstance = instance
            if(tmpInstance == null) {
                tmpInstance = Room.databaseBuilder(
                    ctx.applicationContext,
                    playDatabase::class.java,
                    "studentDatabase"
                ).build()
                instance = tmpInstance
            }
            return tmpInstance
        }
    }
}

