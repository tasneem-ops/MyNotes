package com.example.mynotes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDatabaseDao {
    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note?)

    @Query("select * from notes_table where noteId =:id ")
    suspend fun getNote(id : Int): Note?

    @Query("select * from notes_table")
    suspend fun getAllNotes(): List<Note>
}