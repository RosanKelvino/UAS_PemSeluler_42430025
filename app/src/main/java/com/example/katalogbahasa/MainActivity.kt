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
            Language(
                "Python",
                "Data Science & AI",
                R.drawable.python,
                "Python adalah bahasa pemrograman tingkat tinggi yang diciptakan oleh Guido van Rossum dan pertama kali dirilis pada tahun 1991. Filosofi desain Python sangat menekankan pada keterbacaan kode, menggunakan sintaks yang bersih dan sangat mirip dengan bahasa Inggris manusia.\n\nKeunggulan utama Python adalah komunitasnya yang masif dan pustaka (library) pendukung yang sangat melimpah. Hal ini menjadikannya 'raja' di bidang Data Science, Machine Learning, dan Artificial Intelligence (AI).\n\nSelain itu, Python juga sering digunakan untuk pengembangan Web backend (menggunakan framework seperti Django atau Flask), otomatisasi tugas (scripting), hingga keamanan siber (cybersecurity).\n\nContoh Kode 'Hello World':\nprint(\"Hello, World!\")"
            ),
            Language(
                "Java",
                "Android & Enterprise",
                R.drawable.java,
                "Java adalah bahasa pemrograman berorientasi objek (OOP) tingkat tinggi yang dikembangkan oleh Sun Microsystems pada tahun 1995. Java terkenal dengan slogannya 'Write Once, Run Anywhere' (WORA), yang berarti kode Java dapat dijalankan di perangkat apa saja asalkan memiliki Java Virtual Machine (JVM).\n\nKelebihan utama Java adalah tingkat keamanannya yang tinggi, manajemen memori yang solid, dan kemampuannya menangani proyek berskala besar dengan sangat stabil.\n\nJava sangat populer digunakan untuk membangun sistem backend perusahaan berskala raksasa (enterprise apps), sistem perbankan, aplikasi desktop, dan merupakan bahasa klasik dalam pengembangan aplikasi Android sebelum kehadiran Kotlin.\n\nContoh Kode 'Hello World':\npublic class Main {\n    public static void main(String[ ] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}"
            ),
            Language(
                "Kotlin",
                "Modern Android",
                R.drawable.kotlin,
                "Kotlin adalah bahasa pemrograman modern berorientasi objek yang dikembangkan oleh JetBrains (perusahaan pembuat Android Studio). Dirilis pertama kali pada tahun 2011, Kotlin dirancang untuk bisa beroperasi sepenuhnya (100% interoperable) dengan kode Java yang sudah ada.\n\nSatu hal yang paling disukai developer dari Kotlin adalah fitur 'Null Safety', yang secara efektif mengeliminasi error NullPointerException yang sering membuat aplikasi Java crash.\n\nSejak tahun 2019, Google telah mengumumkan bahwa Kotlin adalah bahasa utama (First-Class) dan paling direkomendasikan untuk pengembangan aplikasi Android modern. Sintaksnya yang jauh lebih ringkas membuat developer bisa menulis kode lebih sedikit dan lebih rapi.\n\nContoh Kode 'Hello World':\nfun main() {\n    println(\"Hello, World!\")\n}"
            ),
            Language(
                "C++",
                "Game Development",
                R.drawable.cpp,
                "C++ adalah bahasa pemrograman tingkat menengah yang diciptakan oleh Bjarne Stroustrup sebagai ekstensi dari bahasa C. Bahasa ini menggabungkan kontrol tingkat rendah terhadap perangkat keras (seperti memori dan prosesor) dengan fitur berorientasi objek.\n\nKekuatan absolut dari C++ terletak pada performanya yang sangat tinggi dan waktu eksekusi yang sangat cepat. Namun, sebagai gantinya, kurva pembelajarannya cukup curam karena developer harus mengelola memori secara manual.\n\nC++ adalah standar industri tidak tertulis untuk pembuatan Game skala besar (Triple-A) melalui Unreal Engine, pengembangan sistem operasi, perangkat lunak simulasi real-time, dan sistem embedded (perangkat tertanam).\n\nContoh Kode 'Hello World':\n#include <iostream>\n\nint main() {\n    std::cout << \"Hello, World!\" << std::endl;\n    return 0;\n}"
            ),
            Language(
                "Ruby",
                "Web Development",
                R.drawable.ruby,
                "Ruby adalah bahasa pemrograman dinamis dan open-source yang diciptakan oleh Yukihiro Matsumoto di Jepang. Filosofi utama Ruby cukup unik: berfokus pada kebahagiaan developer dan produktivitas, bukan sekadar efisiensi mesin.\n\nKode Ruby sangat elegan, ekspresif, dan bisa dibaca hampir seperti kalimat naratif. Popularitas Ruby meledak berkat framework web bernama 'Ruby on Rails' (RoR).\n\nDengan RoR, developer bisa membangun dan meluncurkan sistem website atau startup dari nol hingga siap pakai dengan kecepatan yang luar biasa. Banyak perusahaan raksasa seperti GitHub, Shopify, dan Airbnb awalnya dibangun menggunakan teknologi ini.\n\nContoh Kode 'Hello World':\nputs \"Hello, World!\""
            ),
            Language(
                "Swift",
                "iOS Development",
                R.drawable.swift,
                "Swift adalah bahasa pemrograman tangguh, cepat, dan intuitif yang diciptakan secara eksklusif oleh Apple Inc. pada tahun 2014. Swift dirancang untuk menggantikan bahasa pendahulunya, Objective-C, yang dinilai sudah terlalu tua dan sulit dibaca.\n\nSwift menggabungkan konsep dari berbagai bahasa modern, menghasilkan bahasa yang lebih aman, mengeksekusi kode lebih cepat, dan sangat menyenangkan untuk ditulis. Bahasa ini juga memiliki sistem pengelolaan memori otomatis (ARC).\n\nJika kamu ingin membuat aplikasi untuk iPhone (iOS), MacBook (macOS), Apple Watch (watchOS), atau Apple TV (tvOS), maka Swift adalah satu-satunya bahasa yang wajib kamu kuasai secara mendalam.\n\nContoh Kode 'Hello World':\nprint(\"Hello, World!\")"
            ),
            Language(
                "JavaScript",
                "Web & Frontend",
                R.drawable.javascript,
                "JavaScript adalah bahasa pemrograman inti penyusun dunia website modern, berdampingan dengan HTML dan CSS. Jika HTML adalah kerangka dan CSS adalah pakaian, maka JavaScript adalah otot yang membuat sebuah website bisa bergerak dan berinteraksi dengan pengguna.\n\nSaat ini, JavaScript adalah salah satu bahasa dengan ekosistem terbesar di dunia. Awalnya JavaScript hanya bisa berjalan di browser (Frontend), namun kini sudah sangat berevolusi.\n\nDengan adanya teknologi Node.js, JavaScript kini bisa digunakan untuk membangun server (Backend). Bahkan dengan framework seperti React Native, JavaScript bisa digunakan untuk membuat aplikasi mobile.\n\nContoh Kode 'Hello World':\nconsole.log(\"Hello, World!\");"
            ),
            Language(
                "Rust",
                "System Programming",
                R.drawable.rust,
                "Rust adalah bahasa pemrograman sistem modern yang berfokus pada tiga hal utama: kecepatan, keamanan memori, dan konkurensi (menjalankan banyak tugas bersamaan). Rust sering kali dinobatkan sebagai 'Bahasa yang Paling Dicintai' oleh komunitas developer selama bertahun-tahun berturut-turut.\n\nInovasi terbesar Rust adalah sistem 'Ownership'. Sistem ini menjamin keamanan memori tanpa perlu bantuan Garbage Collector, sehingga aplikasi tidak akan mengalami kebocoran memori (memory leak) dan tetap berjalan secepat kilat (mirip C++).\n\nRust sering digunakan untuk membangun inti sistem operasi, browser engine, teknologi WebAssembly, hingga jaringan Blockchain yang membutuhkan keamanan dan performa super tinggi.\n\nContoh Kode 'Hello World':\nfn main() {\n    println!(\"Hello, World!\");\n}"
            ),
            Language(
                "Go",
                "Cloud & Backend",
                R.drawable.go,
                "Go (sering disebut Golang) adalah bahasa pemrograman open-source yang dikembangkan oleh Google pada tahun 2009. Go dirancang dengan memikirkan infrastruktur komputasi modern: multi-core processor, jaringan besar, dan arsitektur cloud.\n\nKekuatan utama Go adalah kesederhanaan sintaksnya dan fitur 'Goroutines'. Fitur ini memungkinkan aplikasi menjalankan jutaan proses secara bersamaan (konkuren) dengan penggunaan memori yang sangat sangat kecil.\n\nBerkat performanya yang luar biasa efisien, Go menjadi bahasa favorit untuk membangun sistem backend berskala raksasa, arsitektur Microservices, dan teknologi Cloud computing (bahkan teknologi populer seperti Docker dan Kubernetes ditulis dengan Go).\n\nContoh Kode 'Hello World':\npackage main\n\nimport \"fmt\"\n\nfunc main() {\n    fmt.Println(\"Hello, World!\")\n}"
            ),
            Language(
                "Dart",
                "Cross-platform Mobile",
                R.drawable.i_dart,
                "Dart adalah bahasa pemrograman yang dioptimalkan untuk pengembangan antarmuka pengguna (UI) di berbagai platform, dikembangkan oleh Google. Dart memiliki sintaks yang familiar, mirip seperti perpaduan antara Java dan JavaScript.\n\nDart memiliki dua mode kompilasi: JIT (Just-In-Time) yang memungkinkan fitur 'Hot Reload' agar developer bisa melihat perubahan kode secara instan saat ngoding, dan AOT (Ahead-Of-Time) agar aplikasi berjalan sangat mulus dan cepat saat dirilis ke publik.\n\nPopularitas Dart meroket tajam seiring dengan booming-nya framework Flutter. Dengan kombinasi Dart dan Flutter, developer bisa membuat aplikasi Android, iOS, Web, dan Desktop sekaligus, hanya dengan menulis satu basis kode saja.\n\nContoh Kode 'Hello World':\nvoid main() {\n    print('Hello, World!');\n}"
            )
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