# 📚 Gencidev Book App

Aplikasi Android sederhana menggunakan **Jetpack Compose**, **Room**, dan **Retrofit** untuk menampilkan daftar buku dari [OpenLibrary API](https://openlibrary.org/developers/api).

## ✨ Fitur
- Menampilkan daftar buku berdasarkan query default `"android"` kemudian disimpan ke database local dengan room.
- Pencarian buku **lokal** (dari database Room).
- Menyimpan data buku secara offline menggunakan **Room Database**.
- Menampilkan detail buku (judul dan penulis).

## 🛠️ Tech Stack
- **Kotlin**
- **Jetpack Compose** (UI)
- **Navigation Component** (navigasi antar screen)
- **Retrofit2** (panggilan API)
- **Room Database** (penyimpanan lokal)
- **Coroutines + Flow** (async dan reactive stream)
- **MVVM Architecture** (ViewModel, Repository, DAO)

