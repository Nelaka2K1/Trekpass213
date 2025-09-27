package com.example.trekpass2

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    
    private lateinit var tvUserName: TextView
    private lateinit var tvUserEmail: TextView
    private lateinit var auth: FirebaseAuth
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        
        auth = FirebaseAuth.getInstance()
        
        initViews()
        loadUserData()
        setClickListeners()
    }
    
    private fun initViews() {
        tvUserName = findViewById(R.id.tvUserName)
        tvUserEmail = findViewById(R.id.tvUserEmail)
    }
    
    private fun loadUserData() {
        val currentUser = auth.currentUser
        currentUser?.let { user ->
            tvUserName.text = user.displayName ?: "User"
            tvUserEmail.text = user.email ?: ""
        }
    }
    
    private fun setClickListeners() {
        // Add click listeners for profile options
        findViewById<TextView>(R.id.tvMyBookings).setOnClickListener {
            // Navigate to bookings
            Toast.makeText(this, "My Bookings feature coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        findViewById<TextView>(R.id.tvSchedule).setOnClickListener {
            // Navigate to schedule
            Toast.makeText(this, "Schedule feature coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        findViewById<TextView>(R.id.tvSettings).setOnClickListener {
            // Navigate to settings
            Toast.makeText(this, "Settings feature coming soon!", Toast.LENGTH_SHORT).show()
        }
    }
}
