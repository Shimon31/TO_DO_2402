package com.example.todolist2402

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.todolist2402.databinding.FragmentAddBinding
import java.util.Calendar


class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding
    var showTime: String? = null
    var showDate: String? = null


    lateinit var database: NoteDataBase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)


        database = Room.databaseBuilder(requireActivity(), NoteDataBase::class.java, "Note-DB")
            .allowMainThreadQueries().build()



        binding.dateBtn.setOnClickListener {

            pickADate()

        }

        binding.timeBtn.setOnClickListener {

            pickATime()
        }

        binding.submitBtn.setOnClickListener {
            val titleStr = binding.textTV.text.toString()
            val timeStr = showTime ?: "00:00"
            val dateStr = showDate ?: "00/00/0000"

            val note = Note(title = titleStr, time = timeStr, date = dateStr)
            database.getNoteDao().insertData(note)

            findNavController().navigate(R.id.action_addFragment_to_homeFragment)

        }

        return binding.root
    }

    private fun pickATime() {
        val calendar = Calendar.getInstance()

        val minute = calendar.get(Calendar.MINUTE)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        val timePicker = TimePickerDialog(
            requireActivity(), TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->

                showTime = "$hourOfDay : $minute"
                binding.timeBtn.text = showTime


            }, hour, minute, false

        )
        timePicker.show()


    }


    private fun pickADate() {

        val calendar = Calendar.getInstance()

        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        val showDatePicker = DatePickerDialog(
            requireActivity(),
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->

                showDate = "$dayOfMonth/${month + 1}/$year"
                binding.dateBtn.text = showDate

            }, year, month, day
        )

        showDatePicker.show()

    }


}