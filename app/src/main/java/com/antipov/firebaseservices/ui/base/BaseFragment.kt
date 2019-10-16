package com.antipov.firebaseservices.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.antipov.firebaseservices.navigation.AppNavigator
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import moxy.MvpAppCompatFragment
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment : MvpAppCompatFragment(), CoroutineScope, HasSupportFragmentInjector {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    protected abstract val layoutRes: Int

    private val compositeJob = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + compositeJob

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutRes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onDestroy() {
        compositeJob.cancel()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(getActivityNavigator())
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    abstract fun getActivityNavigator(): AppNavigator

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> =
        fragmentDispatchingAndroidInjector

    open fun initListeners() { /* implement if needed */
    }

    open fun onBackPressed() {/* implement if needed */
    }
}