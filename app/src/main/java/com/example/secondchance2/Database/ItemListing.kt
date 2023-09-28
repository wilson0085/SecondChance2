package com.example.secondchance2.Database

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "ItemListing",
    foreignKeys = [ForeignKey(
        entity = User::class,
        childColumns = ["UserID"],
        parentColumns = ["UserID"]
    )])
data class ItemListing(
    @PrimaryKey val ItemID: String,

    @ColumnInfo(name = "ItemName")
    val itemName: String,

    @ColumnInfo(name = "Description")
    val description: String,

    @ColumnInfo(name = "PricingType")
    val pricingType: String, // Exp: Paid, Free

    @ColumnInfo(name = "Price")
    val price: Float, // Assuming NUMBER(6,2) maps to a floating-point number with two decimal places

    @ColumnInfo(name = "ItemPhoto")
    val itemPhoto: Bitmap,

    @ColumnInfo(name = "UserID")
    val UserID: String
)
