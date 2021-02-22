package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.layout_menu_setup.*

class MenuSetup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_menu_setup)
        setupToMainButton.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}