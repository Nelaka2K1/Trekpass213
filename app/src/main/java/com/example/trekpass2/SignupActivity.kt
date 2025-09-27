package com.example.trekpass2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {
    
    private lateinit var etFullName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnSignup: MaterialButton
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        
        // Initialize Firebase Auth and Database
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
        
        initViews()
        setClickListeners()
    }
    
    private fun initViews() {
        etFullName = findViewById(R.id.etFullName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnSignup = findViewById(R.id.btnSignup)
    }
    
    private fun setClickListeners() {
        btnSignup.setOnClickListener {
            val fullName = etFullName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            
            if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            if (password.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            createUserAccount(fullName, email, password)
        }
    }
    
    private fun createUserAccount(fullName: String, email: String, password: String) {
        // Show loading state
        btnSignup.isEnabled = false
        btnSignup.text = "Creating Account..."
        
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                btnSignup.isEnabled = true
                btnSignup.text = "Sign Up"
                
                if (task.isSuccessful) {
                    // User account created successfully
                    val user = auth.currentUser
                    user?.let {
                        // Save user data to Firebase Database
                        val userData = mapOf(
                            "fullName" to fullName,
                            "email" to email,
                            "uid" to it.uid,
                            "createdAt" to System.currentTimeMillis()
                        )
                        
                        database.child("users").child(it.uid).setValue(userData)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this, DiscoverActivity::class.java))
                                finish()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Failed to save user data: ${e.message}", Toast.LENGTH_LONG).show()
                                // Still proceed to next screen even if database save fails
                                startActivity(Intent(this, DiscoverActivity::class.java))
                                finish()
                            }
                    }
                } else {
                    // Account creation failed - show detailed error
                    val exception = task.exception
                    val errorMessage = when {
                        exception?.message?.contains("email address is already in use") == true -> 
                            "This email is already registered. Please try logging in instead."
                        exception?.message?.contains("invalid email") == true -> 
                            "Please enter a valid email address."
                        exception?.message?.contains("password") == true -> 
                            "Password should be at least 6 characters long."
                        exception?.message?.contains("network") == true -> 
                            "Network error. Please check your internet connection."
                        else -> "Signup failed: ${exception?.message ?: "Unknown error"}"
                    }
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                }
            }
    }
}
