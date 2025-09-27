package com.example.trekpass2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class DiscoverActivity : AppCompatActivity() {
    
    private lateinit var cardMountain1: CardView
    private lateinit var cardMountain2: CardView
    private lateinit var cardMountain3: CardView
    private lateinit var ivProfile: android.widget.ImageView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)
        
        initViews()
        setClickListeners()
    }
    
    private fun initViews() {
        cardMountain1 = findViewById(R.id.cardMountain1)
        cardMountain2 = findViewById(R.id.cardMountain2)
        cardMountain3 = findViewById(R.id.cardMountain3)
        ivProfile = findViewById(R.id.ivProfile)
    }
    
    private fun setClickListeners() {
        cardMountain1.setOnClickListener {
            openDetailPage("Northern Mountain", "4.5", "5 Days", "$400/Package")
        }
        
        cardMountain2.setOnClickListener {
            openDetailPage("Northern Mountain", "4.5", "5 Days", "$400/Package")
        }
        
        cardMountain3.setOnClickListener {
            openDetailPage("Northern Mountain", "4.5", "5 Days", "$400/Package")
        }
        
        ivProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
    
    private fun openDetailPage(title: String, rating: String, duration: String, price: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("rating", rating)
        intent.putExtra("duration", duration)
        intent.putExtra("price", price)
        startActivity(intent)
    }
}
