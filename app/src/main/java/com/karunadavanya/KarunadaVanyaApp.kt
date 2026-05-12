package com.karunadavanya

import android.app.Application
import com.google.firebase.FirebaseApp

class KarunadaVanyaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
