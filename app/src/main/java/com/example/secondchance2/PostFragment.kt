package com.example.secondchance2

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.secondchance2.databinding.FragmentPostBinding
import com.example.secondchance2.databinding.FragmentYouBinding


class PostFragment : Fragment() {

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private lateinit var item_image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostBinding.inflate(inflater, container, false)

        val radio_pricing_type = binding.editItemPricingType
        val priceLayout = binding.priceLayout
        val galleryButton = binding.buttonOpenGallery
        val editPrice = binding.editItemPrice
        item_image = binding.galleryPic
        //val confirmButton = binding.buttonConfirm

        radio_pricing_type.setOnCheckedChangeListener { buttonView, isChecked ->
            val paidRadioButton = binding.pricingTypePaid

            if (paidRadioButton.isChecked) {
                priceLayout.visibility = View.VISIBLE
            } else {
                priceLayout.visibility = View.GONE
                editPrice.text.clear()
                editPrice.text.append("0.00")
            }
        }

        galleryButton.setOnClickListener{
            pickImageGallery()
        }
        return binding.root
    }

    private fun pickImageGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            item_image.setImageURI(data?.data)
            item_image.visibility = View.VISIBLE
        }
    }

    companion object{
        val IMAGE_REQUEST_CODE = 1000
    }


}