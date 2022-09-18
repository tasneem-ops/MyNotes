package com.example.mynotes.mainfragment

import android.app.Application
import androidx.lifecycle.*
import com.example.mynotes.database.Note
import com.example.mynotes.database.NoteDatabaseDao
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val noteDatabaseDao: NoteDatabaseDao, application: Application) : AndroidViewModel(application) {

    private val _note = MutableLiveData<Note>()
    val note : LiveData<Note>
    get() = _note


    fun onSaveButtonclicked(newnote: Note){
        viewModelScope.launch {
            insertNote(newnote)
        }
    }
    private suspend fun insertNote(note: Note){
        noteDatabaseDao.insertNote(note)
    }

}

class MainViewModelFactory(
    private val dataSource: NoteDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}