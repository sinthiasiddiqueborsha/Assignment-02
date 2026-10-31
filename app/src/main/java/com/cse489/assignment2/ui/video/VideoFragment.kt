package com.cse489.assignment2.ui.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.cse489.assignment2.databinding.FragmentVideoBinding

class VideoFragment : Fragment() {

    private var _binding: FragmentVideoBinding? = null
    private val binding get() = _binding!!
    private var currentPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
        val uri = videoUrl.toUri()

        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(binding.videoView)

        binding.videoView.setMediaController(mediaController)
        binding.videoView.setVideoURI(uri)
        binding.videoView.setOnPreparedListener { mp ->
            mp.start()
            if (currentPosition > 0) {
                binding.videoView.seekTo(currentPosition)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        currentPosition = binding.videoView.currentPosition
        binding.videoView.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.videoView.stopPlayback()
        _binding = null
    }
}
