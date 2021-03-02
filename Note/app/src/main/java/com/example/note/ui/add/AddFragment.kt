package com.example.note.ui.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.note.R
import com.example.note.data.model.Note
import com.example.note.ui.main.MainFragment
import com.example.note.ui.main.NoteViewModel
import com.example.note.utils.replace
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment(R.layout.fragment_add) {

    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            NoteViewModel.NoteViewModelFactory(requireActivity().application)
        )[NoteViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_add_fragment.setOnClickListener {
            val note =
                Note(edit_text_add_title.text.toString(), edit_text_add_description.text.toString())
            noteViewModel.insertNote(note)
            replace(MainFragment(), R.id.container)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    companion object {
        fun newInstance() = AddFragment()
    }
}
