package com.example.secondchance2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.secondchance2.Database.AppDatabase
import com.example.secondchance2.databinding.FragmentItemScreenBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemScreenFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var currentItemId: String = "null"
    private var currentOwnerId: String = "null"
    private lateinit var appDb : AppDatabase
    private var _binding: FragmentItemScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentItemId = it.getString("itemId").toString()
            currentOwnerId = it.getString("userId").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Do binding
        _binding = FragmentItemScreenBinding.inflate(inflater, container, false)
        // initialize database
        appDb = AppDatabase.getDatabase(requireContext()) // Use requireContext() to get the context
        // apply onClickLister to chat button
        binding.itemScreenChatButton.setOnClickListener {
            replaceFragment(ChatFragment())
        }

        // display item details
        getItemDetails()

        // display owner details
        getOwnerDetails()

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ItemScreenFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun getItemDetails(){
        // predefined the user id
        var thisItemId : String = currentItemId
        GlobalScope.launch {

            var thisitem = appDb.itemListingDao().getThisItemListing(thisItemId)
            if (thisitem != null) {
                binding.itemScreenItemImage.setImageBitmap(thisitem.itemPhoto)
                binding.itemScreenItemName.text = thisitem.itemName.toString()
                if(thisitem.pricingType == "Paid") {
                    binding.itemScreenItemPriceType.text = "For Sale"
                }else{
                    binding.itemScreenItemPriceType.text = "Free"
                }
                binding.itemScreenItemPrice.text = "RM ${thisitem.price.toString()}"
                binding.itemScreenItemDescription.text = thisitem.description.toString()
            }


        }
    }

    fun getOwnerDetails(){
        // predefined the user id
        var thisUserId : String = currentOwnerId
        GlobalScope.launch {

            var thisUser = appDb.userDao().getThisUser(thisUserId)
            print(thisUser)
            if (thisUser != null) {
                binding.itemScreenOwnerName.text = thisUser.userName
                binding.itemScreenOwnerImage.setImageBitmap(thisUser.profilePhoto)
            }

        }
    }


}