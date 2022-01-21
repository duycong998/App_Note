package com.example.note.ui.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.note.R
import com.example.note.data.model.Note
import com.example.note.databinding.FragmentUpdateBinding
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
    private var binding: FragmentUpdateBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update, container, false)
        binding!!.updateFragment = this
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        note = arguments?.getParcelable("note")
        clickUpdateNote()
    }

    private fun clickUpdateNote() {
        binding?.note = note
        button_update.setOnClickListener {
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
