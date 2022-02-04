package com.example.testkalashnikov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.testkalashnikov.data.model.Author
import com.example.testkalashnikov.data.viewmodel.AuthorViewModel
import com.example.testkalashnikov.data.viewmodel.BookViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragment))

        addAuthors()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    //Функция только для тестовой версии. Програмно добавляет 3 автора в базу данных
    private fun addAuthors(){
        val author1 = Author(1,"Иван Толстой", "1828.09.09")
        val author2 = Author(2,"Александр Грибоедов", "1975.01.15")
        val author3 = Author(3,"Эрих Мария Ремарк", "1898.06.22")

        val authorViewModel = ViewModelProvider(this)[AuthorViewModel::class.java]
        authorViewModel.addBook(author1)
        authorViewModel.addBook(author2)
        authorViewModel.addBook(author3)
    }
}