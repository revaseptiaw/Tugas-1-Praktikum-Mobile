package com.example.prakmobileif1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prakmobileif1.data.model.BookDoc
import com.example.prakmobileif1.databinding.ActivityDaftarBukuBinding
import com.example.prakmobileif1.ui.adapter.BookAdapter
import com.example.prakmobileif1.ui.fragment.BookDetailFragment
import com.example.prakmobileif1.viewmodel.MainViewModel

class DaftarBukuActivity : AppCompatActivity(), BookAdapter.OnBookClickListener {

    private lateinit var binding: ActivityDaftarBukuBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi adapter
        adapter = BookAdapter(emptyList(), this)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Observe data dari ViewModel
        viewModel.books.observe(this) { books ->
            adapter.setData(books)
        }

        viewModel.fetchBooks(query = "kotlin programming")
    }

    override fun onBookClick(book: BookDoc) {
        val fragment = BookDetailFragment(
            title = book.title ?: "No Title",
            author = book.authorName?.joinToString(", ") ?: "Unknown Author",
            year = book.firstPublishYear?.toString() ?: "-",
            coverId = book.coverId ?: 0
        )

        fragment.show(supportFragmentManager, BookDetailFragment::class.java.simpleName)
    }
}
