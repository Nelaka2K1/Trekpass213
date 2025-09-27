package com.example.trekpass2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FirebaseTestActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Test Firebase connection
        testFirebaseConnection()
    }
    
    private fun testFirebaseConnection() {
        try {
            val auth = FirebaseAuth.getInstance()
            val database = FirebaseDatabase.getInstance().reference
            
            // Test database write
            database.child("test").setValue("connection_test_${System.currentTimeMillis()}")
                .addOnSuccessListener {
                    Toast.makeText(this, "✅ Firebase Database: Connected", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "❌ Firebase Database Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
                
            // Test auth instance
            if (auth != null) {
                Toast.makeText(this, "✅ Firebase Auth: Connected", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "❌ Firebase Auth: Failed to connect", Toast.LENGTH_LONG).show()
            }
            
        } catch (e: Exception) {
            Toast.makeText(this, "❌ Firebase Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
