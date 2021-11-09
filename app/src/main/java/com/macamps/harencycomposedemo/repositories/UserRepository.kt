package com.macamps.harencycomposedemo.repositories

import com.softradix.jetpackcomposedemo.database.dao.UserDao
import com.softradix.jetpackcomposedemo.database.entity.*
import javax.inject.Inject

class UserRepository @Inject constructor(private var userDao: UserDao) {
    suspend fun addUser(item:List<User>){
        userDao.insertUser(item)
    }

    suspend fun addPlaylist(item:List<Playlist>){
        userDao.insertPlaylist(item)
    }

    suspend fun addSong(item:List<Song>){
        userDao.insertSong(item)
    }

    suspend fun addPlaylistSongCrossRef(item:List<PlaylistSongCrossRef>){
        userDao.insertPlaylistSongCrossRef(item)
    }

    suspend fun getUserWithPlaylistAndSongs(userId:Int): List<UserWithPlaylistAndSongs>{
       return userDao.getUserWithPlaylistAndSongs(userId)
    }

}