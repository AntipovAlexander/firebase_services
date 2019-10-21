package com.antipov.firebaseservices.ui.onboarding.register

import com.antipov.firebaseservices.ui.base.BaseView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface RegisterView : BaseView {
    fun onRegisterSuccessful()
}