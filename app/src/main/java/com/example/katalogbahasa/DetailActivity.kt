package com.example.katalogbahasa

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgLogo: ImageView = findViewById(R.id.img_detail_logo)
        val tvTitle: TextView = findViewById(R.id.tv_detail_title)
        val tvDescription: TextView = findViewById(R.id.tv_detail_description)

        val title = intent.getStringExtra("EXTRA_TITLE")
        val description = intent.getStringExtra("EXTRA_DESC")
        val iconResId = intent.getIntExtra("EXTRA_ICON", 0)

        tvTitle.text = title
        tvDescription.text = description

        if (iconResId != 0) {
            imgLogo.setImageResource(iconResId)
        }
        val btnBack: ImageView = findViewById(R.id.btn_back)

        btnBack.setOnClickListener {
            finish()
        }
    }
}