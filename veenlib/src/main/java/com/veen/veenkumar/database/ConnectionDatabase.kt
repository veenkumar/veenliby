package com.veen.veenkumar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [AddDatabase::class],
    version = 1
)
abstract class ConnectionDatabase : RoomDatabase() {
//    abstract fun getNoteDao(): DatabaseDao

    companion object {
        @Volatile
        private var instance: ConnectionDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ConnectionDatabase::class.java,
            "mobolousdatabase"
        ).allowMainThreadQueries().build()
    }
}
