package com.example.testkalashnikov.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testkalashnikov.R
import com.example.testkalashnikov.data.model.Author
import com.example.testkalashnikov.data.model.Book
import com.example.testkalashnikov.data.viewmodel.AuthorViewModel
import com.example.testkalashnikov.data.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var bookViewModel : BookViewModel
    private lateinit var authorViewModel : AuthorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        authorViewModel = ViewModelProvider(this).get(AuthorViewModel::class.java)

        view.name_update_et.setText(args.currentBook.name)
        view.description_update_et.setText(args.currentBook.description)
        view.authorid_update_et.setText(args.currentBook.authorId.toString())

        //set author
        authorViewModel.readAllData.observe(viewLifecycleOwner, Observer { author ->
            try{
                author_name_tv.text = author[args.currentBook.authorId-1].name
                author_date_tv.text = author[args.currentBook.authorId-1].birth_date
            }catch (e: Exception){
                Toast.makeText(requireContext(), "Автора с таким номером нет", Toast.LENGTH_SHORT).show()
            }
        })

        view.update_author_btn.setOnClickListener{
            updateBook()
        }

        return view
    }

    private fun updateBook(){
        val name = name_update_et.text.toString()
        val description = description_update_et.text.toString()
        val authorId = Integer.parseInt(authorid_update_et.text.toString())

        if(inputCheck(name,description,authorid_update_et.text)){
            val updatedBook = Book(args.currentBook.id,name,description,authorId)

            //Update
            bookViewModel.updateBook(updatedBook)
            Toast.makeText(requireContext(), "Успешно обновлено", Toast.LENGTH_SHORT).show()

            //Go back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name : String, description : String, authorid : Editable): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(description) && authorid.isEmpty())
    }
}