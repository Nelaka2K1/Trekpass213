package com.example.trekpass2

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class StartingActivity : AppCompatActivity() {
    
    private lateinit var btnLetsGo: MaterialButton
    private lateinit var tvSignUp: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starting)
        
        initViews()
        setClickListeners()
    }
    
    private fun initViews() {
        btnLetsGo = findViewById(R.id.btnLetsGo)
        tvSignUp = findViewById(R.id.tvSignUp)
    }
    
    private fun setClickListeners() {
        btnLetsGo.setOnClickListener {
            // Navigate to login page
            startActivity(Intent(this, LoginActivity::class.java))
        }
        
        tvSignUp.setOnClickListener {
            // Navigate to signup page
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
