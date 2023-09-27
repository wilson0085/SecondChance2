package com.example.secondchance2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.secondchance2.databinding.FragmentIndividualChatBinding

class IndividualChatFragment : Fragment() {

    private var _binding: FragmentIndividualChatBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIndividualChatBinding.inflate(inflater, container, false)
        val backButton = binding.imageButton2

        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        return binding.root
    }

}