package com.antipov.firebaseservices.ui.main.screen

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.core.view.isVisible
import com.antipov.firebaseservices.R
import com.antipov.firebaseservices.data.model.User
import com.antipov.firebaseservices.navigation.AppNavigator
import com.antipov.firebaseservices.ui.base.BaseFragment
import com.antipov.firebaseservices.ui.host.di.HostNavigator
import com.antipov.firebaseservices.ui.main.flow.di.MainFlowNavigator
import com.antipov.firebaseservices.ui.main.screen.adapter.MainScreenAdapter
import com.antipov.firebaseservices.utils.extensions.showSnackbar
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.main_screen_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import javax.inject.Inject

class MainScreenFragment : BaseFragment(), MainScreenView {

    @Inject
    @field:MainFlowNavigator
    lateinit var navigator: AppNavigator

    @Inject
    @field:HostNavigator
    lateinit var hostNavigator: AppNavigator

    @Inject
    @InjectPresenter
    lateinit var presenter: MainScreenPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override val layoutRes = R.layout.main_screen_fragment

    override fun getActivityNavigator() = navigator

    override fun onStart() {
        super.onStart()
        presenter.requestUserInfo()
    }

    override fun showUser(user: User) {
        mainScreenUsername.text = user.displayName
        Glide.with(this).load(user.avatar).into(mainScreenAvatar)
        mainScreenEmailNotification.isVisible = !user.isEmailVerified
        mainScreenEmailNotification.setOnClickListener { presenter.runEmailValidation() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getNotes()
        mainScreenViewPager.adapter = MainScreenAdapter(fragmentManager!!)
        logoutBtn.onClick { presenter.logout() }
        fabAdd.onClick { presenter.addClicked() }
    }

    override fun showCreateNoteDialog() {
        alert {
            var titleEt: EditText? = null
            var noteEt: EditText? = null
            customView {
                linearLayout {
                    layoutParams.apply {
                        padding = dip(16)
                    }
                    orientation = VERTICAL
                    textView("Title:")
                    titleEt = editText()
                    textView("Note:")
                    noteEt = editText()
                }
            }
            okButton {
                presenter.createNote(
                    titleEt?.text?.toString() ?: "",
                    noteEt?.text?.toString() ?: "",
                    System.currentTimeMillis()
                )
            }
        }.show()
    }

    override fun onValidationSendSuccess() {
        Toast.makeText(context, "Check your inbox", Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(message: String) {
        showSnackbar(message)
    }

    override fun showProgress() {
        progress.isVisible = true
    }

    override fun hideProgress() {
        progress.isVisible = false
    }

    override fun onBackPressed() {
        navigatorHolder.setNavigator(hostNavigator)
        presenter.onBackPressed()
    }
}