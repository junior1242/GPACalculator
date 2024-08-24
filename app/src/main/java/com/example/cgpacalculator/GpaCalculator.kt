package com.example.cgpacalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class GpaCalculator : AppCompatActivity() {

    private lateinit var courseContainer: LinearLayout
    private lateinit var btnAddCourse: Button
    private lateinit var btnRemoveCourse: Button
    private lateinit var btnCalculateGpa: Button
    private lateinit var tvTotalCreditHours: TextView
    private lateinit var tvSelectedGrades: TextView
    private lateinit var tvCalculatedGpa:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gpacalculator)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

                        // Closes the current activity and returns to the previous one
        btnBack.setOnClickListener {
            finish()
        }

        courseContainer = findViewById(R.id.courseContainer)
        btnAddCourse = findViewById(R.id.btnAddCourse)
        btnRemoveCourse = findViewById(R.id.btnRemoveCourse)
        btnCalculateGpa = findViewById(R.id.btnCalculateGpa)
        tvTotalCreditHours = findViewById(R.id.tvTotalCreditHours)
        tvSelectedGrades = findViewById(R.id.tvSelectedGrades)
        tvCalculatedGpa = findViewById(R.id.tvCalculatedGpa)

        repeat(5) {
            addCourseView()
        }

        btnAddCourse.setOnClickListener {
            addCourseView()
            Toast.makeText(this,"course added",Toast.LENGTH_SHORT).show()
        }

        btnRemoveCourse.setOnClickListener {
            removeCourseView()
            Toast.makeText(this,"course remove",Toast.LENGTH_SHORT).show()
        }

        btnCalculateGpa.setOnClickListener {
            calculateGpa()
        }
    }

    private fun addCourseView() {
        val inflater = LayoutInflater.from(this)
        val courseView = inflater.inflate(R.layout.course_item, courseContainer, false)

        val spinner = courseView.findViewById<Spinner>(R.id.spinnerGrade)
        ArrayAdapter.createFromResource(
            this,
            R.array.grades_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        courseContainer.addView(courseView)
    }

    private fun removeCourseView() {
        if (courseContainer.childCount > 0) {
            courseContainer.removeViewAt(courseContainer.childCount - 1)
        }
    }

    private fun calculateGpa() {
        var totalCreditHours = 0
        var totalGradePoints = 0.0

        for (i in 0 until courseContainer.childCount) {
            val courseView = courseContainer.getChildAt(i)

            val etCourseName = courseView.findViewById<EditText>(R.id.etCourseName)
            val etCreditHours = courseView.findViewById<EditText>(R.id.etCreditHours)
            val spinnerGrade = courseView.findViewById<Spinner>(R.id.spinnerGrade)

            val courseName = etCourseName.text.toString()
            val creditHours = etCreditHours.text.toString().toIntOrNull() ?: 0
            val selectedGrade = spinnerGrade.selectedItem.toString()

            totalCreditHours += creditHours
            totalGradePoints += calculateGradePoint(selectedGrade) * creditHours
        }

        val gpa = if (totalCreditHours > 0) totalGradePoints / totalCreditHours else 0.0

        // Round the GPA to 2 decimal places
        val roundedGpa = String.format("%.2f", gpa).toDouble()
        val roundGrades = String.format("%.2f",totalGradePoints).toDouble()
        // Update the UI with the rounded GPA
        tvTotalCreditHours.text = "Total Credit Hours: $totalCreditHours"
        tvSelectedGrades.text = "Selected Grades: $roundGrades"
        tvCalculatedGpa.text = "Total GPA: $roundedGpa"
        Toast.makeText(this, "Calculated GPA: $roundedGpa", Toast.LENGTH_LONG).show()
    }


    private fun calculateGradePoint(grade: String): Double {
        return when (grade) {
            "A" -> 4.0
            "A-"->3.7
            "B+"->3.3
            "B" -> 3.0
            "B-"->2.7
            "C+" -> 2.3
            "C"->2.0
            "C-"->1.7
            "D" -> 1.0
            else -> 0.0
        }
    }
}
