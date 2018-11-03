package com.android.exerciseapp.util.rx

import io.reactivex.Scheduler

/**
 * Created by JasonYang.
 */
interface SchedulerProvider {
    fun ui(): Scheduler

    fun computation(): Scheduler

    fun newThread(): Scheduler

    fun io(): Scheduler
}