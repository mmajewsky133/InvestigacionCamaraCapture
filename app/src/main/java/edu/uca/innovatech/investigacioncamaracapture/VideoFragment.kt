package edu.uca.innovatech.investigacioncamaracapture

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import edu.uca.innovatech.investigacioncamaracapture.databinding.FragmentVideoBinding
import java.lang.Exception

class VideoFragment : Fragment() {

    private lateinit var binding: FragmentVideoBinding

    val REQUEST_VIDEO_CAPTURE = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVideoBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnCamera.setOnClickListener(){
            dispatchTakeVideoIntent()
        }
        binding.btnPause.setOnClickListener(){
            with (binding.vvCamera) {
                if (isPlaying) {
                    pause()
                } else {
                    start()
                }
            }
        }
    }

    private fun dispatchTakeVideoIntent() {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        try {
            startActivityForResult(intent, REQUEST_VIDEO_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            val videoUri = data?.data

            with (binding.vvCamera) {
                setVideoURI(videoUri)

                val mediaController = MediaController(this@VideoFragment.context)
                setMediaController(mediaController)

                mediaController.setAnchorView(binding.vvCamera)
                requestFocus()
                start()
            }
        }
    }
}