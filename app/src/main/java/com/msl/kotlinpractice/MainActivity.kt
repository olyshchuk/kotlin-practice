package com.msl.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.msl.kotlinpractice.adapter.BookListAdapter
import com.msl.kotlinpractice.model.BookListModel
import com.msl.kotlinpractice.viewmodel.BookViewModel
import io.reactivex.Observer

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:  BookViewModel
    private lateinit var bookListAdapter: BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadAPIData()
        initRecyclerView()
    }

    private fun initSearchBox() {
        val inputBookName = findViewById<EditText>(R.id.inputBookName)
        inputBookName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loadAPIData()
            }
        })
    }

    private fun initRecyclerView(){

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration  = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(decoration)
            bookListAdapter = BookListAdapter()
            adapter =bookListAdapter
        }
    }

    fun loadAPIData() {
        viewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        viewModel.getBookListObserver().observe(this, {
            if(it != null) {
                //update adapter...
                bookListAdapter.bookListData = it.items
                bookListAdapter.notifyDataSetChanged()
            }
            else {
                Toast.makeText(this, "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.getCallFromApi()
    }
}