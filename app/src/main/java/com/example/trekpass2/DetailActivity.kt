package com.example.trekpass2

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class DetailActivity : AppCompatActivity() {
    
    private lateinit var tvTitle: TextView
    private lateinit var tvRating: TextView
    private lateinit var tvQuantity: TextView
    private lateinit var tvDuration: TextView
    private lateinit var tvPrice: TextView
    private lateinit var btnMinus: MaterialButton
    private lateinit var btnPlus: MaterialButton
    private lateinit var btnBookNow: MaterialButton
    
    private var quantity = 5
    private var basePrice = 400
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        
        initViews()
        loadData()
        setClickListeners()
    }
    
    private fun initViews() {
        tvTitle = findViewById(R.id.tvTitle)
        tvRating = findViewById(R.id.tvRating)
        tvQuantity = findViewById(R.id.tvQuantity)
        tvDuration = findViewById(R.id.tvDuration)
        tvPrice = findViewById(R.id.tvPrice)
        btnMinus = findViewById(R.id.btnMinus)
        btnPlus = findViewById(R.id.btnPlus)
        btnBookNow = findViewById(R.id.btnBookNow)
    }
    
    private fun loadData() {
        val title = intent.getStringExtra("title") ?: "Mount Fuji"
        val rating = intent.getStringExtra("rating") ?: "4.9"
        val duration = intent.getStringExtra("duration") ?: "5 Days"
        val price = intent.getStringExtra("price") ?: "$400/Package"
        
        tvTitle.text = title
        tvRating.text = rating
        tvDuration.text = duration
        tvPrice.text = price
        
        updatePrice()
    }
    
    private fun setClickListeners() {
        btnMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                tvQuantity.text = quantity.toString()
                updatePrice()
            }
        }
        
        btnPlus.setOnClickListener {
            quantity++
            tvQuantity.text = quantity.toString()
            updatePrice()
        }
        
        btnBookNow.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("title", tvTitle.text.toString())
            intent.putExtra("quantity", quantity)
            intent.putExtra("totalPrice", quantity * basePrice)
            startActivity(intent)
        }
    }
    
    private fun updatePrice() {
        val totalPrice = quantity * basePrice
        tvPrice.text = "$$totalPrice/Package"
    }
}
