package com.makentoshe.mathworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.example_result.*
import kotlinx.android.synthetic.main.example_task.*

var numTask: Int = 0

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.example_result)
        headResult.text = intent.getStringExtra("h")
        subheadResult.text = intent.getStringExtra("subh")
        resultProgressBar.progress = intent.getIntExtra("answers", 0)
        numTask = intent.getIntExtra("tasks", 0)
        if (intent.getIntExtra("answers", 0) == numTask) {
            textCongratulations.text = this.getString(R.string.TextResultGood)
        }
        resultProgressBar.max = numTask
        val task: String = this.getString(R.string.result)
        val taskS: String = String.format(task, ((intent.getIntExtra("answers", 0)*100)/numTask))
        textResultPC.text = taskS
    }
}