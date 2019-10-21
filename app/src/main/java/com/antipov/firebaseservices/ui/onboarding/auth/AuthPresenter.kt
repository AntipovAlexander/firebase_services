package com.antipov.firebaseservices.ui.onboarding.auth

import com.antipov.firebaseservices.domain.user.AuthUserUseCase
import com.antipov.firebaseservices.navigation.Screens
import com.antipov.firebaseservices.ui.base.BasePresenter
import com.antipov.firebaseservices.utils.extensions.runOnUi
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
            runOnUi { viewState.showProgress() }
            val result = authUserUseCase.invoke(AuthUserUseCase.Params(login, password))
            result.fold({
                runOnUi {
                    viewState.onAuthSuccess()
                    router.replaceScreen(Screens.Main)
                }
            }, {
                viewState.showMessage(it.message ?: "Error occurred")
            })
            runOnUi { viewState.hideProgress() }
        }
    }

    fun openRegister() = router.replaceScreen(Screens.Register)

    fun onBackPressed() = router.exit()
}