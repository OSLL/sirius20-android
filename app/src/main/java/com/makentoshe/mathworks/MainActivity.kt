package com.makentoshe.mathworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.task.*
import kotlinx.android.synthetic.main.task2.*
import kotlinx.android.synthetic.main.task_result.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageButton4.setOnClickListener {
            setContentView(R.layout.task)

        }
        SettingsButton.setOnClickListener {
            setContentView(R.layout.settings)

        }
        ContinueButton.setOnClickListener {
            setContentView(R.layout.task2)

        }
        checkButton.setOnClickListener {
            setContentView(R.layout.task_result)

        }
        ContinueButtonRes.setOnClickListener {
            setContentView(R.layout.task2)

        }
    }
}
