package com.example.note.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.note.data.model.Note
import com.example.note.data.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app: Application) : ViewModel() {
    private val noteRepository: NoteRepository = NoteRepository(app)

    fun insertNote(note: Note) = viewModelScope.launch {
        noteRepository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

    fun getAllNote(): LiveData<MutableList<Note>> = noteRepository.getAllNote()

    fun getAllNoteByTitle(name: String): LiveData<MutableList<Note>> =
        noteRepository.getAllNoteByTitle(name)

    //viewmodel không có đối số truyền vào nên khi khởi tạo bị lỗi nên
    class NoteViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NoteViewModel(app) as T
            }
            throw IllegalAccessException("Unable construct viewModel")
        }
    }
}
