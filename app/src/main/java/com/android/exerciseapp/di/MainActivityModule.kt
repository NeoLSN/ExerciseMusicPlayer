package com.android.exerciseapp.di

import androidx.appcompat.app.AppCompatActivity
import com.android.exerciseapp.presentation.MainActivity
import dagger.Binds
import dagger.Module

/**
 * Created by JasonYang.
 */
@Module interface MainActivityModule {

    @Binds
    fun provideAppCompatActivity(activity: MainActivity): AppCompatActivity
}