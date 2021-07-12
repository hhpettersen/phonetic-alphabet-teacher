package com.app.phoneticalphabet.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.phoneticalphabet.models.HighScore

@Database(
    entities = [HighScore::class], // Tell the database the entries will hold data of this type
    version = 1
)

abstract class Database : RoomDatabase() {
    abstract fun getDao(): DAO
}