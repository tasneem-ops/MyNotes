package com.example.mynotes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "notes_table")
data class Note (
    @PrimaryKey (autoGenerate = true)
    var noteId : Int = 0,
    @ColumnInfo(name = "title")
    var title : String = "Null",
    @ColumnInfo(name = "note")
    var note : String = "Null",
        )