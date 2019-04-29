package com.porterduffxfermode

import android.support.v7.app.AppCompatDelegate

/**
 *  @date 2019/1/10   4:59 PM
 *  @author weishukai
 *  @describe
 */
fun getNightModeIsOpen(): Boolean = SPUtils.getBoolean("nightModeSp", false)

fun openNightMode() {
    SPUtils.put("nightModeSp", true)
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
}

fun closeNightMode() {
    SPUtils.put("nightModeSp", false)
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
}