package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_act_completed.*

class ActCompletedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_completed
        )
        var score=intent.getIntExtra("score",2)
        var max=intent.getIntExtra("max",5)
        var rate=(score.toDouble()/max.toDouble()*100.0).toInt()
        scoreRateBar.progress=rate
        scoreRateText.text= "$rate%"
        var lives=intent.getIntExtra("lives",3)
        backButton.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            intent.putExtra("lives",lives)
            startActivity(intent)
        }
    }
}