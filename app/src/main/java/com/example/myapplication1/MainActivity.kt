package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Menginisialisasi RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 2. Memanggil fungsi untuk mengambil data
        fetchDataFromApi()
    }

    private fun fetchDataFromApi() {
        // Menggunakan Coroutine untuk menjalankan operasi jaringan di latar belakang
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Panggil API menggunakan Retrofit
                val response = ApiClient.apiService.getPosts()

                // Periksa apakah respons berhasil
                if (response.isSuccessful) {
                    val posts = response.body()

                    // Kembali ke thread utama (UI) untuk memperbarui tampilan
                    runOnUiThread {
                        if (posts != null) {
                            // 3. Menghubungkan data ke adapter dan RecyclerView
                            postAdapter = PostAdapter(posts)
                            recyclerView.adapter = postAdapter
                        }
                    }
                }
            } catch (e: Exception) {
                // Tangani error, misalnya menampilkan pesan Toast
                // Log.e("MainActivity", "Error fetching data: ${e.message}")
            }
        }
    }
}