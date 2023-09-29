package com.example.secondchance2.Database

import androidx.room.*

@Dao
interface ItemLikeListDao {
    //@Query("SELECT A.UserID, B.ItemID, B.UserID, A.ItemID FROM ItemLikeList A INNER JOIN ItemLikeList B ON A.OwnerID = B.UserID AND A.UserID = :userId")
    @Query("SELECT * FROM ItemLikeList WHERE UserID = :userId ")
    fun matchList(userId: String): List<ItemLikeList>

//    data class ItemLikeList(
//        @ColumnInfo(name = "UserID") val UserID: String,
//        @ColumnInfo(name = "ItemID") val ItemID: String,
//        @ColumnInfo(name = "OwnerID") val OwnerID: String,
//        @ColumnInfo(name = "LikedDate") val LikedDate: String,
//        @ColumnInfo(name = "LikedTime") val LikedTime: String,
//    )

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(itemLikeList: ItemLikeList)

    @Query("SELECT A.UserID AS UserID, C.UserName AS UserName, C.ProfilePhoto AS UserPhoto, B.ItemID AS UserItemID, E.ItemName AS UserItemName, E.ItemPhoto AS UserItemPhoto, E.PricingType as UserItemPriceType, B.UserID AS MatchedUserID, D.UserName AS MatchUserName, D.ProfilePhoto AS MatchedUserPhoto, A.ItemID AS MatchedUserItemID, F.ItemName AS MatchedUserItemName, F.ItemPhoto AS MatchedUserItemPhoto, F.PricingType AS MatchedUserItemPriceType FROM ItemLikeList A INNER JOIN ItemLikeList B ON A.OwnerID = B.UserID INNER JOIN User C ON C.UserID = A.UserID INNER JOIN User D ON B.UserID = D.UserID INNER JOIN ItemListing E ON B.ItemID = E.ItemID INNER JOIN ItemListing F ON F.ItemID = A.ItemID  AND A.UserID = :userId;")
    fun getMatchedListForUser(userId: String): List<UserMatchedList>

}