package com.example.secondchance2

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.secondchance2.Database.AppDatabase
import com.example.secondchance2.Database.ItemListing

class YouScreenAdapter (private val itemListingDataset: List<ItemListing>
): RecyclerView.Adapter<YouScreenAdapter.ItemViewHolder>() {
    lateinit var appDb : AppDatabase

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        var itemCard: LinearLayout = view.findViewById(R.id.rating)
        var itemImage: ImageView = view.findViewById(R.id.item_image)
        var itemName: TextView = view.findViewById(R.id.item_name)
        var itemPrice: TextView = view.findViewById(R.id.item_price)
        var itemPricingType: TextView = view.findViewById(R.id.item_pricing_type)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder{
        // Create a new view
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.you_screen_single_layout, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: YouScreenAdapter.ItemViewHolder, position: Int){
        val itemListing = itemListingDataset[position]
        holder.itemImage.setImageBitmap(itemListing.itemPhoto)
        holder.itemName.text = itemListing.itemName.toString()
        holder.itemPrice.text = "RM" + itemListing.price.toString()
        holder.itemPricingType.text = itemListing.pricingType.toString()

        // Add click listener to each item card/fragment
        holder.itemCard.setOnClickListener{
            val context = holder.itemView.context
            // Replace the current fragment with ItemScreenFragment
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val itemScreenFragment = ItemScreenFragment()

            // Pass data to fragment
            val bundle = Bundle()
            bundle.putString("itemId", itemListing.ItemID)
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

}