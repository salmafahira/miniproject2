package com.salmafahira0038.miniproject2.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salmafahira0038.miniproject2.database.FilmDao
import com.salmafahira0038.miniproject2.model.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val dao: FilmDao) : ViewModel() {


    fun insert(judul: String, deskripsi: String, tahunRilis: String) {
        val film = Film(
            judul = judul,
            deskripsi = deskripsi,
            tahunRilis = tahunRilis
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(film)
        }
    }

    fun getFilm(id: Long): Film? {
        return null
    }
}