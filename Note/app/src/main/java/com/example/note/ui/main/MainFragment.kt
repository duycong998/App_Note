package com.example.note.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.note.data.model.Note
import com.example.note.ui.add.AddFragment
import com.example.note.ui.update.UpdateFragment
import com.example.note.utils.replace
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main), NoteAdapter.OnItemClick<Note> {
    private val adapter by lazy { NoteAdapter() }
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            NoteViewModel.NoteViewModelFactory(requireActivity().application)
        )[NoteViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        //  initData()
        initEvent()
    }

    private fun initEvent() {
        add_button.setOnClickListener {
            replace(AddFragment(), R.id.container)
        }
    }

    private fun initData() {

    }

    private fun initView() {
        val recyclerviewNote = view!!.findViewById<RecyclerView>(R.id.recyclerViewNote)
        recyclerviewNote.setHasFixedSize(true)
        recyclerviewNote.adapter = adapter
        adapter.registerItemRecyclerViewClickListener(this)

        noteViewModel.getAllNote().observe(requireActivity(), {
            adapter.updateNote(it)
        })
    }

    override fun onDelete(note: Note) {
        noteViewModel.deleteNote(note)
//        noteViewModel.getAllNote().observe(requireActivity(), {
//            adapter.updateNote(it)
//        })
    }

    override fun onEdit(note: Note) {
        replace(UpdateFragment.newInstance(note), R.id.container)
    }
}
