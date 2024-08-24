package com.example.cgpacalculator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnGPA = findViewById<Button>(R.id.btnGPA)
        val btnFuture = findViewById<Button>(R.id.btnFuture)
        progressBar = findViewById(R.id.loaderBar)

        btnGPA.setOnClickListener {
            showLoadingAndNavigate {
                startActivity(Intent(applicationContext, GpaCalculator::class.java))
            }
        }

        btnFuture.setOnClickListener {
            showLoadingAndNavigate {
                startActivity(Intent(applicationContext, FuturePlanner::class.java))
            }
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showExitDialog()
            }
        })
    }

    private fun showLoadingAndNavigate(navigationAction: () -> Unit) {
        progressBar.visibility = View.VISIBLE
        Handler().postDelayed({
            progressBar.visibility = View.GONE
            navigationAction()
        }, 1000)
    }

    private fun showExitDialog() {
        AlertDialog.Builder(this)
            .setMessage("Do you really want to leave?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                finish()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.cancel() // Reappear the app
            }
            .create()
            .show()
    }
}
