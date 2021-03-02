package com.example.note.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.note.database.NoteDao
import com.example.note.database.NoteDatabase
import com.example.note.data.model.Note

class NoteRepository(app: Application) {
    private val noteDao: NoteDao

    // hàm gọi sau constructor
    init {
        val noteDatabase: NoteDatabase = NoteDatabase.getNoteDatabase(app)
        noteDao = noteDatabase.noteDao()
    }

    suspend fun insertNote(note: Note) = noteDao.insertNote(note)
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
    suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    fun getAllNote(): LiveData<MutableList<Note>> = noteDao.getAllNote()

    fun getAllNoteByTitle(name: String): LiveData<MutableList<Note>> = noteDao.getNoteByTitle(name)
}
