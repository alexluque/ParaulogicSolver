package cat.alexgluque.paraulogicsolver.android

import android.app.Application
import cat.alexgluque.paraulogicsolver.data.localSource.appContext

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}