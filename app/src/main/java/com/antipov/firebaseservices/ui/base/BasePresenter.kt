package com.antipov.firebaseservices.ui.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import moxy.MvpPresenter
import moxy.MvpView
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<V : MvpView> : MvpPresenter<V>(), CoroutineScope {

    private val compositeJob = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + compositeJob

    override fun onDestroy() {
        compositeJob.cancel()
        super.onDestroy()
    }
}