package com.anushka.roomapp

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert
    suspend fun insertBook(book: Book)

    @Query("SELECT * FROM books_table")
    fun getAllBooks(): List<Book>

    @Update
    suspend fun updateBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)
}