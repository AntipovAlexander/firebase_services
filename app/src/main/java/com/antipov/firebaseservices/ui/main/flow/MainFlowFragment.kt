package com.antipov.firebaseservices.ui.main.flow

import android.os.Bundle
import com.antipov.firebaseservices.R
import com.antipov.firebaseservices.navigation.AppNavigator
import com.antipov.firebaseservices.ui.base.BaseFragment
import com.antipov.firebaseservices.ui.main.flow.di.MainFlowNavigator
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class MainFlowFragment : BaseFragment(), MainFlowView {

    @Inject
    @InjectPresenter
    lateinit var presenter: MainFlowPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    @Inject
    @field:MainFlowNavigator
    lateinit var navigator: AppNavigator

    private val currentFragment: BaseFragment?
        get() = childFragmentManager.findFragmentById(R.id.mainFlowContainer) as? BaseFragment

    override val layoutRes: Int = R.layout.main_flow_fragment

    override fun getActivityNavigator() = navigator

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.post {
            if (childFragmentManager.findFragmentById(R.id.mainFlowContainer) == null) presenter.openMainScreen()
        }
    }

    override fun onBackPressed() = currentFragment?.onBackPressed() ?: Unit
}