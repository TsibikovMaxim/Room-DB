package com.example.testkalashnikov.data.repository

import androidx.lifecycle.LiveData
import com.example.testkalashnikov.data.bookdb.BookDao
import com.example.testkalashnikov.data.model.Book

class BookRepository(private val userDao: BookDao) {

    val readAllData: LiveData<List<Book>> = userDao.readAllData()

    suspend fun addBook(book: Book){
        userDao.addBook(book)
    }

    suspend fun updateBook(book: Book){
        userDao.updateBook(book)
    }
}