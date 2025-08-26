package com.example.gencidev.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gencidev.data.local.BookEntity
import com.example.gencidev.viewmodel.BookViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun BookListScreen(navController: NavController, viewModel: BookViewModel) {
    val books by viewModel.books.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = searchQuery,
            onValueChange = viewModel::onSearchQueryChange,
            label = { Text("Search Books") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(books, key = { it.key }) { book ->
                BookItem(book = book) {
                    val encodedKey = URLEncoder.encode(book.key, StandardCharsets.UTF_8.toString())
                    navController.navigate("detail/$encodedKey")
                }
            }
        }
    }
}

@Composable
fun BookItem(book: BookEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = book.title, style = MaterialTheme.typography.titleMedium)
            book.authorName?.let {
                Text(text = it, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}