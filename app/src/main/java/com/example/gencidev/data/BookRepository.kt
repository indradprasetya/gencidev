package com.example.gencidev.data

import com.example.gencidev.data.api.OpenLibraryApiService
import com.example.gencidev.data.local.BookDao
import com.example.gencidev.data.local.BookEntity
import kotlinx.coroutines.flow.Flow

class BookRepository(
    private val apiService: OpenLibraryApiService,
    private val bookDao: BookDao
) {
    val allBooks: Flow<List<BookEntity>> = bookDao.getAllBooks()

    suspend fun refreshBooks(query: String) {
        try {
            val response = apiService.searchBooks(query)
            val entities = response.docs.map { book ->
                BookEntity(
                    key = book.key,
                    title = book.title,
                    authorName = book.author_name?.joinToString(", ")

                )
            }
            bookDao.insertAll(entities)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun searchBooks(query: String): Flow<List<BookEntity>> {
        return bookDao.searchBooks(query)
    }
}
