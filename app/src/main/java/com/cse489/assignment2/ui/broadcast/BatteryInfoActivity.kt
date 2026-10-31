package com.cse489.assignment2.ui.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cse489.assignment2.R
import com.cse489.assignment2.databinding.ActivityBatteryInfoBinding

class BatteryInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBatteryInfoBinding
    private val batteryReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == Intent.ACTION_BATTERY_CHANGED) {
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                if (level >= 0 && scale > 0) {
                    val batteryPct = (level * 100) / scale
                    binding.tvBatteryPercentage.text = getString(R.string.battery_level_format, batteryPct)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBatteryInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Battery Notification"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnBatteryNext.setOnClickListener {
            val intent = Intent(this, BatterySummaryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryReceiver, filter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(batteryReceiver)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
