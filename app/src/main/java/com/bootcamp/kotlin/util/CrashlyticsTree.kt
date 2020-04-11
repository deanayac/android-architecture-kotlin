package com.bootcamp.kotlin.util

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

private const val FIREBASE_CRASHLYTICS_KEY_PRIORITY = "priority"
private const val FIREBASE_CRASHLYTICS_KEY_TAG = "tag"
private const val FIREBASE_CRASHLYTICS_KEY_MESSAGE = "message"

class CrashlyticsTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
            return
        }

        FirebaseCrashlytics.getInstance().setCustomKey(FIREBASE_CRASHLYTICS_KEY_PRIORITY, priority)
        FirebaseCrashlytics.getInstance().setCustomKey(FIREBASE_CRASHLYTICS_KEY_TAG, tag ?: "")
        FirebaseCrashlytics.getInstance().setCustomKey(FIREBASE_CRASHLYTICS_KEY_MESSAGE, message)

        t?.let {
            FirebaseCrashlytics.getInstance().recordException(t)
        } ?: kotlin.run {
            FirebaseCrashlytics.getInstance().recordException(Exception(message))
        }
    }
}