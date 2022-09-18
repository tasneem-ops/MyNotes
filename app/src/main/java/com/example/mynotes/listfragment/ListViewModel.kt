package com.example.mynotes.listfragment

import android.app.Application
import android.icu.text.MessageFormat.format
import android.os.Build
import android.text.format.DateFormat.format
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.mynotes.database.Note
import com.example.mynotes.database.NoteDatabaseDao
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.String.format
import java.text.DateFormat
import java.text.MessageFormat.format
import java.text.SimpleDateFormat
import java.util.*

class ListViewModel(val noteDatabaseDao: NoteDatabaseDao, application: Application) : AndroidViewModel(application) {

    private val _notes = MutableLiveData<List<Note>>()
    val notes : LiveData<List<Note>>
        get() = _notes

    private val _selectedNote = MutableLiveData<Note>()
    val selectedNote : LiveData<Note>
        get() = _selectedNote


init {
    getAllNotes()

}
    fun getAllNotes(){
        viewModelScope.launch {
            _notes.value = getNotes()
        }
    }
    private suspend fun getNotes(): List<Note>{
        return noteDatabaseDao.getAllNotes()
    }
    fun onNavigate(note: Note){
        _selectedNote.value = note
    }
    fun doneNavigation(){
        _selectedNote.value = null
    }

}

class ListViewModelFactory(
    private val dataSource: NoteDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}