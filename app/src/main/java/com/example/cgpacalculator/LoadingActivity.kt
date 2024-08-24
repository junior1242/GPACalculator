package com.example.cgpacalculator

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cgpacalculator.MainActivity
import com.example.cgpacalculator.R

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        val appName = findViewById<TextView>(R.id.app_gpa)
        val developerInfo = findViewById<TextView>(R.id.DeveloperInfo)
        val icon=findViewById<ImageView>(R.id.icon)

        val fadeInAppName = AnimationUtils.loadAnimation(this, R.anim.fade_in_app_name)
        val fadeInDeveloperInfo = AnimationUtils.loadAnimation(this, R.anim.fade_in_developer_info)
        val fadeInIcon=AnimationUtils.loadAnimation(this, R.anim.fade_in_app_name)



        appName.startAnimation(fadeInAppName)
        icon.startAnimation((fadeInIcon))
        developerInfo.startAnimation(fadeInDeveloperInfo)


        appName.visibility = TextView.VISIBLE
        icon.visibility=ImageView.VISIBLE
        developerInfo.visibility = TextView.VISIBLE

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2500)
    }
}
