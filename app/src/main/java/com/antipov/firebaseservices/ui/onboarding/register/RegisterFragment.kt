package com.antipov.firebaseservices.ui.onboarding.register

import com.antipov.firebaseservices.R
import com.antipov.firebaseservices.navigation.AppNavigator
import com.antipov.firebaseservices.ui.base.BaseFragment
import com.antipov.firebaseservices.ui.host.di.HostNavigator
import com.antipov.firebaseservices.ui.onboarding.flow.di.OnboardingFlowNavigator
import com.antipov.firebaseservices.ui.onboarding.register.RegisterPresenter
import com.antipov.firebaseservices.ui.onboarding.register.RegisterView
import kotlinx.android.synthetic.main.register_fragment.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.jetbrains.anko.sdk27.coroutines.onClick
import javax.inject.Inject

class RegisterFragment : BaseFragment(), RegisterView {

    @Inject
    @field:OnboardingFlowNavigator
    lateinit var navigator: AppNavigator

    @Inject
    @field:HostNavigator
    lateinit var hostNavigator: AppNavigator

    @Inject
    @InjectPresenter
    lateinit var presenter: RegisterPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override val layoutRes: Int = R.layout.register_fragment

    override fun getActivityNavigator(): AppNavigator = navigator

    override fun initListeners() {
        registerFragmentAuthButton.onClick { presenter.openAuth() }
    }

    override fun onBackPressed() {
        navigatorHolder.setNavigator(hostNavigator)
        presenter.onBackPressed()
    }
}