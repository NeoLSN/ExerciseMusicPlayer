package com.android.exerciseapp.di

import com.android.exerciseapp.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by JasonYang.
 */
@Module
interface MainActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun contributeMainActivity(): MainActivity
}