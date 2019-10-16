package com.antipov.firebaseservices.ui.base

import androidx.annotation.StringRes
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface BaseView : MvpView {
    fun showProgress() { /* implement if needed */
    }

    fun hideProgress() { /* implement if needed */
    }

    fun showMessage(message: String) { /* implement if needed */
    }

    fun showMessage(@StringRes message: Int) { /* implement if needed */
    }

    fun showFullScreenError(message: String) { /* implement if needed */
    }

    fun hideFullScreenError() { /* implement if needed */
    }

    fun onNoInternet() { /* implement if needed */
    }
}