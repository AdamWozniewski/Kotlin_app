package com.adamwozniewski.kotlin_app

import android.app.Application

/**
 * Domyslna klasa aplikacji
 */

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
    companion object {
        lateinit var appContext: App
    }
}