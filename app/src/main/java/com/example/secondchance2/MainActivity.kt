package com.example.secondchance2

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceControl.Transaction
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.ColumnInfo
import com.example.secondchance2.Database.AppDatabase
import com.example.secondchance2.Database.User
import com.example.secondchance2.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var appDb : AppDatabase
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create the database
        appDb = AppDatabase.getDatabase(this)
        Toast.makeText(this, "Database Created", Toast.LENGTH_LONG).show()
        insertDummyData()
        // Toast a message to indicate database successfully created

        replaceFragment(ExploreFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_explore -> replaceFragment(ExploreFragment())
                R.id.navigation_swipe -> replaceFragment(SwipeFragment())
                R.id.navigation_post -> replaceFragment(PostFragment())
                R.id.navigation_chat -> replaceFragment(ChatFragment())
                R.id.navigation_you -> replaceFragment(YouFragment())

                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            // If the Drawable is already a BitmapDrawable, return its Bitmap directly
            return drawable.bitmap
        }

        // Otherwise, create a new Bitmap and draw the Drawable on it
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }

    fun insertDummyData(){
        val userId = "1000"
        val userName: String = "Jake Lim"
        val joinedYear: Int = 2021
        val emailVerification: String = "Y"
        val address: String = "Puchong, Malaysia"
        val starRating: Float = 4.5F
        val drawable = resources.getDrawable(R.drawable.woman_profile_fotor, null) // Replace with your Drawable
        val bitmap = drawableToBitmap(drawable)
        val profilePhoto: Bitmap = bitmap

        val thisUser = User(userId, userName, joinedYear, emailVerification, address, starRating, profilePhoto)
        GlobalScope.launch(Dispatchers.IO) {
            appDb.userDao().insert(thisUser)
        }
        Toast.makeText(this, "Records Created", Toast.LENGTH_LONG).show()
    }

}