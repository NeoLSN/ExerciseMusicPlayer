package com.android.exerciseapp

import android.app.Application
import com.android.exerciseapp.di.AppComponent
import com.android.exerciseapp.di.DaggerAppComponent
import dagger.android.HasActivityInjector

/**
 * Created by JasonYang.
 */
class ExerciseApp : Application(), HasActivityInjector {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun activityInjector() = appComponent.activityInjector
}
