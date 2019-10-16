package com.antipov.firebaseservices.ui.onboarding.register

import com.antipov.firebaseservices.navigation.Screens
import com.antipov.firebaseservices.ui.base.BasePresenter
import moxy.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class RegisterPresenter(private val router: Router) : BasePresenter<RegisterView>() {

    fun openAuth() = router.replaceScreen(Screens.Auth)

    fun onBackPressed() = router.exit()
}