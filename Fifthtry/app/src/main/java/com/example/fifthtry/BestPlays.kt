package com.example.fifthtry
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="BPlays")
data class BestPlays(@PrimaryKey(autoGenerate = true) var id: Long, var name: String,var date: String, var time: String) {
}
