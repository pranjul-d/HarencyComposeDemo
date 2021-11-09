package com.softradix.jetpackcomposedemo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.softradix.jetpackcomposedemo.database.dao.UserDao
import com.softradix.jetpackcomposedemo.database.entity.Playlist
import com.softradix.jetpackcomposedemo.database.entity.PlaylistSongCrossRef
import com.softradix.jetpackcomposedemo.database.entity.Song
import com.softradix.jetpackcomposedemo.database.entity.User

@Database(
    entities = [User::class, Playlist::class, Song::class, PlaylistSongCrossRef::class],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    /***  below object doesn't need be create in case of hilt DI  ***/
//    companion object {
//        @Volatile
//        private var INSTANCE: Database? = null
//
//        fun getInstance(): Database {
//            val instance = INSTANCE
//
//            if (instance != null) {
//                return instance
//            }
//            synchronized(this) {
//                val db = Room.databaseBuilder(
//                    PostApplication.getContext(),
//                    Database::class.java, "user.db"
//                )
//                    .allowMainThreadQueries().build()
//                INSTANCE = db
//                return db
//            }
//
//        }
//
//    }


}