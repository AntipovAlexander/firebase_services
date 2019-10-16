package com.antipov.firebaseservices.ui.onboarding.flow

import com.antipov.firebaseservices.Either
import com.antipov.firebaseservices.domain.user.IsUserLogged
import com.antipov.firebaseservices.navigation.Screens
import com.antipov.firebaseservices.ui.base.BasePresenter
import com.antipov.firebaseservices.utils.extensions.runOnUi
import kotlinx.coroutines.launch
import moxy.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class OnboardingFlowPresenter(
    private val isUserLoggedUseCase: IsUserLogged,
    private val router: Router
) : BasePresenter<OnboardingFlowView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launch {
            val result = isUserLoggedUseCase.invoke(null)
            when (result) {
                is Either.Right -> runOnUi {
//                    if (result.b)

                }
                is Either.Left -> runOnUi {}
            }
        }
    }

    fun openAuth() = router.navigateTo(Screens.Auth)
}