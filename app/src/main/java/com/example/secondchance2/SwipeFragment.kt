package com.example.secondchance2


import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.secondchance2.Database.AppDatabase
import com.example.secondchance2.Database.ItemListing
import com.example.secondchance2.SwipeScreenRecycleView.SwipeScreenItemAdapter
import com.example.secondchance2.databinding.FragmentSwipeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SwipeFragment : Fragment() {
    private lateinit var appDb : AppDatabase

    private var _binding: FragmentSwipeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSwipeBinding.inflate(inflater, container, false)
        val goToMatchList = binding.goToMatchedList
        goToMatchList.setOnClickListener {
            replaceFragment(MatchedListFragment())
        }
        // Access Database
        appDb = AppDatabase.getDatabase(requireContext()) // Use requireContext() to get the context

        // Create the recycleView
        GlobalScope.launch {
            var currentUserId = "1000"
            val freeListing = returnFreeListing(currentUserId)
            withContext(Dispatchers.Main) {
                val recyclerView = binding.recyclerView
                recyclerView.adapter = SwipeScreenItemAdapter(freeListing)

            }
        }

        return binding.root
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    suspend fun returnFreeListing(userId: String): List<ItemListing> {
        return withContext(Dispatchers.IO) {
            appDb.itemListingDao().getAllFreeItemListing(userId)
        }
    }



}