package com.example.secondchance2.Database

import android.graphics.Bitmap

data class UserMatchedList(
    // User Data
    val UserID: String,
    val UserName: String,
    val UserPhoto: Bitmap,
    val UserItemID: String,
    val UserItemName: String,
    val UserItemPhoto: Bitmap,
    val UserItemPriceType: String,
    // Matched User Data
    val MatchedUserID: String,
    val MatchUserName: String,
    val MatchedUserPhoto: Bitmap,
    val MatchedUserItemID: String,
    val MatchedUserItemName: String,
    val MatchedUserItemPhoto: Bitmap,
    val MatchedUserItemPriceType: String
)
