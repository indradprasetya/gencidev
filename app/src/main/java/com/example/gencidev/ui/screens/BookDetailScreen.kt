package com.example.gencidev.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.gencidev.data.local.BookEntity

@Composable
fun BookDetailScreen(bookKey: String?, books: List<BookEntity>) {
    val book = books.find { it.key == bookKey }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (book != null) {
            Text(book.title, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(book.authorName ?: "No author", style = MaterialTheme.typography.bodyLarge)

            // tampilkan cover kalau ada
            book.coverId?.let { coverId ->
                AsyncImage(
                    model = "https://covers.openlibrary.org/b/id/${coverId}-L.jpg",
                    contentDescription = "Book Cover",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(top = 16.dp)
                )
            } ?: Text(
                text = "Gambar tidak tersedia",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp)
            )

        } else {
            Text("Book not found.")
        }
    }
}
