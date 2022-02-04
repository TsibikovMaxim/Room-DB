package com.example.testkalashnikov.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authors_table")
class Author(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val birth_date: String,
)