package com.example.todolist2402

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao


    companion object {


        var database: NoteDataBase? = null

        fun getDb(context: Context) : NoteDataBase{

            if (database==null){
                database = Room.databaseBuilder(context, NoteDataBase::class.java, "Note-DB")
                    .allowMainThreadQueries().build()


                return database as NoteDataBase
            }
            else{
                return database as NoteDataBase

            }


        }

    }

}