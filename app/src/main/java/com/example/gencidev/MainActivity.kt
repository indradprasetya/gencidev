package com.example.gencidev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gencidev.ui.screens.BookDetailScreen
import com.example.gencidev.ui.screens.BookListScreen
import com.example.gencidev.ui.theme.GencidevTheme
import com.example.gencidev.viewmodel.BookViewModel
import com.example.gencidev.viewmodel.BookViewModelFactory

import java.net.URLDecoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GencidevTheme {
                val navController: NavHostController = rememberNavController()
                val viewModel: BookViewModel = viewModel(factory = BookViewModelFactory(this))
                val allBooks by viewModel.books.collectAsState(initial = emptyList())

                Scaffold { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "list",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("list") {
                            BookListScreen(navController = navController, viewModel = viewModel)
                        }
                        composable("detail/{bookKey}") { backStackEntry ->
                            val encodedKey = backStackEntry.arguments?.getString("bookKey")
                            val bookKey = encodedKey?.let {
                                try {
                                    URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
                                } catch (e: Exception) {
                                    null
                                }
                            }

                            BookDetailScreen(
                                bookKey = bookKey,
                                books = allBooks
                            )
                        }
                    }
                }
            }
        }
    }
}

