package com.cse489.assignment2.ui.audio

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cse489.assignment2.databinding.FragmentAudioBinding

class AudioFragment : Fragment() {

    private var _binding: FragmentAudioBinding? = null
    private val binding get() = _binding!!
    private var mediaPlayer: MediaPlayer? = null
    private var isPrepared = false
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var updateSeekBar: Runnable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAudioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPlay.isEnabled = false
        val audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"

        mediaPlayer = MediaPlayer().apply {
            setDataSource(requireContext(), Uri.parse(audioUrl))
            prepareAsync()
        }

        mediaPlayer?.setOnPreparedListener { mp ->
            isPrepared = true
            binding.btnPlay.isEnabled = true
            binding.seekBar.max = mp.duration
            Toast.makeText(requireContext(), "Audio ready to play", Toast.LENGTH_SHORT).show()
        }

        mediaPlayer?.setOnErrorListener { _, what, _ ->
            Toast.makeText(requireContext(), "Error playing audio: $what", Toast.LENGTH_SHORT).show()
            true
        }

        updateSeekBar = object : Runnable {
            override fun run() {
                mediaPlayer?.let {
                    if (isPrepared && it.isPlaying) {
                        binding.seekBar.progress = it.currentPosition
                    }
                }
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(updateSeekBar)

        binding.btnPlay.setOnClickListener {
            if (isPrepared) {
                mediaPlayer?.let { mp ->
                    if (!mp.isPlaying) {
                        mp.start()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Please wait, audio is loading...", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnPause.setOnClickListener {
            mediaPlayer?.let { mp ->
                if (mp.isPlaying) {
                    mp.pause()
                }
            }
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser && isPrepared) {
                    mediaPlayer?.seekTo(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.let {
            if (isPrepared && it.isPlaying) {
                it.pause()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(updateSeekBar)
        mediaPlayer?.release()
        mediaPlayer = null
        _binding = null
    }
}
