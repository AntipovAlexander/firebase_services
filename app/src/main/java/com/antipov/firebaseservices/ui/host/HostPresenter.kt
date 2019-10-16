package com.antipov.firebaseservices.ui.host

import com.antipov.firebaseservices.data.repository.ReactiveRepository
import com.antipov.firebaseservices.navigation.Screens
import com.antipov.firebaseservices.ui.base.BasePresenter
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import moxy.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class HostPresenter(
    private var reactiveRepository: ReactiveRepository,
    private val router: Router
) : BasePresenter<HostView>() {

    @ObsoleteCoroutinesApi
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launch {
            reactiveRepository.getChannel().consumeEach {
            }
        }
        router.newRootScreen(Screens.Onboarding)
    }
}