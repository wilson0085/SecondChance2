package com.example.secondchance2.MatchedListScreenRecycleView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.secondchance2.ChatFragment
import com.example.secondchance2.Database.ItemListing
import com.example.secondchance2.Database.UserMatchedList
import com.example.secondchance2.R


class MatchedListScreenItemAdapter(private val userMatchedListDataset: List<UserMatchedList>
): RecyclerView.Adapter<MatchedListScreenItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        // user ItemViewHolder
        val userItemImage: ImageView = view.findViewById(R.id.match_user_item_image)
        val userImage: ImageView = view.findViewById(R.id.match_user_image)
        val userItemName: TextView = view.findViewById(R.id.match_user_item_name)
        val userItemPriceType: TextView = view.findViewById(R.id.match_user_item_price_type)
        // Matched User ItemViewHolder
        val matchedUserItemImage: ImageView = view.findViewById(R.id.match_matched_user_item_image)
        val matchedUserImage: ImageView = view.findViewById(R.id.match_matched_user_image)
        val matchedUserItemName: TextView = view.findViewById(R.id.match_matched_user_item_name)
        val matchedUserItemPriceType: TextView = view.findViewById(R.id.match_matched_user_item_price_type)
        val chatButton: Button = view.findViewById(R.id.match_chat_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.match_list_screen_single_layout, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val userMatchedList = userMatchedListDataset[position]
        // bind user
        holder.userItemImage.setImageBitmap(userMatchedList.UserItemPhoto)
        holder.userImage.setImageBitmap(userMatchedList.UserPhoto)
        holder.userItemName.text = userMatchedList.UserItemName
        holder.userItemPriceType.text = userMatchedList.UserItemPriceType

        // bind matched user
        holder.matchedUserItemImage.setImageBitmap(userMatchedList.MatchedUserItemPhoto)
        holder.matchedUserImage.setImageBitmap(userMatchedList.MatchedUserPhoto)
        holder.matchedUserItemName.text = userMatchedList.MatchedUserItemName
        holder.matchedUserItemPriceType.text = userMatchedList.MatchedUserItemPriceType

        // add clickListener for chat button
        holder.chatButton.setOnClickListener {
            val context = holder.itemView.context
            // Replace the current fragment with ItemScreenFragment
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout, ChatFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    override fun getItemCount(): Int {
        return userMatchedListDataset.size
    }

}