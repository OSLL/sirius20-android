package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_lobby__main.*

class MenuZoneSelect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lobby__main)
        var lives:Int
        lives=intent.getIntExtra("lives",3)
        val intt=Intent(this,MenuActSelect::class.java)
        intt.putExtra("lives",lives)
        zoneIntegerWarp.setOnClickListener {
            intt.putExtra("zone","integer")
            startActivity(intt)
        }
        zoneModuloWarp.setOnClickListener {
            intt.putExtra("zone","modulo")
            startActivity(intt)
        }
        zoneCombineWarp.setOnClickListener {
            intt.putExtra("zone","combine")
            startActivity(intt)
        }
        zoneStatsWarp.setOnClickListener {
            intt.putExtra("zone","stats")
            startActivity(intt)
        }
        zoneFloatWarp.setOnClickListener {
            intt.putExtra("zone","float")
            startActivity(intt)
        }
        zoneFunctionWarp.setOnClickListener {
            intt.putExtra("zone","function")
            startActivity(intt)
        }
        zoneTriangleWarp.setOnClickListener {
            intt.putExtra("zone","triangle")
            startActivity(intt)
        }
        zonePlainWarp.setOnClickListener {
            intt.putExtra("zone","plain")
            startActivity(intt)
        }
        zoneStereoWarp.setOnClickListener {
            intt.putExtra("zone","stereo")
            startActivity(intt)
        }
        zoneDerivWarp.setOnClickListener {
            intt.putExtra("zone","deriv")
            startActivity(intt)
        }
        zoneComplexWarp.setOnClickListener {
            intt.putExtra("zone","complex")
            startActivity(intt)
        }
    }
}