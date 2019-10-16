package com.antipov.firebaseservices.ui.onboarding.flow

import android.os.Bundle
import android.view.View
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.post { if (childFragmentManager.findFragmentById(R.id.hostContainer) == null) presenter.openAuth() }
    }

    override val layoutRes: Int = R.layout.onboarding_flow_fragment

    override fun getActivityNavigator() = navigator

    override fun onBackPressed() = currentFragment?.onBackPressed() ?: Unit
}