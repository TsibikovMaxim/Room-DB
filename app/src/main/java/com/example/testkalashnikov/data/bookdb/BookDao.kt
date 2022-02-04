package com.example.testkalashnikov.data.bookdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.testkalashnikov.data.model.Book

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBook(book: Book)

    @Update
    suspend fun updateBook(book: Book)

    @Query("SELECT * FROM books_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Book>>

}