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

class HomeFragment : Fragment() {

lateinit var binding: FragmentHomeBinding

    lateinit var database: NoteDataBase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)


        database = Room.databaseBuilder(requireActivity(), NoteDataBase::class.java, "Note-DB")
            .allowMainThreadQueries().build()

       val notes : List<Note> = database.getNoteDao().getAllData()

           val adapter = NoteAdapter()
           adapter.submitList(notes)


           binding.recyclerView.adapter = adapter


        binding.addBtn.setOnClickListener {


            findNavController().navigate(R.id.action_homeFragment_to_addFragment)

        }

        return binding.root
    }

}