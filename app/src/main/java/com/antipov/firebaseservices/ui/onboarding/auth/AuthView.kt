package com.antipov.firebaseservices.ui.onboarding.auth

import com.antipov.firebaseservices.ui.base.BaseView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface AuthView : BaseView {
    fun onAuthSuccess()
}