package com.lintschool.communi.stories

import androidx.preference.PreferenceManager
import com.lintschool.communi.stories.data.SharedPreferencesWrapper
import com.lintschool.communi.stories.data.StoriesApi
import com.lintschool.communi.stories.data.StoriesRepository
import com.lintschool.communi.stories.data.StoriesUseCase
import com.lintschool.communi.stories.data.StoriesVM
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object StoriesInjector {

    val storiesModule = module {

        single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
        single { SharedPreferencesWrapper(get()) }
        single { StoriesApi() }

        factory {
            StoriesRepository(
                get(),
                get()
            )
        }
        factory { StoriesUseCase(get()) }
        viewModel { StoriesVM(get()) }
    }
}
