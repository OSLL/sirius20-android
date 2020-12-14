package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.layout_title.*

class MenuTitle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_title)
        continueButton.setOnClickListener {
            val intt= Intent(this,MenuZoneSelect::class.java)
            startActivity(intt)
        }
    }
}