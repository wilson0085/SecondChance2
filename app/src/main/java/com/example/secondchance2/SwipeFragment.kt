package com.example.secondchance2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.secondchance2.databinding.FragmentChatBinding
import com.example.secondchance2.databinding.FragmentSwipeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SwipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SwipeFragment : Fragment() {
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

        return binding.root
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}