package com.example.myapplication1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    // ViewHolder menyimpan referensi ke tampilan untuk setiap item
    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textViewTitle)
        val body: TextView = itemView.findViewById(R.id.textViewBody)
    }

    // Membuat ViewHolder baru (saat pertama kali item ditampilkan)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        // Mengembangkan (inflate) layout item_post.xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    // Menghubungkan data ke ViewHolder (untuk item yang sudah ada)
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.title.text = post.title
        holder.body.text = post.body
    }

    // Mengembalikan jumlah total item dalam daftar
    override fun getItemCount(): Int = posts.size
}