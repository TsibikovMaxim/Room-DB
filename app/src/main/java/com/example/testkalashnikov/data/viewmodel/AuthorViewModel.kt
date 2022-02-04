package com.example.testkalashnikov.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.testkalashnikov.data.authordb.AuthorDatabase
import com.example.testkalashnikov.data.model.Author
import com.example.testkalashnikov.data.repository.AuthorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthorViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Author>>
    private val repository: AuthorRepository

    init {
        val authorDao = AuthorDatabase.getDatabase(application).authorDao()
        repository = AuthorRepository(authorDao)
        readAllData = repository.readAllData
    }

    fun addBook(author: Author){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAuthor(author)
        }
    }

}