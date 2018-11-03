package com.android.exerciseapp.presentation.podcastlist

import androidx.lifecycle.MutableLiveData
import com.android.exerciseapp.presentation.BaseViewModel
import com.android.exerciseapp.presentation.common.Result
import com.android.exerciseapp.presentation.common.toResult
import com.android.exerciseapp.provider.AssetFilesProvider
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

/**
 * Created by JasonYang.
 */
class PodcastListViewModel @Inject constructor(
    private val assetFiles: AssetFilesProvider
) : BaseViewModel() {

    val podcasts = MutableLiveData<Result<List<String>>>()

    fun getAssetFileList() {
        Observable.fromIterable(assetFiles.listAssetFiles())
            .filter { it.contains(".mp3") }
            .toList()
            .toResult()
            .subscribe { podcasts.postValue(it) }
            .addTo(disposables)
    }
}