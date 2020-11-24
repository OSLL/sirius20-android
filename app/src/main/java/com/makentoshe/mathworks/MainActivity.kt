package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intt=Intent(this, MenuZoneSelect::class.java)
        startActivity(intt)
    }
}
