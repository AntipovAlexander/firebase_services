package com.antipov.firebaseservices.ui.onboarding.flow

import com.antipov.firebaseservices.navigation.Screens
import com.antipov.firebaseservices.ui.base.BasePresenter
import moxy.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class OnboardingFlowPresenter(private val router: Router) : BasePresenter<OnboardingFlowView>() {

    fun openAuth() = router.navigateTo(Screens.Auth)
}