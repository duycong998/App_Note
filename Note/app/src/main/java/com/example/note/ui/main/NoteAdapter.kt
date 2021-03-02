package com.example.note.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.note.data.model.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var listNote: MutableList<Note> = mutableListOf()
    private var onItemClickListener: OnItemClick<Note>? = null

    fun registerItemRecyclerViewClickListener(
        onItemRecyclerViewClickListener: OnItemClick<Note>?
    ) {
        onItemClickListener = onItemRecyclerViewClickListener
    }

    fun updateNote(notes: MutableList<Note>) {
        notes?.let {
            this.listNote.clear()
            this.listNote.addAll(notes)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(listNote[position])
    }

    override fun getItemCount() = listNote.size

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewTitle = itemView.findViewById<TextView>(R.id.text_view_item_title)
        private val textViewDescription =
            itemView.findViewById<TextView>(R.id.text_view_item_description)
        private val imageDelete = itemView.findViewById<ImageView>(R.id.image_item_delete)
        private val imageEdit = itemView.findViewById<ImageView>(R.id.image_item_edit)

        fun onBind(note: Note) {
            textViewDescription.text = note.mDescription
            textViewTitle.text = note.mTitle

            imageDelete.setOnClickListener {
                onItemClickListener!!.onDelete(note)
                Log.d("AAA", "delete")
            }

            imageEdit.setOnClickListener {
                onItemClickListener!!.onEdit(note)
                Log.d("AAA", "edit")
            }
        }
    }

    interface OnItemClick<T> {
        fun onDelete(note: Note)
        fun onEdit(note: Note)
    }
}
