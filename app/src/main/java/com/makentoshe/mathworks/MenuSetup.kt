package com.makentoshe.mathworks

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.layout_setup.*


class MenuSetup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid", R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_setup)
        val themeArray= arrayOf(getString(R.string.theme_apptheme), getString(R.string.theme_darktheme), getString(R.string.theme_inverttheme))
        val linksTheme= mapOf(getString(R.string.theme_apptheme) to R.style.AppTheme, getString(R.string.theme_darktheme) to R.style.DarkTheme, getString(R.string.theme_inverttheme) to R.style.InvertTheme)
        val adapt= ArrayAdapter<String>(this, R.layout.list_simple_element, R.id.list_content, themeArray)
        var t=0
        list_theme.adapter=adapt
        list_theme.setOnItemClickListener { _, _, position, _ ->
            linksTheme[themeArray[position]]?.let { application.setTheme(it) }
            PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("themeid", linksTheme[themeArray[position]]
                    ?: error("")).apply()
            finish()
            startActivity(intent)
        }
        continueButtonTask.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}