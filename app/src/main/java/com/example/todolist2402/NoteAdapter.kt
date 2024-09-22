package com.example.todolist2402

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.todolist2402.databinding.ItemDesignBinding

class NoteAdapter(var noteEdit: NoteEdit ) : ListAdapter<Note, NoteViewHolder>(comparator) {


    interface NoteEdit{

        fun onNoteEdit(note: Note)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        return NoteViewHolder(
            ItemDesignBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        getItem(position).let {

            holder.binding.apply {

                titleTV.text = it.title
                timeTV.text = it.time
                dateTV.text = it.date

            }

            holder.itemView.setOnClickListener {_ ->

                noteEdit.onNoteEdit(it)

            }


        }
    }

    companion object {

        var comparator = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }


        }

    }


}