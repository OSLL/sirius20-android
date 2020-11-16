package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var lives:Int
        lives=intent.getIntExtra("lives",3)
        lifeCounter.text="Lives: $lives"
        warpButtonToArithmeticAct1.setOnClickListener {
            val intt= Intent(this,ArithmeticAct1Activity::class.java)
            startActivity(intt)
        }
        warpButtonToArithmeticAct5.setOnClickListener {
            val intt= Intent(this,ArithmeticAct5Activity::class.java)
            intt.putExtra("lives",lives)
            startActivity(intt)
        }
        resurrectButton.setOnClickListener { if(lives<3) lives++; lifeCounter.text="Lives: $lives";  }
    }
}
