package com.antipov.firebaseservices.ui.main.screen

import com.antipov.firebaseservices.data.model.User
import com.antipov.firebaseservices.ui.base.BaseView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface MainScreenView : BaseView {
    fun showUser(user: User)
}