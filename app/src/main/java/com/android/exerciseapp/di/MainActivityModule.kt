package com.android.exerciseapp.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.android.exerciseapp.di.annotations.ViewModelKey
import com.android.exerciseapp.presentation.MainActivity
import com.android.exerciseapp.presentation.podcastlist.PodcastListFragment
import com.android.exerciseapp.presentation.podcastlist.PodcastListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by JasonYang.
 */
@Module interface MainActivityModule {

    @Binds
    fun provideAppCompatActivity(activity: MainActivity): AppCompatActivity

    @ContributesAndroidInjector
    fun contributePodcastListFragment(): PodcastListFragment

    @Binds
    @IntoMap
    @ViewModelKey(PodcastListViewModel::class)
    fun bindParkingPlacesViewModel(parkingPlacesViewModel: PodcastListViewModel): ViewModel
}