package com.example.note.ui.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.note.R
import com.example.note.data.model.Note
import com.example.note.ui.main.MainFragment
import com.example.note.ui.main.NoteViewModel
import com.example.note.utils.replace
import kotlinx.android.synthetic.main.fragment_update.*

class UpdateFragment : Fragment() {
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            NoteViewModel.NoteViewModelFactory(requireActivity().application)
        )[NoteViewModel::class.java]
    }
    private var note: Note? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        note = arguments?.getParcelable("note")
        val title = view.findViewById<EditText>(R.id.edit_text_update_title)
        val description = view.findViewById<EditText>(R.id.edit_text_update_description)
        title.setText(note!!.mTitle)
        description.setText(note!!.mDescription)

        button_update.setOnClickListener {
            note!!.mTitle = title.text.toString()
            note!!.mDescription = description.text.toString()
            noteViewModel.updateNote(note!!)
            replace(MainFragment(), R.id.container)
        }
    }

    companion object {

        fun newInstance(note: Note) = UpdateFragment().apply {
            arguments = bundleOf("note" to note)
        }
    }
}
