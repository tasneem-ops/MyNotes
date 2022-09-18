package com.example.mynotes.mainfragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mynotes.R
import com.example.mynotes.database.Note
import com.example.mynotes.database.NoteDatabase
import com.example.mynotes.databinding.FragmentMainBinding
import java.text.SimpleDateFormat
import java.util.*


class MainFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding   = FragmentMainBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application

        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao

        val viewModelFactory = MainViewModelFactory(dataSource, application)

        val mainViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(MainViewModel::class.java)

        binding.mainViewModel = mainViewModel
        val note = Note()
        binding.note = note
        binding?.apply {
            note.title = titleText.text.toString()
            note.note = noteText.text.toString()
        }
        binding.saveButton.setOnClickListener {
            mainViewModel.onSaveButtonclicked(note)
            Toast.makeText(context, "Successfully added you note", Toast.LENGTH_SHORT)
            Log.i("XXX", "Success")
           this.findNavController().navigate(R.id.action_mainFragment_to_listFragment)

        }
        return binding.root
    }

}