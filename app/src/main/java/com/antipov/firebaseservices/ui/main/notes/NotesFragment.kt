package com.antipov.firebaseservices.ui.main.notes

import com.antipov.firebaseservices.R
import com.antipov.firebaseservices.navigation.AppNavigator
import com.antipov.firebaseservices.ui.base.BaseFragment
import com.antipov.firebaseservices.ui.host.di.HostNavigator
import javax.inject.Inject

class NotesFragment : BaseFragment(), NotesView {

    override val layoutRes: Int = R.layout.notes_fragment

    @Inject
    @field:HostNavigator
    lateinit var hostNavigator: AppNavigator

    override fun getActivityNavigator(): AppNavigator = hostNavigator
}