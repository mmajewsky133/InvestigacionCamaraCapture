package edu.uca.innovatech.investigacioncamaracapture

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import edu.uca.innovatech.investigacioncamaracapture.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnPhotos.setOnClickListener(){
            it.findNavController().navigate(R.id.action_menuFragment_to_photoFragment)
        }
        binding.btnVideos.setOnClickListener(){
            it.findNavController().navigate(R.id.action_menuFragment_to_videoFragment)
        }
    }
}