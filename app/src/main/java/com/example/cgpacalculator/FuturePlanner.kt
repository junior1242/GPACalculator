package com.example.cgpacalculator


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cgpacalculator.R

class FuturePlanner : AppCompatActivity() {

    private lateinit var currentGpaInput: EditText
    private lateinit var totalCreditsInput: EditText
    private lateinit var desiredGpaInput: EditText
    private lateinit var futureCreditsInput: EditText
    private lateinit var resultOutput: TextView
    private lateinit var buttonOfSum: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.futureplanner)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }
        title = "Future Planner"
        currentGpaInput = findViewById(R.id.currentGpaInput)
        totalCreditsInput = findViewById(R.id.totalCreditsInput)
        desiredGpaInput = findViewById(R.id.desiredGpaInput)
        futureCreditsInput = findViewById(R.id.futureCreditsInput)
        resultOutput = findViewById(R.id.resultOutput)
        buttonOfSum = findViewById(R.id.calculateBotton)
        buttonOfSum.setOnClickListener {
            calculateFutureCgpa()
        }
    }

    private fun calculateFutureCgpa() {

        try {
            val currentGPA = currentGpaInput.text.toString().toDouble()
            val totalCredits = totalCreditsInput.text.toString().toInt()
            val desiredGPA = desiredGpaInput.text.toString().toDouble()
            val futureCredits = futureCreditsInput.text.toString().toInt()

            val requiredGPA = (desiredGPA * (totalCredits + futureCredits) - currentGPA * totalCredits) / futureCredits

            resultOutput.text = "Minimum GPA required: %.2f".format(requiredGPA)
            val rounded = String.format("%.2f", requiredGPA).toDouble()
            Toast.makeText(this, "Required GPA: $rounded ", Toast.LENGTH_LONG).show()

        } catch (e: NumberFormatException) {
            resultOutput.text = "Please enter valid numbers."
        }
    }
}

