package com.example.gencidev.di

import android.content.Context
import androidx.room.Room
import com.example.gencidev.data.BookRepository
import com.example.gencidev.data.api.OpenLibraryApiService
import com.example.gencidev.data.local.AppDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {
    fun provideApiService(): OpenLibraryApiService {
        return Retrofit.Builder()
            .baseUrl("https://openlibrary.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenLibraryApiService::class.java)
    }

    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "books.db"
        ).build()
    }

    fun provideRepository(context: Context): BookRepository {
        return BookRepository(
            provideApiService(),
            provideDatabase(context).bookDao()
        )
    }
}
