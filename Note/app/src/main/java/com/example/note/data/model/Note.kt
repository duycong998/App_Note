package com.example.note.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "note_table")
@Parcelize
data class Note(
    @ColumnInfo(name = "m_title") var mTitle: String = "abc",
    @ColumnInfo(name = "m_description") var mDescription: String = "xzr"
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = " m_id")
    var mId: Int = 0
}
