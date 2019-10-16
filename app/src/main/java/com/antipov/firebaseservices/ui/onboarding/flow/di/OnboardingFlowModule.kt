package com.antipov.firebaseservices.ui.onboarding.flow.di

import androidx.appcompat.app.AppCompatActivity
import com.antipov.firebaseservices.R
import com.antipov.firebaseservices.data.repository.ReactiveRepository
import com.antipov.firebaseservices.di.scopes.PerChildFragment
import com.antipov.firebaseservices.di.scopes.PerFragment
import com.antipov.firebaseservices.navigation.AppNavigator
import com.antipov.firebaseservices.ui.onboarding.flow.OnboardingFlowFragment
import com.antipov.firebaseservices.ui.onboarding.flow.OnboardingFlowPresenter
import com.antipov.firebaseservices.ui.onboarding.auth.AuthFragment
import com.antipov.firebaseservices.ui.onboarding.auth.di.AuthModule
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ru.terrakok.cicerone.Router

@Module
abstract class OnboardingFlowModule {

    @PerChildFragment
    @ContributesAndroidInjector(modules = [AuthModule::class])
    abstract fun nestedInjectorInjector(): AuthFragment

    @Module
    companion object {

        @Provides
        @PerFragment
        @JvmStatic
        @OnboardingFlowNavigator
        fun provideFirstFlowNav(flowFragment: OnboardingFlowFragment) =
            AppNavigator(
                flowFragment.activity as AppCompatActivity,
                flowFragment.childFragmentManager,
                R.id.onboardingFlowContainer
            )

        @Provides
        @PerFragment
        @JvmStatic
        fun providePresenter(reactiveRepository: ReactiveRepository, router: Router) =
            OnboardingFlowPresenter(
                reactiveRepository,
                router
            )

//        @Provides
//        @PerFragment
//        @JvmStatic
//        fun provideHostDependency() = FirstFlowDependency()
    }
}