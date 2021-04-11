package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.layout_act_failure.*

class ActFailure : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_failure)
        continueButtonFailure.setOnClickListener {
            val intt=Intent(this,MenuActSelect::class.java); intt.putExtra("zone",intent.getStringExtra("zone"));startActivity(intt)
        }
    }
}