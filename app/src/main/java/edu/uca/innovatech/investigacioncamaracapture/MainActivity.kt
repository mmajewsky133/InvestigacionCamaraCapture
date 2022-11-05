package edu.uca.innovatech.investigacioncamaracapture

import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build.VERSION_CODES.M
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import edu.uca.innovatech.investigacioncamaracapture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    fun getImageUri(values: ContentValues): Uri?{
        val imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        return imageUri
    }

    fun getVidUri(values: ContentValues): Uri?{
        val vidUri = contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values)

        return vidUri
    }

}