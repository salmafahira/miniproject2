package com.salmafahira0038.miniproject2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.salmafahira0038.miniproject2.model.Film
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    @Insert
    suspend fun insert(film: Film)

    @Update
    suspend fun update(film: Film)

    @Query("SELECT * FROM film ORDER BY deskripsi DESC")
    fun getFilm(): Flow<List<Film>>

    @Query("SELECT * FROM film WHERE id = :id")
    suspend fun getFilmById(id: Long): Film?

    @Query("DELETE FROM film WHERE id = :id")
    suspend fun deleteById(id: Long)
}