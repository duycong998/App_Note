package com.example.note.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.note.data.model.Note
import com.example.note.databinding.ItemNoteBinding

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var listNote: MutableList<Note> = mutableListOf()
    private var onItemClickListener: OnItemClick<Note>? = null

    fun registerItemRecyclerViewClickListener(
        onItemRecyclerViewClickListener: OnItemClick<Note>?
    ) {
        onItemClickListener = onItemRecyclerViewClickListener
    }

    fun updateNote(notes: MutableList<Note>) {
        notes.let {
            this.listNote.clear()
            this.listNote.addAll(notes)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val userItemBind = ItemNoteBinding.inflate(inflater, parent, false)
        return NoteViewHolder(userItemBind)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(listNote[position])
    }

    override fun getItemCount() = listNote.size

    inner class NoteViewHolder(private val itemNoteBinding: ItemNoteBinding) :
        RecyclerView.ViewHolder(itemNoteBinding.root) {

        fun onBind(note: Note) {
            itemNoteBinding.note = note

            itemNoteBinding.imageItemDelete.setOnClickListener {
                onItemClickListener!!.onDelete(note)
            }

            itemNoteBinding.imageItemEdit.setOnClickListener {
                onItemClickListener!!.onEdit(note)
            }
        }
    }

    interface OnItemClick<T> {
        fun onDelete(note: Note)
        fun onEdit(note: Note)
    }
}
