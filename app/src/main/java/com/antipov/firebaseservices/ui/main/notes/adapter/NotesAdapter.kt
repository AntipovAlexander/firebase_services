package com.antipov.firebaseservices.ui.main.notes.adapter

import com.antipov.firebaseservices.data.model.Note
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class NotesAdapter : ListDelegationAdapter<MutableList<Note>>() {

    init {
        items = mutableListOf()
        delegatesManager.apply {
            addDelegate(NoteDelegate())
        }
    }

    fun swapData(notes: List<Note>) {
        items.clear()
        items.addAll(notes)
        notifyDataSetChanged()
    }
}