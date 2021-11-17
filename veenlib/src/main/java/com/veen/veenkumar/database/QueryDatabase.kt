package com.veen.veenkumar.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.veen.veenkumar.database.AddDatabase

@Dao
interface QueryDatabase {
    @Insert
    fun addToCart(addDatabase: AddDatabase)

    @Query("SELECT * FROM addDatabase ORDER BY id DESC")
    fun getalldate(): List<AddDatabase>

    @Insert
    fun addMultipleNotes(vararg addDatabase: AddDatabase)

    @Query("update addDatabase set size = :value where id = :id")
    fun updatenote(value: String?, id: String?)

    // @Delete
    //fun delnote(Int  id)
    // fun delnote(addToCart: AddToCart)

    @Query("DELETE FROM addDatabase WHERE id = :id")
    fun deleteByTitle(id: Int?)
}