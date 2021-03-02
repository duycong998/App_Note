package com.example.note.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.note.data.model.Note

@Dao
interface NoteDao {
    //suspend chạy đa luồng đa tiến trình trên room nhờ coroutine
    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    // livedata
    @Query("SELECT * FROM note_table")
    fun getAllNote(): LiveData<MutableList<Note>>

    @Query("SELECT * FROM note_table WHERE m_title = :title ")
    fun getNoteByTitle(title: String): LiveData<MutableList<Note>>
}
