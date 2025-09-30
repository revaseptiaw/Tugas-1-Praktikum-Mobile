package com.example.prakmobileif1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prakmobileif1.databinding.ActivityDaftarBukuBinding
import com.example.prakmobileif1.ui.adapter.BookAdapter
import com.example.prakmobileif1.viewmodel.MainViewModel

class DaftarBukuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDaftarBukuBinding

    private val viewModel: MainViewModel by viewModels()

    private val adapter = BookAdapter(books = emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDaftarBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.books.observe(this) { books ->
            adapter.setData(books)
        }

        viewModel.fetchBooks(query = "kotlin programming")
    }
}
