package com.antipov.firebaseservices.ui.main.flow.di

import androidx.appcompat.app.AppCompatActivity
import com.antipov.firebaseservices.R
import com.antipov.firebaseservices.di.scopes.PerChildFragment
import com.antipov.firebaseservices.di.scopes.PerFragment
import com.antipov.firebaseservices.navigation.AppNavigator
import com.antipov.firebaseservices.ui.main.flow.MainFlowFragment
import com.antipov.firebaseservices.ui.main.flow.MainFlowPresenter
import com.antipov.firebaseservices.ui.main.screen.MainScreenFragment
import com.antipov.firebaseservices.ui.main.screen.di.MainScreenModule
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ru.terrakok.cicerone.Router

@Module
abstract class MainFlowModule {

    @PerChildFragment
    @ContributesAndroidInjector(modules = [MainScreenModule::class])
    abstract fun mainScreenInjectorInjector(): MainScreenFragment

    @Module
    companion object {

        @Provides
        @PerFragment
        @JvmStatic
        @MainFlowNavigator
        fun provideFirstFlowNav(flowFragment: MainFlowFragment) =
            AppNavigator(
                flowFragment.activity as AppCompatActivity,
                flowFragment.childFragmentManager,
                R.id.mainFlowContainer
            )

        @Provides
        @PerFragment
        @JvmStatic
        fun providePresenter(router: Router) =
            MainFlowPresenter(router)
    }
}