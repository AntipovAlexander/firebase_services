package com.antipov.firebaseservices.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.antipov.firebaseservices.R
import com.antipov.firebaseservices.navigation.AppNavigator
import com.antipov.firebaseservices.utils.extensions.showSnackbar
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import moxy.MvpAppCompatActivity
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : MvpAppCompatActivity(), HasSupportFragmentInjector,
    BaseView, CoroutineScope {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val compositeJob = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + compositeJob

    protected abstract val layoutRes: Int

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.hostContainer) as? BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        initListeners()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(getActivityNavigator())
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        compositeJob.cancel()
        super.onDestroy()
    }

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     * Must be called onStart()
     */
    protected open fun overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     * Must be called on finish()
     */
    protected open fun overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    override fun finish() {
        super.finish()
        overridePendingTransitionExit()
    }

    override fun startActivity(intent: Intent?, options: Bundle?) {
        super.startActivity(intent, options)
        overridePendingTransitionEnter()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        fragmentDispatchingAndroidInjector

    abstract fun getActivityNavigator(): AppNavigator

    open fun initListeners() { /* implement if needed */
    }

    override fun showMessage(message: String) {
        showSnackbar(message)
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

}
