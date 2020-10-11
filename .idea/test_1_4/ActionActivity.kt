package com.quickmaths.quickmaths

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_action.*

class ActionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action)
        title_button_load.setOnClickListener{
            val intt = Intent(this, TestActivity1::class.java)
                startActivity(intt)
        }
    }
}