package com.antipov.firebaseservices.navigation

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

/**
 * Put here your new implementation of AppNavigator, if it needed
 */
class AppNavigator : BaseNavigator {

    constructor(activity: AppCompatActivity, @IdRes layout: Int) : super(
        activity,
        layout
    )

    constructor(activity: AppCompatActivity, childFragmentManager: androidx.fragment.app.FragmentManager, @IdRes layout: Int) : super(
        activity,
        childFragmentManager,
        layout
    )
}