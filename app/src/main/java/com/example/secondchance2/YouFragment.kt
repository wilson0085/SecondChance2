package com.example.secondchance2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.example.secondchance2.Database.AppDatabase
import com.example.secondchance2.Database.ItemListing
import com.example.secondchance2.Database.User
import com.example.secondchance2.databinding.FragmentYouBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

/**
 * A simple [Fragment] subclass.
 * Use the [YouFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class YouFragment : Fragment() {
    private lateinit var appDb : AppDatabase
    private var _binding: FragmentYouBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentYouBinding.inflate(inflater, container, false)
        appDb = AppDatabase.getDatabase(requireContext()) // Use requireContext() to get the context
        Toast.makeText(requireContext(), "Database Exist.", Toast.LENGTH_LONG).show()
        getUserDetails()

        //Create recycleView
        GlobalScope.launch {
            var currentUserId = "1000"
            var freeListing = returnFreeListing(currentUserId)
            withContext(Dispatchers.Main){
                val recyclerView = binding.recyclerView
                recyclerView.adapter = YouScreenAdapter(freeListing)
            }
            val listingSize = freeListing.size

            // Update Listing size
            if (listingSize > 1){
                binding.listingNum.text = listingSize.toString() + " listings"
            }else{
                binding.listingNum.text = listingSize.toString() + " listing"
            }
        }


        return binding.root
    }

    fun getUserDetails(){
        // Get current year
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)

        // predefined the user id
        var thisUserId :String = "1000"
        GlobalScope.launch {

            var thisUser= appDb.userDao().getThisUser(thisUserId)

            binding.userName.text = thisUser.userName
            binding.userRating.text = thisUser.starRating.toString()
            var thisJoinedYear = currentYear - thisUser.joinedYear
            binding.joinYear.text = "Joined " + thisJoinedYear.toString() + " years ago"
            if (thisUser.emailVerification == "Y"){
                binding.verifyText.text = "Already Verified"
            }else{
                binding.verifyText.text = "Not Verified"
            }
            binding.locationText.text = thisUser.address
            binding.profilePic.setImageBitmap(thisUser.profilePhoto)

        }
    }

    suspend fun returnFreeListing(userId: String): List<ItemListing>{
        return withContext(Dispatchers.IO){
            appDb.itemListingDao().getAllUserItemListing(userId)
        }
    }

}