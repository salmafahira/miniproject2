package com.salmafahira0038.miniproject2.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salmafahira0038.miniproject2.database.FilmDao
import com.salmafahira0038.miniproject2.model.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecycleBinViewModel(private val dao: FilmDao) : ViewModel() {

    // Perbaiki nama fungsi menjadi getDeletedFilm()
    val deletedData: StateFlow<List<Film>> = dao.getDeletedFilm().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    // Perbaiki nama fungsi menjadi restoreToById()
    fun restoreFilm(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.restoreToById(id)
        }
    }

    // Perbaiki nama fungsi menjadi emptyREcycleBin()
    fun permanentDeleteFilm(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }


    fun emptyRecycleBin() {
        viewModelScope.launch(Dispatchers.IO) {
            dao.emptyRecycleBin()
        }
    }
}
