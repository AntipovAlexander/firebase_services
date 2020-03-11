package com.antipov.firebaseservices.ui.main.notes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antipov.firebaseservices.R
import com.antipov.firebaseservices.data.model.Note
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.list_item_note_card.view.*

class NoteDelegate : AdapterDelegate<MutableList<Note>>() {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_note_card, parent, false)
    )

    override fun isForViewType(items: MutableList<Note>, position: Int): Boolean = true

    override fun onBindViewHolder(
        items: MutableList<Note>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ViewHolder).bind(items[position])
    }

    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(note: Note) {
            with(itemView) {
                noteCardTitle.text = note.title
                noteCardContent.text = note.content
            }
        }
    }
}