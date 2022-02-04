package com.example.testkalashnikov.data.authordb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testkalashnikov.data.model.Author

@Dao
interface AuthorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAuthor(author: Author)

    @Query("SELECT * FROM authors_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Author>>

}