package com.antipov.firebaseservices.ui.main.flow

import com.antipov.firebaseservices.navigation.Screens
import com.antipov.firebaseservices.ui.base.BasePresenter
import moxy.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class MainFlowPresenter(private val router: Router) : BasePresenter<MainFlowView>() {
    fun openMainScreen() = router.navigateTo(Screens.MainScreen)
}