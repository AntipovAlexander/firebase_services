package com.antipov.firebaseservices.ui.onboarding.auth

import com.antipov.firebaseservices.navigation.Screens
import com.antipov.firebaseservices.ui.base.BasePresenter
import moxy.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class AuthPresenter(private val router: Router) : BasePresenter<AuthView>() {

    fun openRegister() = router.replaceScreen(Screens.Register)

    fun onBackPressed() = router.exit()
}