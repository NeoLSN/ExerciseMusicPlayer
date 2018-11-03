package com.android.exerciseapp.di

import android.content.Context
import com.android.exerciseapp.provider.AssetFilesProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by JasonYang.
 */
@Module
class ServiceModule {

    @Singleton
    @Provides
    fun provideAssetFilesProvider(context: Context) = AssetFilesProvider(context)
}