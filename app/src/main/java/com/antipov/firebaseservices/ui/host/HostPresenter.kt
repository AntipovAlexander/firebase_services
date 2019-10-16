package com.antipov.firebaseservices.ui.host

import com.antipov.firebaseservices.domain.user.IsUserLogged
import com.antipov.firebaseservices.navigation.Screens
import com.antipov.firebaseservices.ui.base.BasePresenter
import kotlinx.coroutines.launch
import moxy.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class HostPresenter(
    private val isUserLoggedUseCase: IsUserLogged,
    private val router: Router
) : BasePresenter<HostView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launch {
            isUserLoggedUseCase.invoke(null).fold({ isAuthed ->
                if (isAuthed)
                    viewState.onLoggedIn()
                else
                    viewState.onNotLoggedIn()
            }, {
                viewState.showMessage(it.message ?: "Auth check error")
            })
        }
    }

    fun openOnboarding() = router.navigateTo(Screens.Onboarding)

    fun openMain() = router.navigateTo(Screens.Main)
}