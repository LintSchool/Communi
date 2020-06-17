package com.lintschool.communi.di

import com.lintschool.communi.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MagicModule::class])
interface MagicComponent {
    fun poke(mainActivity: MainActivity)
}