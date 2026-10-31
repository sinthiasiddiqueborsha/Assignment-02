package com.cse489.assignment2.ui.broadcast

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.cse489.assignment2.R
import com.cse489.assignment2.databinding.FragmentBroadcastReceiverBinding

class BroadcastReceiverFragment : Fragment() {

    private var _binding: FragmentBroadcastReceiverBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBroadcastReceiverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = arrayOf(
            getString(R.string.option_custom_broadcast),
            getString(R.string.option_system_battery)
        )

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerBroadcastType.adapter = adapter

        binding.btnNext.setOnClickListener {
            val selectedPosition = binding.spinnerBroadcastType.selectedItemPosition
            if (selectedPosition == 0) {
                // Custom Broadcast Receiver flow
                val intent = Intent(requireContext(), CustomInputActivity::class.java)
                startActivity(intent)
            } else {
                // System Battery Notification Receiver flow
                val intent = Intent(requireContext(), BatteryInfoActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
