package com.antipov.firebaseservices

import android.app.Activity
import android.app.Application
import com.antipov.firebaseservices.BuildConfig.DEBUG
import com.antipov.firebaseservices.di.DaggerApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class SingleActivityApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        if (DEBUG) Timber.plant(Timber.DebugTree())
        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }


}