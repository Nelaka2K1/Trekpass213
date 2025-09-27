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
import java.text.SimpleDateFormat
import java.util.*

class PaymentActivity : AppCompatActivity() {
    
    private lateinit var etPaymentMethod: TextInputEditText
    private lateinit var etSmartCard: TextInputEditText
    private lateinit var etAmount: TextInputEditText
    private lateinit var etCurrency: TextInputEditText
    private lateinit var btnPay: MaterialButton
    
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    
    private var title = ""
    private var quantity = 1
    private var totalPrice = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        
        // Initialize Firebase
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
        
        initViews()
        loadData()
        setClickListeners()
    }
    
    private fun initViews() {
        etPaymentMethod = findViewById(R.id.etPaymentMethod)
        etSmartCard = findViewById(R.id.etSmartCard)
        etAmount = findViewById(R.id.etAmount)
        etCurrency = findViewById(R.id.etCurrency)
        btnPay = findViewById(R.id.btnPay)
    }
    
    private fun loadData() {
        title = intent.getStringExtra("title") ?: "Mount Fuji"
        quantity = intent.getIntExtra("quantity", 1)
        totalPrice = intent.getIntExtra("totalPrice", 400)
        
        etAmount.setText(totalPrice.toString())
    }
    
    private fun setClickListeners() {
        btnPay.setOnClickListener {
            processPayment()
        }
    }
    
    private fun processPayment() {
        val paymentMethod = etPaymentMethod.text.toString()
        val smartCard = etSmartCard.text.toString()
        val amount = etAmount.text.toString()
        val currency = etCurrency.text.toString()
        
        if (paymentMethod.isEmpty() || smartCard.isEmpty() || amount.isEmpty() || currency.isEmpty()) {
            Toast.makeText(this, "Please fill in all payment details", Toast.LENGTH_SHORT).show()
            return
        }
        
        // Save booking to Firebase Database
        saveBookingToDatabase()
    }
    
    private fun saveBookingToDatabase() {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show()
            return
        }
        
        val bookingId = database.child("bookings").push().key ?: ""
        val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        
        val bookingData = mapOf(
            "bookingId" to bookingId,
            "userId" to currentUser.uid,
            "title" to title,
            "quantity" to quantity,
            "totalPrice" to totalPrice,
            "paymentMethod" to etPaymentMethod.text.toString(),
            "smartCard" to etSmartCard.text.toString(),
            "amount" to etAmount.text.toString(),
            "currency" to etCurrency.text.toString(),
            "bookingDate" to currentDate,
            "status" to "confirmed"
        )
        
        database.child("bookings").child(bookingId).setValue(bookingData)
            .addOnSuccessListener {
                Toast.makeText(this, "Payment successful! Booking confirmed.", Toast.LENGTH_LONG).show()
                
                // Navigate back to discover page
                val intent = Intent(this, DiscoverActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Payment failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
