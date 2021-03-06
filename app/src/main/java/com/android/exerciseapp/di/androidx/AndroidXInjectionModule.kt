package com.android.exerciseapp.di.androidx

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.internal.Beta
import dagger.multibindings.Multibinds

@Beta
@Module(includes = [AndroidInjectionModule::class])
abstract class AndroidXInjectionModule private constructor() {

    @Multibinds
    internal abstract fun supportFragmentInjectorFactories(): Map<Class<out Fragment>, AndroidInjector.Factory<out Fragment>>

    @Multibinds
    internal abstract fun supportFragmentInjectorFactoriesWithStringKeys(): Map<String, AndroidInjector.Factory<out Fragment>>
}
