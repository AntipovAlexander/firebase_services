package com.antipov.firebaseservices.ui.main.notes

import com.antipov.firebaseservices.data.model.Note
import com.antipov.firebaseservices.ui.base.BaseView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface NotesView : BaseView {
    fun setNotes(notes: List<Note>)
}