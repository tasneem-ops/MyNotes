package com.example.mynotes.listfragment


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.database.Note
import com.example.mynotes.databinding.NoteListBinding


class NoteListAdapter() : ListAdapter<Note, NoteListAdapter.NoteViewHolder>(DiffCallback){
    companion object DiffCallback: DiffUtil.ItemCallback <Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.noteId == newItem.noteId
        }
    }
    class NoteViewHolder(private var binding : NoteListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind( note: Note){
            binding.note = note
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(NoteListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note =  getItem(position)
        holder.bind(note)
    }
}