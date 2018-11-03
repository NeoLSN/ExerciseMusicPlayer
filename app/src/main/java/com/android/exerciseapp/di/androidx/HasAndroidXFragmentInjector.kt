package com.android.exerciseapp.di.androidx

import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.internal.Beta

@Beta
interface HasAndroidXFragmentInjector {

    fun supportFragmentInjector(): AndroidInjector<Fragment>
}