package com.antipov.firebaseservices.ui.onboarding.flow

import com.antipov.firebaseservices.R
import com.antipov.firebaseservices.navigation.AppNavigator
import com.antipov.firebaseservices.ui.base.BaseFragment
import com.antipov.firebaseservices.ui.host.di.HostNavigator
import com.antipov.firebaseservices.ui.onboarding.flow.di.OnboardingFlowNavigator
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class OnboardingFlowFragment : BaseFragment(),
    OnboardingFlowView {

    @Inject
    @InjectPresenter
    lateinit var presenter: OnboardingFlowPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    @Inject
    @field:OnboardingFlowNavigator
    lateinit var navigator: AppNavigator

    @Inject
    @field:HostNavigator
    lateinit var hostNavigator: AppNavigator

    private val currentFragment: BaseFragment?
        get() = childFragmentManager.findFragmentById(R.id.onboardingFlowContainer) as? BaseFragment

    override val layoutRes: Int = R.layout.onboarding_flow_fragment

    override fun getActivityNavigator() = navigator

    override fun onLoggedIn() {
        navigatorHolder.setNavigator(hostNavigator)
        view?.post { presenter.openMain() }
    }

    override fun onNotLoggedIn() {
        view?.post { presenter.openAuth() }
    }

    override fun onBackPressed() = currentFragment?.onBackPressed() ?: Unit
}