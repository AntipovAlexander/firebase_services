package com.antipov.firebaseservices.navigation

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import com.antipov.firebaseservices.R
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

abstract class BaseNavigator : SupportAppNavigator {

    constructor(activity: AppCompatActivity, @IdRes layout: Int) : super(
        activity,
        layout
    )

    constructor(
        activity: AppCompatActivity,
        childFragmentManager: androidx.fragment.app.FragmentManager, @IdRes layout: Int
    ) : super(activity, childFragmentManager, layout)

    override fun setupFragmentTransaction(
        command: Command?,
        currentFragment: androidx.fragment.app.Fragment?,
        nextFragment: androidx.fragment.app.Fragment?,
        fragmentTransaction: androidx.fragment.app.FragmentTransaction
    ) {
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_from_right,
            R.anim.slide_to_left,
            R.anim.slide_from_left,
            R.anim.slide_to_right
        )
        super.setupFragmentTransaction(command, currentFragment, nextFragment, fragmentTransaction)
    }
}