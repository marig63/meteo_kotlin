package com.marisa.guillaume.appmeteo

import android.app.Application

class App : Application(){

    companion object {
        lateinit var instance : App

        val database:DataBase by lazy {
            DataBase(instance)
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}