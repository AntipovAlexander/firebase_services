package com.antipov.firebaseservices.ui.host.di

import com.antipov.firebaseservices.R
import com.antipov.firebaseservices.data.repository.ReactiveRepository
import com.antipov.firebaseservices.di.scopes.PerActivity
import com.antipov.firebaseservices.di.scopes.PerFragment
import com.antipov.firebaseservices.navigation.AppNavigator
import com.antipov.firebaseservices.ui.onboarding.flow.OnboardingFlowFragment
import com.antipov.firebaseservices.ui.onboarding.flow.di.OnboardingFlowModule
import com.antipov.firebaseservices.ui.host.HostActivity
import com.antipov.firebaseservices.ui.host.HostPresenter
import com.antipov.firebaseservices.ui.main.MainFlowFragment
import com.antipov.firebaseservices.ui.main.di.MainFlowModule
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ru.terrakok.cicerone.Router

@Module
abstract class HostModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [OnboardingFlowModule::class])
    abstract fun onboarding(): OnboardingFlowFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [MainFlowModule::class])
    abstract fun main(): MainFlowFragment

    @Module
    companion object {
        @Provides
        @PerActivity
        @JvmStatic
        fun providePresenter(reactiveRepository: ReactiveRepository, router: Router) =
            HostPresenter(reactiveRepository, router)

        @Provides
        @PerActivity
        @JvmStatic
        @HostNavigator
        fun provideMainActivityNavigator(hostActivity: HostActivity) =
            AppNavigator(hostActivity, R.id.hostContainer)
    }
}