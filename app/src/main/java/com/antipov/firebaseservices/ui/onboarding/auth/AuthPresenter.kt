package com.antipov.firebaseservices.ui.onboarding.auth

import com.antipov.firebaseservices.ui.base.BasePresenter
import moxy.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class AuthPresenter(private val router: Router) : BasePresenter<AuthView>() {

    fun openRegister() {

    }

    fun onBackPressed() = router.exit()
}