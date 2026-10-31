package com.cse489.assignment2.ui.imagescale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.cse489.assignment2.databinding.FragmentImageScaleBinding

class ImageScaleFragment : Fragment() {

    private var _binding: FragmentImageScaleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageScaleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageUrl = "https://picsum.photos/1000/1500"

        // Hide progress bar once loaded / error handled by Glide natively
        binding.progressBar.visibility = View.GONE
        binding.tvError.visibility = View.GONE

        Glide.with(this)
            .load(imageUrl)
            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
            .error(android.R.drawable.stat_notify_error)
            .into(binding.photoView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
