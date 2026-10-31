package com.cse489.assignment2.ui.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.cse489.assignment2.databinding.ActivityCustomReceiverBinding

class CustomReceiverActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomReceiverBinding
    private lateinit var customReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Custom Broadcast Receiver"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Check if message was passed directly via Intent extra
        val directMessage = intent.getStringExtra("message")
        if (!directMessage.isNullOrEmpty()) {
            binding.tvReceivedMessage.text = directMessage
        }

        // Initialize Custom BroadcastReceiver
        customReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == "com.cse489.assignment2.ACTION_CUSTOM_MESSAGE") {
                    val msg = intent.getStringExtra("message")
                    if (!msg.isNullOrEmpty()) {
                        binding.tvReceivedMessage.text = msg
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter("com.cse489.assignment2.ACTION_CUSTOM_MESSAGE")
        ContextCompat.registerReceiver(
            this,
            customReceiver,
            filter,
            ContextCompat.RECEIVER_NOT_EXPORTED
        )
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(customReceiver)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
