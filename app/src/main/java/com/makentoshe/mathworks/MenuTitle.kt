package com.makentoshe.mathworks

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.layout_title.*

class MenuTitle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_title)
        continueButton.setOnClickListener {
            startActivity(Intent(this,MenuZoneSelect::class.java))
        }
        settingsButton.setOnClickListener {
            startActivity(Intent(this,MenuSetup::class.java))
        }
        infoButton.setOnClickListener {
            startActivity(Intent(this,MenuInfo::class.java))
        }
        var i=0
        titleText.setOnClickListener{
            if (i<5) {i++;titleText.setTextColor(Color.rgb(i*32,128,32));titleText.text="MathW${i}rks"} else {startActivity(Intent(this,MenuHidden::class.java));finish()}
        }
    }
}