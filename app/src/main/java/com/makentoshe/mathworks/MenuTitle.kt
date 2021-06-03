package com.makentoshe.mathworks

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.layout_title.*

class MenuTitle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var prefs=PreferenceManager.getDefaultSharedPreferences(applicationContext)
        setTheme(prefs.getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_title)
        continueButton.setOnClickListener {
            if (prefs.getBoolean("visitedTestBoss",false)) startActivity(Intent(this,MenuZoneSelect::class.java)) else startActivity(Intent(this,TestBossActivity::class.java))
        }
        settingsButton.setOnClickListener {
            startActivity(Intent(this,MenuSetup::class.java))
        }
        infoButton.setOnClickListener {
            startActivity(Intent(this,MenuInfo::class.java))
        }
        var i=0
        titleText.setOnClickListener{
            if (i<6 && prefs.getBoolean("lockedMenuHidden",true)) {i++;Log.d("???","$i");if(i>2) {
                when(7-i){
                    1->{
                        Toast.makeText(applicationContext,getString(R.string.toast_hidden_singular,7-i),LENGTH_SHORT).show()}
                    0->{}
                    else->{
                        Toast.makeText(applicationContext,getString(R.string.toast_hidden_plural,7-i),LENGTH_SHORT).show()
                    }
                }
            }} else {startActivity(Intent(this,MenuHidden::class.java));finish(); prefs.edit().putBoolean("lockedMenuHidden",false).apply()}
        }
    }
}