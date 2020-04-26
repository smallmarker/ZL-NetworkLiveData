package com.zl.networklivedata

import android.app.Application
import android.content.Context


/**
 * @author      zl
 * @Date        2020/4/21
 **/
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {

        private lateinit var INSTANCE: Application

        fun getInstance(): Application {
            return this.INSTANCE;
        }
    }
}