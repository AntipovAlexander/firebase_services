package com.antipov.firebaseservices.navigation

import androidx.fragment.app.Fragment
import com.antipov.firebaseservices.ui.onboarding.auth.AuthFragment
import com.antipov.firebaseservices.ui.onboarding.flow.OnboardingFlowFragment
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
//
//    object FirstNested : SupportAppScreen() {
//        override fun getFragment(): FirstNestedFragment = FirstNestedFragment()
//    }
//
//    object SecondNested : SupportAppScreen() {
//        override fun getFragment(): SecondNestedFragment = SecondNestedFragment()
//    }
//
//    object ThirdNested : SupportAppScreen() {
//        override fun getFragment(): ThirdNestedFragment = ThirdNestedFragment()
//    }

}