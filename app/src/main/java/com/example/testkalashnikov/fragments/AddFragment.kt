package com.example.testkalashnikov.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testkalashnikov.data.model.Book
import com.example.testkalashnikov.data.viewmodel.BookViewModel
import com.example.testkalashnikov.R
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var bookViewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        view.add_author_btn.setOnClickListener{
            insertDataToDB()
        }

        return view
    }

    private fun insertDataToDB() {
        val name = name_add_et.text.toString()
        val description = description_add_et.text.toString()
        val authorid = authorid_add_et.text

        if(inputCheck(name,description,authorid)){
            val book = Book(0,name,description,Integer.parseInt(authorid.toString()))

            //Add
            bookViewModel.addBook(book)
            Toast.makeText(requireContext(), "Успешно добавлено", Toast.LENGTH_SHORT).show()

            //Go back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name : String, description : String, authorid : Editable): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(description) && authorid.isEmpty())
    }
}