package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.multiple_1.*


class MultipleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.multiple_1)
        if (intent.getIntExtra("progress_2", 0) == 4) {
            imageView.visibility = View.INVISIBLE
            progressBarZone1Act2.progress = intent.getIntExtra("progress_2", 0)
            val task: String = this.getString(R.string.textMultiple2)
            val taskS: String = String.format(task)
            textMultiple1.setText(taskS)
        }
        ContinueMultiple.setOnClickListener {
            val multIntent = Intent(this, Multiple2Activity::class.java)
            multIntent.putExtra("progress", progressBarZone1Act2.progress+1)
            startActivity(multIntent)

        }

    }
}