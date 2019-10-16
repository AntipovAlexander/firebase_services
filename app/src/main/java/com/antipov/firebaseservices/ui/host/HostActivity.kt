package com.antipov.firebaseservices.ui.host

import com.antipov.firebaseservices.R
import com.antipov.firebaseservices.navigation.AppNavigator
import com.antipov.firebaseservices.ui.base.BaseActivity
import com.antipov.firebaseservices.ui.host.di.HostNavigator
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class HostActivity : BaseActivity(), HostView {

    @Inject
    @InjectPresenter
    lateinit var presenter: HostPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    @Inject
    @field:HostNavigator
    lateinit var navigator: AppNavigator

    override val layoutRes = R.layout.activity_host

    override fun getActivityNavigator() = navigator

    override fun onLoggedIn() = presenter.openMain()

    override fun onNotLoggedIn() = presenter.openOnboarding()
}
