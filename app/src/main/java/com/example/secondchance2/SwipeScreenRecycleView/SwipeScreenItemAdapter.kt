package com.example.secondchance2.SwipeScreenRecycleView


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.secondchance2.Database.ItemListing
import com.example.secondchance2.R
import com.example.secondchance2.ItemScreenFragment
import com.example.secondchance2.Database.AppDatabase
import com.example.secondchance2.Database.ItemLikeList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SwipeScreenItemAdapter (private val itemListingDataset: List<ItemListing>
): RecyclerView.Adapter<SwipeScreenItemAdapter.ItemViewHolder>(){
    lateinit var appDb : AppDatabase
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        var itemImage: ImageView = view.findViewById(R.id.swipe_item_image)
        val itemName: TextView = view.findViewById(R.id.swipe_item_name)
        val itemPriceType: TextView = view.findViewById(R.id.swipe_item_price_type)
        val itemPrice: TextView = view.findViewById(R.id.swipe_item_price)
        //val itemHorizontalLinearLayout: LinearLayout = view.findViewById(R.id.swipe_horizontal_linear_layout)
        val itemInfoImage: ImageView = view.findViewById(R.id.swipe_info_image)
        val itemLoveImage: ImageView = view.findViewById(R.id.swipe_love_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.swipe_screen_single_layout, parent, false)
        return ItemViewHolder(adapterLayout)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: SwipeScreenItemAdapter.ItemViewHolder, position: Int) {
        val itemListing = itemListingDataset[position]
        holder.itemImage.setImageBitmap(itemListing.itemPhoto)
        holder.itemName.text = itemListing.itemName.toString()
        holder.itemPriceType.text = "Price Type: " + itemListing.pricingType.toString()
        holder.itemPrice.text =  "Price: RM" + String.format("%.2f", itemListing.price)
        holder.itemInfoImage.setImageResource(R.drawable.baseline_info_24)
        holder.itemLoveImage.setImageResource(R.drawable.greenheart)// Toast "You show love when this image is clicked"
        // Add a click listener to itemLoveImage
        appDb = AppDatabase.getDatabase(holder.itemView.context)
        holder.itemLoveImage.setOnClickListener {
            // Show a toast message when itemLoveImage is clicked
            Toast.makeText(
                holder.itemView.context,
                "You liked ${holder.itemName.text.toString()}",
                Toast.LENGTH_SHORT
            ).show()
            // Update the ItemLikeList Table
            var thisUserId = "1000" // default user
            var thisItemId = itemListing.ItemID.toString()
            var thisOwnerId = itemListing.UserID.toString()
            // Get the current date and time
            val currentDateTime = LocalDateTime.now()
            // Extract the date and time components
            val likedDate = currentDateTime.toLocalDate().toString()
            val currentTime = LocalTime.now()
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            val likedTime = currentTime.format(formatter)
            var thisItemLikeList = ItemLikeList(thisUserId, thisItemId, thisOwnerId, likedDate, likedTime)
            // Use a coroutine scope to call the insert method
            GlobalScope.launch(Dispatchers.IO) {
                appDb.itemLikeListDao().insert(thisItemLikeList)
                var owner = appDb.userDao().getThisUser(thisUserId)
            }

        }

        // Add a click listener to itemInfoImage
        holder.itemInfoImage.setOnClickListener {
            val context = holder.itemView.context

            // Replace the current fragment with ItemScreenFragment
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val itemScreenFragment = ItemScreenFragment() // Replace this with the actual fragment you want to navigate to

            // If you need to pass data to the fragment, you can set arguments here
            val bundle = Bundle()
            bundle.putString("itemId", itemListing.ItemID) // Replace with the appropriate data to pass
            bundle.putString("userId", itemListing.UserID)
            itemScreenFragment.arguments = bundle

            fragmentTransaction.replace(R.id.frame_layout, itemScreenFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }


    }

    override fun getItemCount(): Int {
        return itemListingDataset.size
    }

//    private fun replaceFragment(fragment: Fragment){
//        val fragmentManager = requireActivity().supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.frame_layout, fragment)
//        fragmentTransaction.addToBackStack(null)
//        fragmentTransaction.commit()
//    }


}