package com.example.secondchance2.SwipeScreenRecycleView


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.secondchance2.Database.ItemListing
import com.example.secondchance2.R
import com.example.secondchance2.ItemScreenFragment

class SwipeScreenItemAdapter (private val itemListingDataset: List<ItemListing>
): RecyclerView.Adapter<SwipeScreenItemAdapter.ItemViewHolder>(){

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        var itemImage: ImageView = view.findViewById(R.id.swipe_item_image)
        val itemName: TextView = view.findViewById(R.id.swipe_item_name)
        val itemPriceType: TextView = view.findViewById(R.id.swipe_item_price_type)
        val itemPrice: TextView = view.findViewById(R.id.swipe_item_price)
        val itemHorizontalLinearLayout: LinearLayout = view.findViewById(R.id.swipe_horizontal_linear_layout)
        val itemInfoImage: ImageView = view.findViewById(R.id.swipe_info_image)
        val itemLoveImage: ImageView = view.findViewById(R.id.swipe_love_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.swipe_screen_single_layout, parent, false)
        return ItemViewHolder(adapterLayout)
    }


    override fun onBindViewHolder(holder: SwipeScreenItemAdapter.ItemViewHolder, position: Int) {
        val itemListing = itemListingDataset[position]
        holder.itemImage.setImageBitmap(itemListing.itemPhoto)
        holder.itemName.text = itemListing.itemName.toString()
        holder.itemPriceType.text = itemListing.pricingType.toString()
        holder.itemPrice.text =  itemListing.price.toString()
        holder.itemInfoImage.setImageResource(R.drawable.baseline_info_24)
        holder.itemLoveImage.setImageResource(R.drawable.greenheart)// Toast "You show love when this image is clicked"
        // Add a click listener to itemLoveImage
        holder.itemLoveImage.setOnClickListener {
            // Show a toast message when itemLoveImage is clicked
            Toast.makeText(
                holder.itemView.context,
                "You show love to ${holder.itemName.text.toString()}",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Add a click listener to itemInfoImage
        holder.itemInfoImage.setOnClickListener {
            val context = holder.itemView.context

            // Show a toast message when itemInfoImage is clicked
            Toast.makeText(
                context,
                "You will navigate to detail screen of ${holder.itemName.text.toString()}",
                Toast.LENGTH_SHORT
            ).show()

            // Replace the current fragment with ItemScreenFragment
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val itemScreenFragment = ItemScreenFragment() // Replace this with the actual fragment you want to navigate to

            // If you need to pass data to the fragment, you can set arguments here
            val bundle = Bundle()
            bundle.putString("itemId", itemListing.UserID) // Replace with the appropriate data to pass
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