package com.example.testkalashnikov.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.testkalashnikov.data.bookdb.BookDatabase
import com.example.testkalashnikov.data.repository.BookRepository
import com.example.testkalashnikov.data.model.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Book>>
    private val repository: BookRepository

    init {
        val boookDao = BookDatabase.getDatabase(application).bookDao()
        repository = BookRepository(boookDao)
        readAllData = repository.readAllData
    }

    fun addBook(book: Book){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBook(book)
        }
    }

    fun updateBook(book: Book){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBook(book)
        }
    }
}