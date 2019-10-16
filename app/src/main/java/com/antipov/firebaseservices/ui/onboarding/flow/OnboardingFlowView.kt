package com.antipov.firebaseservices.ui.onboarding.flow

import com.antipov.firebaseservices.ui.base.BaseView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface OnboardingFlowView : BaseView {
}