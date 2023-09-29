package com.example.secondchance2

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceControl.Transaction
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.secondchance2.Database.AppDatabase
import com.example.secondchance2.Database.ItemLikeList
import com.example.secondchance2.Database.ItemListing
import com.example.secondchance2.Database.User
import com.example.secondchance2.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var appDb : AppDatabase
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create the database
        appDb = AppDatabase.getDatabase(this)
        Toast.makeText(this, "Database Created", Toast.LENGTH_LONG).show()
        insertDummyData()

        insertDummyDataItemListing()
        // insert ItemLikedList
        insertDummyItemLikeList()
        // Toast a message to indicate database successfully created

        replaceFragment(ExploreFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_explore -> replaceFragment(ExploreFragment())
                R.id.navigation_swipe -> replaceFragment(SwipeFragment())
                R.id.navigation_post -> replaceFragment(PostFragment())
                R.id.navigation_chat -> replaceFragment(ChatFragment())
                R.id.navigation_you -> replaceFragment(YouFragment())

                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            // If the Drawable is already a BitmapDrawable, return its Bitmap directly
            return drawable.bitmap
        }

        // Otherwise, create a new Bitmap and draw the Drawable on it
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }

    fun insertDummyData(){
        // Insert user 1
        var userId = "1000"
        var userName: String = "Luna"
        var joinedYear: Int = 2021
        var emailVerification: String = "Y"
        var address: String = "Puchong, Malaysia"
        var starRating: Float = 4.5F
        var drawable = resources.getDrawable(R.drawable.woman_profile_fotor, null) // Replace with your Drawable
        var bitmap = drawableToBitmap(drawable)
        var profilePhoto: Bitmap = bitmap

        var thisUser1 = User(userId, userName, joinedYear, emailVerification, address, starRating, profilePhoto)
        lifecycleScope.launch(Dispatchers.IO) {
            appDb.userDao().insert(thisUser1)
        }

        // Insert User2
        userId = "1001"
        userName = "Tan Hua Hua"
        joinedYear = 2020
        emailVerification = "Y"
        address = "Petaling Jaya, Malaysia"
        starRating = 5F
        drawable = resources.getDrawable(R.drawable.blond_female_girl_fotor_2023092616279, null) // Replace with your Drawable
        bitmap = drawableToBitmap(drawable)
        profilePhoto = bitmap

        var thisUser2 = User(userId, userName, joinedYear, emailVerification, address, starRating, profilePhoto)
        lifecycleScope.launch(Dispatchers.IO) {
            appDb.userDao().insert(thisUser2)
        }

        // Insert User3
        userId = "1002"
        userName = "Tan Ah Kau"
        joinedYear = 2019
        emailVerification = "Y"
        address = "Penang, Malaysia"
        starRating = 5F
        drawable = resources.getDrawable(R.drawable.man2_profile_fotor, null) // Replace with your Drawable
        bitmap = drawableToBitmap(drawable)
        profilePhoto = bitmap

        var thisUser3 = User(userId, userName, joinedYear, emailVerification, address, starRating, profilePhoto)
        lifecycleScope.launch(Dispatchers.IO) {
            appDb.userDao().insert(thisUser3)
        }

        // Insert User4
        userId = "1003"
        userName = "Abu Bakar"
        joinedYear = 2018
        emailVerification = "Y"
        address = "Setapak, Malaysia"
        starRating = 5F
        drawable = resources.getDrawable(R.drawable.man_profile_fotor, null) // Replace with your Drawable
        bitmap = drawableToBitmap(drawable)
        profilePhoto = bitmap

        var thisUser4 = User(userId, userName, joinedYear, emailVerification, address, starRating, profilePhoto)
        lifecycleScope.launch(Dispatchers.IO) {
            appDb.userDao().insert(thisUser4)
        }

        Toast.makeText(this, "Records Created", Toast.LENGTH_SHORT).show()
    }

    fun insertDummyDataItemListing(){
        // Item 1
        var itemID = "1000"
        var itemName = "Acer Aspire 5 Laptop"
        var itemDescription = "The Acer Aspire 5 is an affordable and lightweight laptop, offering a variety of processor options and a Full HD display for everyday computing tasks."
        var pricingType = "Free"
        var price = 0.00F
        var drawable = resources.getDrawable(R.drawable.acer_fotor, null)
        var bitmap = drawableToBitmap(drawable)
        var itemPhoto = bitmap
        var userID = "1000"

        var item1 = ItemListing(itemID, itemName, itemDescription, pricingType, price, itemPhoto, userID)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemListingDao().insert(item1)
        }

        // Item 2
        itemID = "1001"
        itemName = "Camping Storage Box"
        itemDescription = "A camping storage box is an essential outdoor accessory that provides a convenient and organized way to store camping gear, equipment, and supplies. These boxes are typically designed to be rugged, waterproof, and easy to transport, making them ideal for keeping your camping essentials secure and easily accessible during outdoor adventures."
        pricingType = "Free"
        price = 0.00F
        drawable = resources.getDrawable(R.drawable.camping_storage_box_fotor, null)
        bitmap = drawableToBitmap(drawable)
        itemPhoto = bitmap
        userID = "1000"

        var item2 = ItemListing(itemID, itemName, itemDescription, pricingType, price, itemPhoto, userID)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemListingDao().insert(item2)
        }

        // Item 3
        itemID = "1002"
        itemName = "X Brand Desk Lamp"
        itemDescription = "X brand desk lamp is a small, portable lighting fixture typically designed for use on a desk or workspace. It provides focused illumination for tasks such as reading, writing, or working on a computer. "
        pricingType = "Free"
        price = 0.00F
        drawable = resources.getDrawable(R.drawable.desk_lamp_fotor, null)
        bitmap = drawableToBitmap(drawable)
        itemPhoto = bitmap
        userID = "1000"

        var item3 = ItemListing(itemID, itemName, itemDescription, pricingType, price, itemPhoto, userID)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemListingDao().insert(item3)
        }

        // Item 4
        itemID = "1003"
        itemName = "Apple iPad Air 5"
        itemDescription = "The iPad Air 5 featured a sleek design with a 10.9-inch Liquid Retina display, powered by the M1 chip for high-performance computing. It supported the Apple Pencil and Magic Keyboard, making it a versatile and powerful tablet for various tasks."
        pricingType = "Free"
        price = 0.00F
        drawable = resources.getDrawable(R.drawable.ipad_air_fotor, null)
        bitmap = drawableToBitmap(drawable)
        itemPhoto = bitmap
        userID = "1000"

        var item4 = ItemListing(itemID, itemName, itemDescription, pricingType, price, itemPhoto, userID)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemListingDao().insert(item4)
        }

        // Item 5
        itemID = "1004"
        itemName = "JBL True Wireless Earbuds"
        itemDescription = "JBL true wireless earbuds are compact, cord-free audio devices designed for a wireless and immersive listening experience. They typically offer high-quality sound, ergonomic design, and features like touch controls, noise cancellation, and long battery life for music enthusiasts and on-the-go users."
        pricingType = "Free"
        price = 0.00F
        drawable = resources.getDrawable(R.drawable.jbl_fotor, null)
        bitmap = drawableToBitmap(drawable)
        itemPhoto = bitmap
        userID = "1001"

        var item5 = ItemListing(itemID, itemName, itemDescription, pricingType, price, itemPhoto, userID)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemListingDao().insert(item5)
        }

        // Item 6
        itemID = "1005"
        itemName = "MSI RGB Keyboard"
        itemDescription = "An MSI keyboard with RGB lighting offers customizable, vibrant backlighting to enhance the gaming experience. It allows users to personalize lighting effects and colors for a visually stunning and immersive gameplay atmosphere."
        pricingType = "Free"
        price = 0.00F
        drawable = resources.getDrawable(R.drawable.keyboard_msi_fotor, null)
        bitmap = drawableToBitmap(drawable)
        itemPhoto = bitmap
        userID = "1001"

        var item6 = ItemListing(itemID, itemName, itemDescription, pricingType, price, itemPhoto, userID)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemListingDao().insert(item6)
        }

        // Item 7
        itemID = "1006"
        itemName = "Apple MacBook Air"
        itemDescription = "The M1 MacBook Air is a lightweight laptop powered by Apple's M1 chip, delivering exceptional performance and energy efficiency. It features a fanless design and a Retina display, making it a powerful and silent option for everyday computing tasks."
        pricingType = "Paid"
        price = 3000.00F
        drawable = resources.getDrawable(R.drawable.macbook_fotor, null)
        bitmap = drawableToBitmap(drawable)
        itemPhoto = bitmap
        userID = "1001"

        var item7 = ItemListing(itemID, itemName, itemDescription, pricingType, price, itemPhoto, userID)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemListingDao().insert(item7)
        }

        // Item 8
        itemID = "1007"
        itemName = "Marshall Portable Speaker"
        itemDescription = "Marshall portable speakers are renowned for their iconic design and robust sound quality, providing a distinctive blend of style and powerful audio for music enthusiasts. They often feature Bluetooth connectivity and a portable form factor, making them ideal for on-the-go music playback."
        pricingType = "Free"
        price = 0.00F
        drawable = resources.getDrawable(R.drawable.marshall_fotor, null)
        bitmap = drawableToBitmap(drawable)
        itemPhoto = bitmap
        userID = "1001"

        var item8 = ItemListing(itemID, itemName, itemDescription, pricingType, price, itemPhoto, userID)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemListingDao().insert(item8)
        }

        // Item 9
        itemID = "1008"
        itemName = "Midea Robot Vacuum Cleaner"
        itemDescription = "The Midea robot vacuum cleaner is a smart and automated cleaning device equipped with intelligent navigation and cleaning modes, designed to efficiently clean floors and carpets while requiring minimal manual intervention. It often includes features like app control and voice assistant compatibility for added convenience."
        pricingType = "Free"
        price = 0.00F
        drawable = resources.getDrawable(R.drawable.midea_robot_vacuum_fotor, null)
        bitmap = drawableToBitmap(drawable)
        itemPhoto = bitmap
        userID = "1002"

        var item9 = ItemListing(itemID, itemName, itemDescription, pricingType, price, itemPhoto, userID)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemListingDao().insert(item9)
        }

        // Item 10
        itemID = "1009"
        itemName = "Oneplus 11 5G"
        itemDescription = "The OnePlus 11 5G flagship combines power with effortless elegance. Driven by the most extreme hardware in OnePlus history, dial every possibility up to 11. The OnePlus 11 5G is the Shape of Power."
        pricingType = "Free"
        price = 0.00F
        drawable = resources.getDrawable(R.drawable.oneplus_11_fotor, null)
        bitmap = drawableToBitmap(drawable)
        itemPhoto = bitmap
        userID = "1002"

        var item10 = ItemListing(itemID, itemName, itemDescription, pricingType, price, itemPhoto, userID)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemListingDao().insert(item10)
        }

        // Item 11
        itemID = "1010"
        itemName = "Artistic Paint"
        itemDescription = "An artistic painting is a visual masterpiece crafted with skill and creativity, using various techniques and colors to convey emotions, tell stories, or capture the essence of a subject, offering viewers a unique and subjective experience."
        pricingType = "Free"
        price = 0.00F
        drawable = resources.getDrawable(R.drawable.paint_fotor, null)
        bitmap = drawableToBitmap(drawable)
        itemPhoto = bitmap
        userID = "1002"

        var item11 = ItemListing(itemID, itemName, itemDescription, pricingType, price, itemPhoto, userID)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemListingDao().insert(item11)
        }

        // Item 12
        itemID = "1011"
        itemName = "Philips 4K Smart TV"
        itemDescription = "A Philips 4K TV delivers stunningly sharp and vibrant 4K Ultra HD resolution, providing immersive visuals with rich colors and clarity. It often features smart functionality for streaming content and a sleek design that enhances any living space."
        pricingType = "Free"
        price = 0.00F
        drawable = resources.getDrawable(R.drawable.philips_tv_fotor, null)
        bitmap = drawableToBitmap(drawable)
        itemPhoto = bitmap
        userID = "1002"

        var item12 = ItemListing(itemID, itemName, itemDescription, pricingType, price, itemPhoto, userID)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemListingDao().insert(item12)
        }

        // Item 13
        itemID = "1012"
        itemName = "Samsung Galaxy S23 Ultra 5G"
        itemDescription = "The Samsung S23 Ultra is a big, feature-packed phone with a steep \$1200 price tag to match. It offers an excellent 6.8-inch screen."
        pricingType = "Free"
        price = 0.00F
        drawable = resources.getDrawable(R.drawable.s23_ultra_fotor, null)
        bitmap = drawableToBitmap(drawable)
        itemPhoto = bitmap
        userID = "1003"

        var item13 = ItemListing(itemID, itemName, itemDescription, pricingType, price, itemPhoto, userID)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemListingDao().insert(item13)
        }

        // Item 14
        itemID = "1013"
        itemName = "Thermos Food Container"
        itemDescription = "A thermos food container is a portable, insulated vessel designed to keep food hot or cold for extended periods. It's ideal for preserving the temperature and freshness of meals, making it convenient for picnics, outdoor adventures, or lunch on the go."
        pricingType = "Free"
        price = 0.00F
        drawable = resources.getDrawable(R.drawable.thermos_fotor, null)
        bitmap = drawableToBitmap(drawable)
        itemPhoto = bitmap
        userID = "1003"

        var item14 = ItemListing(itemID, itemName, itemDescription, pricingType, price, itemPhoto, userID)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemListingDao().insert(item14)
        }

        // Item 15
        itemID = "1014"
        itemName = "Brand X Trolley"
        itemDescription = "A goods trolley is a wheeled, transportable cart designed for efficiently moving and transporting items or cargo. It often features a sturdy frame and handles, making it practical for tasks like grocery shopping or warehouse logistics."
        pricingType = "Free"
        price = 0.00F
        drawable = resources.getDrawable(R.drawable.trolley_fotor, null)
        bitmap = drawableToBitmap(drawable)
        itemPhoto = bitmap
        userID = "1003"

        var item15 = ItemListing(itemID, itemName, itemDescription, pricingType, price, itemPhoto, userID)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemListingDao().insert(item15)
        }

        // Item 16
        itemID = "1015"
        itemName = "Under Armor Running Shoes"
        itemDescription = "Under Armour running shoes are performance-focused footwear known for their comfort and support, designed to enhance a runner's speed and endurance with innovative technology and stylish design."
        pricingType = "Free"
        price = 0.00F
        drawable = resources.getDrawable(R.drawable.under_armor_fotor, null)
        bitmap = drawableToBitmap(drawable)
        itemPhoto = bitmap
        userID = "1003"

        var item16 = ItemListing(itemID, itemName, itemDescription, pricingType, price, itemPhoto, userID)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemListingDao().insert(item16)
        }

        Toast.makeText(this, "Item Listing Records Created", Toast.LENGTH_SHORT).show()

    }

    fun insertDummyItemLikeList(){
        // user 1000 like activity
        var userID = "1000"
        var itemId = "1004"
        var ownerId = "1001"
        var likedDate = "2023-10-10"
        var likedTime = "10:20"
        var thisItemLikeList1 = ItemLikeList(userID, itemId, ownerId, likedDate, likedTime)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemLikeListDao().insert(thisItemLikeList1)
        }

        userID = "1000"
        itemId = "1005"
        ownerId = "1001"
        likedDate = "2023-10-10"
        likedTime = "10:20"
        var thisItemLikeList2 = ItemLikeList(userID, itemId, ownerId, likedDate, likedTime)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemLikeListDao().insert(thisItemLikeList2)
        }
//        data class ItemLikeList(
//            @ColumnInfo(name = "UserID") val UserID: String,
//            @ColumnInfo(name = "ItemID") val ItemID: String,
//            @ColumnInfo(name = "OwnerID") val OwnerID: String,
//            @ColumnInfo(name = "LikedDate") val LikedDate: String,
//            @ColumnInfo(name = "LikedTime") val LikedTime: String,
//        )

        userID = "1000"
        itemId = "1006"
        ownerId = "1001"
        likedDate = "2023-10-10"
        likedTime = "10:20"
        var thisItemLikeList3 = ItemLikeList(userID, itemId, ownerId, likedDate, likedTime)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemLikeListDao().insert(thisItemLikeList3)
        }

        // user 1001 like activity
        userID = "1001"
        itemId = "1003"
        ownerId = "1000"
        likedDate = "2023-10-10"
        likedTime = "10:20"
        var thisItemLikeList4 = ItemLikeList(userID, itemId, ownerId, likedDate, likedTime)
        lifecycleScope.launch(Dispatchers.IO){
            appDb.itemLikeListDao().insert(thisItemLikeList4)
        }
        Toast.makeText(this, "Like Activity Created", Toast.LENGTH_SHORT).show()
    }


}