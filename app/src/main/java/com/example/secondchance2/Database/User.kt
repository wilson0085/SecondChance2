package com.example.secondchance2.Database


import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey val UserID: String,

    @ColumnInfo(name = "UserName")
    val userName: String,

    @ColumnInfo(name = "JoinedYear")
    val joinedYear: Int,

    @ColumnInfo(name = "EmailVerification")
    val emailVerification: String,

    @ColumnInfo(name = "ADDRESS")
    val address: String,

    @ColumnInfo(name = "StarRating")
    val starRating: Float, // Assuming NUMBER(2,1) maps to a floating-point number with one decimal place

    @ColumnInfo(name = "ProfilePhoto")
    val profilePhoto: Bitmap
)
