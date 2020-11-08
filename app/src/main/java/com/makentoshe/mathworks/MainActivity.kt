package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        warpButtonToArithmeticAct1.setOnClickListener {
            val intt= Intent(this,ArithmeticAct1Activity::class.java)
            startActivity(intt)
        }
        warpButtonToArithmeticAct5.setOnClickListener {
            val intt= Intent(this,ArithmeticAct1Activity::class.java)
            startActivity(intt)
        }
    }
}
