package com.cse489.assignment2.ui.broadcast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cse489.assignment2.databinding.ActivityBatterySummaryBinding

class BatterySummaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBatterySummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBatterySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Battery Summary"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
