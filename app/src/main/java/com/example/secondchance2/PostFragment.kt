package com.example.secondchance2

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.secondchance2.Database.AppDatabase
import com.example.secondchance2.Database.ItemListing
import com.example.secondchance2.databinding.FragmentPostBinding
import com.example.secondchance2.databinding.FragmentYouBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException


class PostFragment : Fragment() {

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private lateinit var item_image: ImageView
    private lateinit var appDb : AppDatabase
    private var item_image_bitmap: Bitmap? = null

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
        val confirmButton = binding.buttonConfirm
        var newItemId = ""

        radio_pricing_type.setOnCheckedChangeListener { buttonView, isChecked ->
            val paidRadioButton = binding.pricingTypePaid

            if (paidRadioButton.isChecked) {
                priceLayout.visibility = View.VISIBLE
            } else {
                priceLayout.visibility = View.GONE
                editPrice.text.clear()
            }
        }

        galleryButton.setOnClickListener{
            pickImageGallery()
        }

        confirmButton.setOnClickListener {
            if ((binding.editItemName.text.toString() == "") && (binding.editItemDescription.text.toString() == "")){
                Toast.makeText(requireContext(), "Please fill out all required fields", Toast.LENGTH_SHORT).show()
            }else{
                val name = binding.editItemName.text.toString()
                val description = binding.editItemDescription.text.toString()
                val pricingFree = binding.pricingTypeFree
                val pricingPaid = binding.pricingTypePaid
                var pricingTypeText = ""


                val priceText = binding.editItemPrice.text.toString()
                val price = if (priceText.isEmpty()){
                    0.00F
                }else{
                    priceText.toFloat()
                }

                if(pricingFree.isChecked){
                    pricingTypeText = pricingFree.text.toString()
                }else{
                    pricingTypeText = pricingPaid.text.toString()
                }

                // Access Database
                appDb = AppDatabase.getDatabase(requireContext()) // Use requireContext() to get the context
                GlobalScope.launch{
                    val lastItemId = appDb.itemListingDao().getLastItemID()
                    val itemId = lastItemId?.toInt() ?:0
                    newItemId = (itemId + 1).toString()
                }


                val defaultBitmap = BitmapFactory.decodeResource(resources, R.drawable.paint_fotor)


                val thisItem = ItemListing(newItemId, name, description, pricingTypeText, price, item_image_bitmap ?: defaultBitmap, "1000")

                lifecycleScope.launch(Dispatchers.IO){
                    appDb.itemListingDao().insert(thisItem)
                }

                // Toast indicate item posted
                Toast.makeText(requireContext(), "Item posted", Toast.LENGTH_SHORT).show()

                // Clear Input
                binding.editItemName.text.clear()
                binding.editItemDescription.text.clear()
                binding.galleryPic.visibility = View.GONE
            }



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

            val imageUri = data?.data
            if(imageUri != null){
                item_image_bitmap = getBitmapFromUri(imageUri)
            }
        }
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        return try {
            val inputStream = requireContext().contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    companion object{
        val IMAGE_REQUEST_CODE = 1000
    }


}