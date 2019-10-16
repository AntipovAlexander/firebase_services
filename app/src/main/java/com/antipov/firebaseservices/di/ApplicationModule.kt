package com.antipov.firebaseservices.di

import com.antipov.firebaseservices.di.scopes.PerActivity
import com.antipov.firebaseservices.ui.host.HostActivity
import com.antipov.firebaseservices.ui.host.di.HostModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ApplicationModule {
    @PerActivity
    @ContributesAndroidInjector(modules = [HostModule::class])
    abstract fun bindHost(): HostActivity
}