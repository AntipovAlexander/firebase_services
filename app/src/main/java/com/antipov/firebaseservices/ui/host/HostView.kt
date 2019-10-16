package com.antipov.firebaseservices.ui.host

import com.antipov.firebaseservices.ui.base.BaseView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface HostView : BaseView {
    fun onLoggedIn()
    fun onNotLoggedIn()
}