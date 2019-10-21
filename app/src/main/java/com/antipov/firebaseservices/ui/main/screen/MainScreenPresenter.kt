package com.antipov.firebaseservices.ui.main.screen

import com.antipov.firebaseservices.domain.user.GetUserData
import com.antipov.firebaseservices.ui.base.BasePresenter
import com.antipov.firebaseservices.utils.extensions.runOnUi
import kotlinx.coroutines.launch
import moxy.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class MainScreenPresenter(
    private val getUserDataUseCase: GetUserData,
    private val router: Router
) : BasePresenter<MainScreenView>() {
    fun onBackPressed() = router.exit()

    fun requestUserInfo() = launch {
        getUserDataUseCase.invoke(Unit).fold({
            runOnUi { viewState.showUser(it) }
        }, {
            viewState.showMessage(it.message ?: "Error while getting user profile")
        })
    }

    fun runEmailValidation() {

    }

}