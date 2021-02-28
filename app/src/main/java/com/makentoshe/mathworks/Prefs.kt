package com.makentoshe.mathworks

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context)
{
    private val APP_PREF_INT_EXAMPLE = "intExamplePref"

    private val preferences: SharedPreferences = context.getSharedPreferences(APP_PREF_INT_EXAMPLE,Context.MODE_PRIVATE)

    var intExamplePref: Int
        get() = preferences.getInt(APP_PREF_INT_EXAMPLE, -1)
        set(value) = preferences.edit().putInt(APP_PREF_INT_EXAMPLE, value).apply()
}