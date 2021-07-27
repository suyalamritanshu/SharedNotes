package com.rahuls.sharednotes.roomdb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rahuls.sharednotes.R

class NotesRVAdapter(private val context: Context, private val listener: INotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NotesViewHolder>() {

    private val allNotes = ArrayList<Notes>()

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById<TextView>(R.id.text)
        val deleteButton: ImageView = itemView.findViewById<ImageView>(R.id.deleteButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewHolder = NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.content_add_group, parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNotes = allNotes[position]
        holder.textView.text = currentNotes.text
    }

    fun updateList(newList: List<Notes>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {

        return allNotes.size
    }
}

interface INotesRVAdapter{
    fun onItemClicked(notes: Notes)
}