package com.antipov.firebaseservices.navigation

import androidx.fragment.app.Fragment
import com.antipov.firebaseservices.ui.main.flow.MainFlowFragment
import com.antipov.firebaseservices.ui.main.screen.MainScreenFragment
import com.antipov.firebaseservices.ui.onboarding.auth.AuthFragment
import com.antipov.firebaseservices.ui.onboarding.flow.OnboardingFlowFragment
import com.antipov.firebaseservices.ui.onboarding.register.RegisterFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * Put there your screens.
 */

class Screens {

    object Onboarding : SupportAppScreen() {
        override fun getFragment(): Fragment = OnboardingFlowFragment()
    }

    object Auth : SupportAppScreen() {
        override fun getFragment(): Fragment = AuthFragment()
    }

    object Register : SupportAppScreen() {
        override fun getFragment(): Fragment = RegisterFragment()
    }

    object MainFlow : SupportAppScreen() {
        override fun getFragment(): MainFlowFragment =
            MainFlowFragment()
    }

    object MainScreen : SupportAppScreen() {
        override fun getFragment() = MainScreenFragment()
    }

}