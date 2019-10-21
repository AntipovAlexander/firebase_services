package com.antipov.firebaseservices.ui.onboarding.register

import com.antipov.firebaseservices.domain.user.RegisterUseCase
import com.antipov.firebaseservices.navigation.Screens
import com.antipov.firebaseservices.ui.base.BasePresenter
import com.antipov.firebaseservices.utils.extensions.runOnUi
import kotlinx.coroutines.launch
import moxy.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class RegisterPresenter(
    private val registerUseCase: RegisterUseCase,
    private val router: Router
) : BasePresenter<RegisterView>() {

    fun register(email: String, password: String) =
        launch {
            runOnUi { viewState.showProgress() }
            val result = registerUseCase.invoke(RegisterUseCase.Params(email, password))
            result.fold({
                runOnUi {
                    viewState.onRegisterSuccessful()
                    router.replaceScreen(Screens.Main)
                }
            }, {
                viewState.showMessage(it.message ?: "Register error, try again")
            })
            runOnUi { viewState.hideProgress() }
        }

    fun openAuth() = router.replaceScreen(Screens.Auth)

    fun onBackPressed() = router.exit()
}