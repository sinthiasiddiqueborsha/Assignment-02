package com.cse489.assignment2.ui.broadcast

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cse489.assignment2.databinding.ActivityCustomInputBinding

class CustomInputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Custom Broadcast Input"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSend.setOnClickListener {
            val message = binding.etMessage.text.toString().trim()
            if (message.isEmpty()) {
                Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 1. Send Custom Broadcast
            val broadcastIntent = Intent("com.cse489.assignment2.ACTION_CUSTOM_MESSAGE").apply {
                putExtra("message", message)
                setPackage(packageName)
            }
            sendBroadcast(broadcastIntent)

            // 2. Start Third Activity (CustomReceiverActivity) passing message
            val intent = Intent(this, CustomReceiverActivity::class.java).apply {
                putExtra("message", message)
            }
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
