package com.antipov.firebaseservices.ui.onboarding.auth

import com.antipov.firebaseservices.domain.user.AuthUserUseCase
import com.antipov.firebaseservices.navigation.Screens
import com.antipov.firebaseservices.ui.base.BasePresenter
import kotlinx.coroutines.launch
import moxy.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class AuthPresenter(
    private val authUserUseCase: AuthUserUseCase,
    private val router: Router
) :
    BasePresenter<AuthView>() {

    fun authUser(login: String, password: String) {
        launch {
            val result = authUserUseCase.invoke(AuthUserUseCase.Params(login, password))
            result.toString()
        }
    }

    fun openRegister() = router.replaceScreen(Screens.Register)

    fun onBackPressed() = router.exit()
}