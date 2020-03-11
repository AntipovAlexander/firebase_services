package com.antipov.firebaseservices.ui.main.notes

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.antipov.firebaseservices.R
import com.antipov.firebaseservices.data.model.Note
import com.antipov.firebaseservices.navigation.AppNavigator
import com.antipov.firebaseservices.ui.base.BaseFragment
import com.antipov.firebaseservices.ui.host.di.HostNavigator
import com.antipov.firebaseservices.ui.main.notes.adapter.NotesAdapter
import com.antipov.firebaseservices.utils.VerticalListItemDecorator
import kotlinx.android.synthetic.main.notes_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.jetbrains.anko.support.v4.dip
import javax.inject.Inject

class NotesFragment : BaseFragment(), NotesView {

    override val layoutRes: Int = R.layout.notes_fragment

    @Inject
    @field:HostNavigator
    lateinit var hostNavigator: AppNavigator

    @Inject
    @InjectPresenter
    lateinit var presenter: NotesPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private val adapter: NotesAdapter = NotesAdapter()

    override fun getActivityNavigator(): AppNavigator = hostNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesRecycler.apply {
            adapter = this@NotesFragment.adapter
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            addItemDecoration(VerticalListItemDecorator(dip(16)))
        }
        presenter.getNotes()
    }

    override fun setNotes(notes: List<Note>) {
        adapter.swapData(notes)
    }
}