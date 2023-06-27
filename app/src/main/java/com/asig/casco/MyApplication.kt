package com.asig.casco

import android.app.Application
//import com.orhanobut.hawk.Hawk
//import timber.log.Timber
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

//    companion object {
//        lateinit var instance: MyApplication
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//
//        instance = this
//        init()
//    }

//    private fun init(){
//        initHawk()
//        initTimber()
//    }

/*    private fun initHawk() {
        Hawk.init(this).build()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }*/
}