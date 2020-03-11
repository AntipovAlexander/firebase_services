package com.antipov.firebaseservices.ui.main.screen

import com.antipov.firebaseservices.data.model.Note
import com.antipov.firebaseservices.data.model.User
import com.antipov.firebaseservices.domain.notes.CreateNoteUseCase
import com.antipov.firebaseservices.domain.notes.GetNotesUseCase
import com.antipov.firebaseservices.domain.user.GetUserData
import com.antipov.firebaseservices.domain.user.LogoutUseCase
import com.antipov.firebaseservices.domain.user.ValidateEmailUseCase
import com.antipov.firebaseservices.navigation.Screens
import com.antipov.firebaseservices.ui.base.BasePresenter
import com.antipov.firebaseservices.utils.extensions.runOnUi
import kotlinx.coroutines.launch
import moxy.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class MainScreenPresenter(
    private val getNotesUseCase: GetNotesUseCase,
    private val createNoteUseCase: CreateNoteUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val getUserDataUseCase: GetUserData,
    private val logoutUseCase: LogoutUseCase,
    private val router: Router
) : BasePresenter<MainScreenView>() {

    private lateinit var user: User

    fun onBackPressed() = router.exit()

    fun requestUserInfo() = launch {
        getUserDataUseCase.invoke(Unit).fold({ user ->
            this@MainScreenPresenter.user = user
            runOnUi { viewState.showUser(user) }
        }, {
            viewState.showMessage(it.message ?: "Error while getting user profile")
        })
    }

    fun runEmailValidation() = launch {
        runOnUi { viewState.showProgress() }
        validateEmailUseCase.invoke(null).fold({
            runOnUi { viewState.onValidationSendSuccess() }
        }, {
            viewState.showMessage(it.message ?: "error during email validation")
        })
        runOnUi { viewState.hideProgress() }
    }

    fun createNote(title: String, note: String, createdAt: Long) = launch {
        runOnUi { viewState.showProgress() }
        if (!::user.isInitialized) return@launch
        val noteObj = Note(user.uid, title, note, createdAt)
        createNoteUseCase.invoke(noteObj).fold({
            "".toString()
        }, {
            viewState.showMessage(it.message ?: "error during creating note")
        })
        runOnUi { viewState.hideProgress() }
    }

    fun getNotes() = launch {
        getNotesUseCase.invoke(null).fold({
            "".toString()
        }, {
            it.toString()
        })
    }

    fun logout() = launch {
        logoutUseCase.invoke(Unit).fold({
            runOnUi { router.newRootScreen(Screens.Onboarding) }
        }, {
            viewState.showMessage(it.message ?: "error during user logout")
        })
    }

    fun addClicked() = viewState.showCreateNoteDialog()
}