package com.antipov.firebaseservices.ui.main.notes

import com.antipov.firebaseservices.domain.notes.GetNotesUseCase
import com.antipov.firebaseservices.ui.base.BasePresenter
import com.antipov.firebaseservices.utils.extensions.runOnUi
import kotlinx.coroutines.launch
import moxy.InjectViewState

@InjectViewState
class NotesPresenter(private val getNotesUseCase: GetNotesUseCase) : BasePresenter<NotesView>() {
    fun getNotes() = launch {
        getNotesUseCase.invoke(null).fold({ notes ->
            runOnUi { viewState.setNotes(notes) }
        }, {
            it.toString()
        })
    }
}