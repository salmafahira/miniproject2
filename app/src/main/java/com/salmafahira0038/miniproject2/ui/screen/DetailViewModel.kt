package com.salmafahira0038.miniproject2.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salmafahira0038.miniproject2.database.FilmDao
import com.salmafahira0038.miniproject2.model.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val dao: FilmDao) : ViewModel() {

    var recentlyDeletedList: Film? = null

    suspend fun getFilm(id: Long): Film? {
        return dao.getFilmById(id)
    }

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

    fun update(id: Long, judul: String, deskripsi: String, tahunRilis: String) {
        val film = Film(
            id = id,
            judul = judul,
            deskripsi = deskripsi,
            tahunRilis = tahunRilis
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(film)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            recentlyDeletedList = dao.getFilmById(id)
            dao.moveToRecycleBin(id)
        }
    }

    fun restoreDeletedFilm() {
        viewModelScope.launch(Dispatchers.IO) {
            recentlyDeletedList?.let { dao.update(it.copy(isDeleted = false)) }
            recentlyDeletedList = null
        }
    }
}
