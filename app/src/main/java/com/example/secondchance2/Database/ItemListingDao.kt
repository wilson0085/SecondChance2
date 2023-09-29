package com.example.secondchance2.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ItemListingDao {
    @Query("SELECT * FROM ItemListing WHERE ItemID = :itemId")
    fun getThisItemListing(itemId: String): ItemListing

    @Query("SELECT * FROM ItemListing WHERE PricingType = 'Free' AND UserID != :userId")
    fun getAllFreeItemListing(userId: String): List<ItemListing>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(itemListing: ItemListing)

    @Query("SELECT MAX(ItemID) FROM ItemListing")
    fun getLastItemID(): String
}