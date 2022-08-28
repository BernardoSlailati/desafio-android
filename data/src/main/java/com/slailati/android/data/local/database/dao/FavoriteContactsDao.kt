package com.slailati.android.data.local.database.dao

import androidx.room.*
import com.slailati.android.data.local.model.UserEntity

@Dao
interface FavoriteContactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity): Long

    @Delete
    fun delete(user: UserEntity)

    @Query("DELETE FROM favorite_contacts WHERE user_id = :userId")
    fun deleteById(userId: Int)

    @Query("SELECT * FROM favorite_contacts")
    fun getAll(): List<UserEntity>

}