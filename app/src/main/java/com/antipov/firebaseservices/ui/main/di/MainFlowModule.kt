package com.antipov.firebaseservices.ui.main.di

import androidx.appcompat.app.AppCompatActivity
import com.antipov.firebaseservices.R
import com.antipov.firebaseservices.di.scopes.PerFragment
import com.antipov.firebaseservices.navigation.AppNavigator
import com.antipov.firebaseservices.ui.main.MainFlowFragment
import com.antipov.firebaseservices.ui.main.MainFlowPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
abstract class MainFlowModule {

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
        fun providePresenter(router: Router) = MainFlowPresenter(router)
    }
}