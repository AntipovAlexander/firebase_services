package com.antipov.firebaseservices.ui.main.screen

import com.antipov.firebaseservices.ui.base.BasePresenter
import moxy.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class MainScreenPresenter(private val router: Router) : BasePresenter<MainScreenView>() {
    fun onBackPressed() = router.exit()
}