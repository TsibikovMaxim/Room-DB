package com.example.testkalashnikov.data.repository

import androidx.lifecycle.LiveData
import com.example.testkalashnikov.data.authordb.AuthorDao
import com.example.testkalashnikov.data.model.Author

class AuthorRepository(private val authorDao: AuthorDao) {

    val readAllData: LiveData<List<Author>> = authorDao.readAllData()

    suspend fun addAuthor(author: Author){
        authorDao.addAuthor(author)
    }

}