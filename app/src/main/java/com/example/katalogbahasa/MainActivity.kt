package com.example.katalogbahasa

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val TAG_NIM = "42430025"

    private var catalogArray: Array<Language> = arrayOf()
    private lateinit var adapter: CatalogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            setContentView(R.layout.activity_main)
            Log.d(TAG_NIM, "setContentView berhasil")
        } catch (e: Exception) {
            Log.e(TAG_NIM, "Gagal memuat layout: ${e.message}")
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rv_catalog)
        val etSearch = findViewById<EditText>(R.id.et_search)
        val btnSearch = findViewById<Button>(R.id.btn_search)
        val btnSortAZ = findViewById<Button>(R.id.btn_sort_az)
        val btnSortZA = findViewById<Button>(R.id.btn_sort_za)

        if (recyclerView != null) {
            val orientation = resources.configuration.orientation
            recyclerView.layoutManager = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                GridLayoutManager(this, 2)
            } else {
                LinearLayoutManager(this)
            }
        } else {
            Log.e(TAG_NIM, "Error: RecyclerView tidak ditemukan di XML!")
        }

        initDataArray()

        if (recyclerView != null) {
            adapter = CatalogAdapter(catalogArray.toList())
            recyclerView.adapter = adapter
        }

        btnSearch?.setOnClickListener {
            val query = etSearch?.text.toString().trim()
            if (query.isEmpty()) {
                adapter.updateData(catalogArray.toList())
                Toast.makeText(this, "Menampilkan semua data", Toast.LENGTH_SHORT).show()
            } else {
                linearSearch(query)
            }
        }

        btnSortAZ?.setOnClickListener { bubbleSort(true) }
        btnSortZA?.setOnClickListener { bubbleSort(false) }
    }

    private fun initDataArray() {
        catalogArray = arrayOf(
            Language("Python", "Data Science & AI",R.drawable.python),
            Language("Java", "Android & Enterprise",R.drawable.java),
            Language("Kotlin", "Modern Android",R.drawable.kotlin),
            Language("C++", "Game Development",R.drawable.cpp),
            Language("Ruby", "Web Development",R.drawable.ruby),
            Language("Swift", "iOS Development",R.drawable.swift),
            Language("JavaScript", "Web & Frontend",R.drawable.javascript),
            Language("Rust", "System Programming",R.drawable.rust),
            Language("Go", "Cloud & Backend",R.drawable.go),
            Language("Dart", "Cross-platform Mobile",R.drawable.i_dart)
        )
        Log.i(TAG_NIM, "Data Array berhasil dimuat.")
    }

    private fun linearSearch(keyword: String) {
        try {
            Log.d(TAG_NIM, "Pencarian Linear Search: $keyword")
            val results = mutableListOf<Language>()

            for (item in catalogArray) {
                if (item.name.contains(keyword, ignoreCase = true)) {
                    results.add(item)
                }
            }

            if (results.isNotEmpty()) {
                adapter.updateData(results)
                Log.i(TAG_NIM, "Data ditemukan.")
            } else {
                Toast.makeText(this, "Tidak ditemukan!", Toast.LENGTH_SHORT).show()
                adapter.updateData(catalogArray.toList())
            }
        } catch (e: Exception) {
            Log.e(TAG_NIM, "Error Search: ${e.message}")
        }
    }

    private fun bubbleSort(ascending: Boolean) {
        try {
            val n = catalogArray.size
            for (i in 0 until n - 1) {
                for (j in 0 until n - i - 1) {
                    val compare = catalogArray[j].name.compareTo(catalogArray[j + 1].name, true)
                    val swap = if (ascending) compare > 0 else compare < 0
                    if (swap) {
                        val temp = catalogArray[j]
                        catalogArray[j] = catalogArray[j + 1]
                        catalogArray[j + 1] = temp
                    }
                }
            }
            adapter.updateData(catalogArray.toList())
            Log.i(TAG_NIM, "Bubble Sort Berhasil.")
        } catch (e: Exception) {
            Log.e(TAG_NIM, "Error Sort: ${e.message}")
        }
    }
}