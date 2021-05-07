package com.anushka.roomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var bookDao: BookDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
                applicationContext,
                BookDatabase::class.java, "book_database"
        ).build()

        bookDao = db.bookDao()
        testDB()

    }

    private fun testDB(){

        lifecycleScope.launch(Dispatchers.IO) {
            //Insert
            Log.i("MyTAG","*****     Inserting 3 Books     **********")
            bookDao.insertBook(Book(0,"Java","Alex"))
            bookDao.insertBook(Book(0,"PHP","Mike"))
            bookDao.insertBook(Book(0,"Kotlin","Amelia"))
            Log.i("MyTAG","*****     Inserted 3 Books       **********")

            //Queery
            val books = bookDao.getAllBooks()
            Log.i("MyTAG","*****   ${books.size} books there *****")
            for(book in books){
                Log.i("MyTAG","id: ${book.id} name: ${book.name} author: ${book.author}")
            }

            //Update
            Log.i("MyTAG","*****      Updating a book      **********")
            bookDao.updateBook(Book(1,"PHPUpdated","Mike"))
            //Queery
            val books2 = bookDao.getAllBooks()
            Log.i("MyTAG","*****   ${books2.size} books there *****")
            for(book in books2){
                Log.i("MyTAG","id: ${book.id} name: ${book.name} author: ${book.author}")
            }

            //delete
            Log.i("MyTAG","*****       Deleting a book      **********")
            bookDao.deleteBook(Book(2,"Kotlin","Amelia"))
            //Query
            val books3 = bookDao.getAllBooks()
            Log.i("MyTAG","*****   ${books3.size} books there *****")
            for(book in books3){
                Log.i("MyTAG","id: ${book.id} name: ${book.name} author: ${book.author}")
            }
        }

    }
}