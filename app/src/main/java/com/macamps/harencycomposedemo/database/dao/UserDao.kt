package com.softradix.jetpackcomposedemo.database.dao

import androidx.room.*
import com.softradix.jetpackcomposedemo.database.entity.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(item: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(item: List<Playlist>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(item: List<Song>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylistSongCrossRef(item: List<PlaylistSongCrossRef>)

    @Transaction
    @Query("SELECT * FROM user WHERE userId= :userId")
    fun getUserWithPlaylistAndSongs(userId: Int): List<UserWithPlaylistAndSongs>


}