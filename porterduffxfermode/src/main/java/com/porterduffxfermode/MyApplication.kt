package com.porterduffxfermode

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import kotlin.properties.Delegates

/**
 *  @date 2019/1/23   5:12 PM
 *  @author weishukai
 *  @describe
 */
class MyApplication : Application() {

    companion object {
        var mApp by Delegates.notNull<Application>()
        fun getApp(): Application = mApp
    }


    override fun onCreate() {
        super.onCreate()
        mApp = this
        if (getNightModeIsOpen()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}