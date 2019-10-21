package com.antipov.firebaseservices.ui.main.screen

import com.antipov.firebaseservices.R
import com.antipov.firebaseservices.navigation.AppNavigator
import com.antipov.firebaseservices.ui.base.BaseFragment
import com.antipov.firebaseservices.ui.host.di.HostNavigator
import com.antipov.firebaseservices.ui.main.flow.di.MainFlowNavigator
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
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

    override fun onBackPressed() {
        navigatorHolder.setNavigator(hostNavigator)
        presenter.onBackPressed()
    }
}