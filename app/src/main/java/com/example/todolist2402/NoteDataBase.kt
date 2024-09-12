package com.example.todolist2402

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDataBase : RoomDatabase(){

    abstract fun getNoteDao() : NoteDao

}