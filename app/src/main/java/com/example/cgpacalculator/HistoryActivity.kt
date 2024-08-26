package com.example.cgpacalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {

    private lateinit var tvHistory: TextView
    private lateinit var btnClearHistory: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        tvHistory = findViewById(R.id.tvHistory)
        btnClearHistory = findViewById(R.id.btnClearHistory)
        // Load the GPA history from SharedPreferences
        val sharedPreferences = getSharedPreferences("GPA_HISTORY", MODE_PRIVATE)
        val history = sharedPreferences.getString("history", "No History Found.")

        tvHistory.text = history
        btnClearHistory.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            tvHistory.text = ""

        }
    }
}


