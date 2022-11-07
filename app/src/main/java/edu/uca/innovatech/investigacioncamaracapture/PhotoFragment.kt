package edu.uca.innovatech.investigacioncamaracapture

import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import edu.uca.innovatech.investigacioncamaracapture.databinding.FragmentPhotoBinding
import java.text.SimpleDateFormat
import java.util.*

class PhotoFragment : Fragment() {

    private lateinit var binding: FragmentPhotoBinding

    val REQUEST_IMAGE_CAPTURE = 1
    private var imageUri: Uri? = null
    private var fechayhora = SimpleDateFormat("ddMyyyy_hhmmss").format(Date())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhotoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnCamera.setOnClickListener {
            dispatchTakePictureIntent()
        }
    }

    private fun dispatchTakePictureIntent() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "Prueba_Imagen_${fechayhora.toString()}")
        imageUri = (activity as MainActivity).getImageUri(values)
        //camera intent
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        try {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            println("El error se produjo por: ${e.message}")
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK) {
            binding.ivCamera.setImageURI(imageUri)
        }

    }
}