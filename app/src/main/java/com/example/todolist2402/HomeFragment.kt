package com.example.todolist2402

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.todolist2402.databinding.FragmentHomeBinding

class HomeFragment : Fragment(),NoteAdapter.NoteEdit {

lateinit var binding: FragmentHomeBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)


       val notes : List<Note> = NoteDataBase.getDb(requireContext()).getNoteDao().getAllData()


        notes.let {

            val adapter = NoteAdapter(this)
            adapter.submitList(notes)


            binding.recyclerView.adapter = adapter

        }




        binding.addBtn.setOnClickListener {


            findNavController().navigate(R.id.action_homeFragment_to_addFragment)

        }

        return binding.root
    }

    override fun onNoteEdit(note: Note) {

        var bundle = Bundle()
        bundle.putInt("note",note.id)

        findNavController().navigate(R.id.action_homeFragment_to_addFragment,bundle)

    }

}