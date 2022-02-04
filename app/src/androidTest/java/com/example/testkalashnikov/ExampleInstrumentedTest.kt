package com.example.testkalashnikov

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testkalashnikov.data.authordb.AuthorDao
import com.example.testkalashnikov.data.authordb.AuthorDatabase
import com.example.testkalashnikov.data.bookdb.BookDao
import com.example.testkalashnikov.data.bookdb.BookDatabase
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private lateinit var querysetBook : BookDao
    private lateinit var databaseBook : BookDatabase
    private lateinit var querysetAuthor : AuthorDao
    private lateinit var databaseAuthor : AuthorDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        databaseBook = Room.inMemoryDatabaseBuilder(context, BookDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        querysetBook = databaseBook.bookDao()

        databaseAuthor= Room.inMemoryDatabaseBuilder(context, AuthorDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        querysetAuthor = databaseAuthor.authorDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        databaseBook.close()
        databaseAuthor.close()
    }

    @Test
    @Throws(Exception::class)
    fun review() {
    }
}