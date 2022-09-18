package com.example.mynotes.listfragment

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mynotes.R
import com.example.mynotes.database.NoteDatabase
import com.example.mynotes.databinding.FragmentListBinding


class ListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application

        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao

        val viewModelFactory = ListViewModelFactory(dataSource, application)
        val listViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(ListViewModel::class.java)
        binding.listViewModel = listViewModel
        binding.lifecycleOwner = this
        val adapter = NoteListAdapter()
        binding.noteRecyclerView.adapter = adapter
        listViewModel.notes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        return binding.root
    }

}