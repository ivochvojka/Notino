package cz.ich.notinoapp.main.system

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Notino application with Hilt dependency injection.
 */
@HiltAndroidApp
class NotinoApplication : Application()