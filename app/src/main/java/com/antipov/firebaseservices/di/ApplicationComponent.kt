package com.antipov.firebaseservices.di

import android.app.Application
import com.antipov.firebaseservices.SingleActivityApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NavigationModule::class,
        AppDependencies::class,
        GlobalRepositories::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: SingleActivityApp)
}