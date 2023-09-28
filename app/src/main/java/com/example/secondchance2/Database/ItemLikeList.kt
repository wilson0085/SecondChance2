package com.example.secondchance2.Database


import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "ItemLikeList",
    primaryKeys = ["UserID", "ItemID"],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["UserID"],
            childColumns = ["UserID"],
            onDelete = ForeignKey.CASCADE // Specify the behavior on user deletion
        ),
        ForeignKey(
            entity = ItemListing::class,
            parentColumns = ["ItemID"],
            childColumns = ["ItemID"],
            onDelete = ForeignKey.CASCADE // Specify the behavior on item listing deletion
        )
    ]
)
data class ItemLikeList(
    @ColumnInfo(name = "UserID") val UserID: String,
    @ColumnInfo(name = "ItemID") val ItemID: String,
    @ColumnInfo(name = "OwnerID") val OwnerID: String,
    @ColumnInfo(name = "LikedDate") val LikedDate: String,
    @ColumnInfo(name = "LikedTime") val LikedTime: String,
)

