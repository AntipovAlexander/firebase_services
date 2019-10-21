package com.antipov.firebaseservices.ui.main.screen

import com.antipov.firebaseservices.data.model.Note
import com.antipov.firebaseservices.domain.notes.CreateNoteUseCase
import com.antipov.firebaseservices.domain.user.GetUserData
import com.antipov.firebaseservices.domain.user.ValidateEmailUseCase
import com.antipov.firebaseservices.ui.base.BasePresenter
import com.antipov.firebaseservices.utils.extensions.runOnUi
import kotlinx.coroutines.launch
import moxy.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class MainScreenPresenter(
    private val createNoteUseCase: CreateNoteUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
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

    fun runEmailValidation() = launch {
        runOnUi { viewState.showProgress() }
        validateEmailUseCase.invoke(null).fold({
            runOnUi { viewState.onValidationSendSuccess() }
        }, {
            viewState.showMessage(it.message ?: "error during email validation")
        })
        runOnUi { viewState.hideProgress() }
    }

    fun createNote(note: Note) = launch {
        createNoteUseCase.invoke(note).fold({
            "".toString()
        }, {
            it.toString()
        })
    }

}