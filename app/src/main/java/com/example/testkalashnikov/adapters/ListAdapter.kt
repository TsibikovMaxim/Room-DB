package com.example.testkalashnikov.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testkalashnikov.data.model.Book
import com.example.testkalashnikov.R
import com.example.testkalashnikov.fragments.ListFragmentDirections
import kotlinx.android.synthetic.main.book_item.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.ItemHolder>(){

    private var bookList = emptyList<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.book_item,parent,false)
        return ItemHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val currentItem = bookList[position]
        holder.itemView.book_item_tv.text = currentItem.name

        holder.itemView.book_item.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){ }

    fun setData(book : List<Book>) {
        this.bookList = book
        notifyDataSetChanged()
    }
}