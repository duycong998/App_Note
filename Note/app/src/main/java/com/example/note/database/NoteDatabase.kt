package com.example.note.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.note.data.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao

    companion object {
        //volatile là biến lưu trong bộ nhớ chính nên mỗi lần đọc hay ghi thì nó thực hiện trên bộ nhớ chính chứ kh phải bộ nhớ đệm
        @Volatile
        private var instance: NoteDatabase? = null

        fun getNoteDatabase(context: Context) : NoteDatabase {
            if(instance == null) {
                instance = Room.databaseBuilder(context, NoteDatabase::class.java, "NoteDatabase").build()
            }
            return instance!!
        }
    }
}
