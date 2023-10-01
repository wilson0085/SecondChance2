package com.example.secondchance2

import android.os.Bundle
import android.provider.Settings.Global
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.secondchance2.Database.AppDatabase
import com.example.secondchance2.Database.UserXItem
import com.example.secondchance2.databinding.FragmentExploreBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar


class ExploreFragment : Fragment() {

    private lateinit var appDb : AppDatabase
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)

        // Access Database
        appDb = AppDatabase.getDatabase(requireContext())

        getUserName()

        // Create recycleView
        GlobalScope.launch {
            var currentUserId = "1000"
            var freeListing = returnFreeListing(currentUserId)
            withContext(Dispatchers.Main){
                val recyclerView = binding.itemRecyclerview
                recyclerView.adapter = ExploreScreenAdapter(freeListing)
            }

        }

        return binding.root
    }

    suspend fun returnFreeListing(userId: String): List<UserXItem>{
        return withContext(Dispatchers.IO){
            appDb.itemListingDao().getAllUserItemListingExceptOne(userId)
        }
    }

    fun getUserName(){
        GlobalScope.launch {
            var userID = "1000"
            var thisUser = appDb.userDao().getThisUser(userID)
            val currentHourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

            if (currentHourOfDay in 0..11){
                binding.welcomeMsg.text = "Good Morning " + thisUser.userName
            }else if(currentHourOfDay in 12..16){
                binding.welcomeMsg.text = "Good Afternoon " + thisUser.userName
            }else{
                binding.welcomeMsg.text = "Good Evening " + thisUser.userName
            }


        }
    }


}