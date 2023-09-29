package com.example.secondchance2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.secondchance2.Database.AppDatabase
import com.example.secondchance2.Database.UserMatchedList
import com.example.secondchance2.MatchedListScreenRecycleView.MatchedListScreenItemAdapter
import com.example.secondchance2.databinding.FragmentMatchedListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MatchedListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MatchedListFragment : Fragment() {
    private lateinit var appDb : AppDatabase

    private var _binding: FragmentMatchedListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMatchedListBinding.inflate(inflater, container, false)
        // Access Database
        appDb = AppDatabase.getDatabase(requireContext()) // Use requireContext() to get the context


        // Create the recycleView
        GlobalScope.launch {
            var currentUserId = "1000"
            val allUserMatchedList = returnMatchedList(currentUserId)
            withContext(Dispatchers.Main) {
                val recyclerView = binding.matchedListRecyclerView
                recyclerView.adapter = MatchedListScreenItemAdapter(allUserMatchedList)
            }
        }

        return binding.root
    }

    suspend fun returnMatchedList(userId: String): List<UserMatchedList> {
        return withContext(Dispatchers.IO) {
            appDb.itemLikeListDao().getMatchedListForUser(userId)
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}