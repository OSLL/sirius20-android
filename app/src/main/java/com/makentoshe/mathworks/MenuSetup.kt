package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.layout_setup.*


class MenuSetup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_setup)
        val themeArray= arrayOf(getString(R.string.theme_apptheme),getString(R.string.theme_darktheme))
        val linksTheme= mapOf (getString(R.string.theme_apptheme) to R.style.AppTheme, getString(R.string.theme_darktheme) to R.style.DarkTheme)
        val adapt= ArrayAdapter<String>(this,R.layout.list_simple_element, R.id.list_content, themeArray)
        list_theme.adapter=adapt
        list_theme.setOnItemClickListener { _, _, position, _ ->
            linksTheme[themeArray[position!!]]?.let { application.setTheme(it) }
        }
        continueButtonTask.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}