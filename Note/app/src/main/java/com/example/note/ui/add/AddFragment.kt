package com.example.note.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.note.R
import com.example.note.data.model.Note
import com.example.note.databinding.FragmentAddBinding
import com.example.note.ui.main.MainFragment
import com.example.note.ui.main.NoteViewModel
import com.example.note.utils.replace
import kotlinx.android.synthetic.main.fragment_add.*
import java.util.concurrent.Executor

class AddFragment : Fragment() {
    private var binding: FragmentAddBinding? = null
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            NoteViewModel.NoteViewModelFactory(requireActivity().application)
        )[NoteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false)
        binding!!.addFragment = this
        return binding!!.root
    }

    fun onClickAdd() {
        val note =
            Note(edit_text_add_title.text.toString(), edit_text_add_description.text.toString())
        noteViewModel.insertNote(note)
        replace(MainFragment(), R.id.container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    companion object {
        fun newInstance() = AddFragment()
    }
}
