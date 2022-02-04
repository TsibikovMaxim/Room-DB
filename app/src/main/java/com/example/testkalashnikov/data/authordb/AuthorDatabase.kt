package com.example.testkalashnikov.data.authordb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testkalashnikov.data.model.Author

@Database(entities = [Author::class], version = 1, exportSchema = false)
abstract class AuthorDatabase : RoomDatabase() {

    abstract fun authorDao(): AuthorDao

    companion object {
        @Volatile
        private var INSTANCE: AuthorDatabase? = null

        fun getDatabase(context: Context): AuthorDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AuthorDatabase::class.java,
                    "authors_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}