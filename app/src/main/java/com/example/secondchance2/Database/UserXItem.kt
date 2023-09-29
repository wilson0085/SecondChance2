package com.example.secondchance2.Database

import android.graphics.Bitmap

data class UserXItem(
    // Item Data
    val ItemID: String,
    val ItemName: String,
    val PricingType: String,
    val Price: Float,
    val ItemPhoto: Bitmap,
    // User Data
    val UserID: String,
    val ProfilePhoto: Bitmap,
    val StarRating: Float
)
