package com.android.exerciseapp.provider

import android.content.Context
import java.io.IOException
import javax.inject.Inject

/**
 * Created by JasonYang.
 */
class AssetFilesProvider @Inject constructor(private val context: Context) {

    fun listAssetFiles(path: String = ""): List<String> {

        val result = ArrayList<String>()
        try {
            val list: Array<String> = context.assets.list(path) ?: emptyArray()
            for (file in list) {
                result += file
            }
        } catch (e: IOException) {
            return result
        }

        return result
    }
}