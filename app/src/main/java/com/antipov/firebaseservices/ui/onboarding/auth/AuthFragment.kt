package com.antipov.firebaseservices.ui.onboarding.auth

import androidx.core.view.isVisible
import com.antipov.firebaseservices.R
import com.antipov.firebaseservices.navigation.AppNavigator
import com.antipov.firebaseservices.ui.base.BaseFragment
import com.antipov.firebaseservices.ui.host.di.HostNavigator
import com.antipov.firebaseservices.ui.onboarding.flow.di.OnboardingFlowNavigator
import com.antipov.firebaseservices.utils.extensions.showSnackbar
import kotlinx.android.synthetic.main.auth_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.jetbrains.anko.sdk27.coroutines.onClick
import javax.inject.Inject

class AuthFragment : BaseFragment(), AuthView {

    @Inject
    @field:OnboardingFlowNavigator
    lateinit var navigator: AppNavigator

    @Inject
    @field:HostNavigator
    lateinit var hostNavigator: AppNavigator

    @Inject
    @InjectPresenter
    lateinit var presenter: AuthPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override val layoutRes: Int = R.layout.auth_fragment

    override fun getActivityNavigator(): AppNavigator = navigator

    override fun initListeners() {
        fragmentRegisterGoToRegister.onClick { presenter.openRegister() }
        fragmentRegisterAuthBtn.onClick {
            presenter.authUser(
                fragmentRegisterLogin.text.toString(),
                fragmentRegisterPassword.text.toString()
            )
        }
    }

    override fun onBackPressed() {
        navigatorHolder.setNavigator(hostNavigator)
        presenter.onBackPressed()
    }

    override fun onAuthSuccess() = navigatorHolder.setNavigator(hostNavigator)

    override fun showMessage(message: String) {
        showSnackbar(message)
    }

    override fun showProgress() {
        progress.isVisible = true
    }

    override fun hideProgress() {
        progress.isVisible = false
    }
}