package com.makentoshe.mathworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.multiple_result.*

class MultipleResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.multiple_result)
        progressBarResult.progress = intent.getIntExtra("answers", 0)
        val task: String = this.getString(R.string.result)
        val taskS: String = String.format(task, ((intent.getIntExtra("answers", 0)*100)/6).toInt())
        textResult.setText(taskS)
    }
}