package com.example.todolist2402

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolist2402.databinding.FragmentAddBinding
import java.util.Calendar


class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)


        binding.dateBtn.setOnClickListener {

            pickADate()

        }

        binding.timeBtn.setOnClickListener {

            pickATime()

        }

        return binding.root
    }

    private fun pickATime() {

        val calendar = Calendar.getInstance()

        val minute = calendar.get(Calendar.MINUTE)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        val timePickerDialog = TimePickerDialog(
            requireActivity(), TimePickerDialog.OnTimeSetListener { _, hour,minute ->


                val showTime = "${hour+1}: $minute"

                binding.timeBtn.text = showTime


            }, hour, minute, false

        )

        timePickerDialog.show()


    }

    private fun pickADate() {

        val calendar = Calendar.getInstance()

        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        val showDatePicker = DatePickerDialog(
            requireActivity(),
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->

                val showDate = "$dayOfMonth/${month + 1}/$year"
                binding.dateBtn.text = showDate

            }, year, month, day
        )

        showDatePicker.show()

    }


}