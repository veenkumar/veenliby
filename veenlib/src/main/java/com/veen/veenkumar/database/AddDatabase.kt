package com.veen.veenkumar.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class AddDatabase(
    val image: String,
    val name: String,
    val discription: String,
    val amount: String,
    val size: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}