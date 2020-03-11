package com.antipov.firebaseservices.ui.main.screen.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.antipov.firebaseservices.ui.main.notes.NotesFragment

class MainScreenAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> NotesFragment()
            1 -> NotesFragment()
            else -> throw RuntimeException()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Notes"
            1 -> "Storage"
            else -> throw RuntimeException()
        }
    }

    override fun getCount(): Int = 2

}