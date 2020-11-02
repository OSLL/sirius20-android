package com.makentoshe.mathworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.multiple_2.*
import kotlin.random.Random

val a: Int  = Random.nextInt(1, 9)
val b: Int  = Random.nextInt(1, 9)

class Multiple2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.multiple_2)
        textMultipleTask.text = "Умножь ${a} на ${b} и напиши ответ."
        checkMultiple.setOnClickListener {
            if (editTextMultiple1.getText().toString() == (a*b).toString() ) {
                textRes.text = "Верно"
            }
        }
    }

}