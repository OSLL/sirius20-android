package com.makentoshe.mathworks.ZoneInteger

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.example_task.*
import kotlinx.android.synthetic.main.example_theory.*


class IntegerAct2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.example_theory)
        if (intent.getIntExtra("progress", 0) != 0) {
            progressBarTheory.progress = intent.getIntExtra("progress", 0)
            progressBarTheoryTrue.progress = intent.getIntExtra("progress_true", 0)
        }
        when (progressBarTheory.progress) {
            0 -> {
                val H: String = this.getString(R.string.Head1)
                val subH: String = this.getString(R.string.Subhead1_1)
                head.text = H
                subhead.text = subH
                val task: String = this.getString(R.string.textMultiple1)
                theoryText.text = task
                theoryImage.visibility = View.VISIBLE
                theoryImage.setImageResource(R.drawable.table_multiple)
            }
            4 -> {
                val H: String = this.getString(R.string.Head1)
                val subH: String = this.getString(R.string.Subhead1_1)
                head.text = H
                subhead.text = subH
                val task: String = this.getString(R.string.textMultiple2)
                theoryText.text = task
            }
        }
        continueButtonTheory.setOnClickListener {
            progressBarTheory.progress += 1
            progressBarTheoryTrue.progress += 1
            when (progressBarTheory.progress) {
                1 -> {
                    val multIntent = Intent(this, Multiple2Activity::class.java)
                    multIntent.putExtra("progress", progressBarTheory.progress)
                    multIntent.putExtra("progress_true", progressBarTheoryTrue.progress)
                    startActivity(multIntent)
                }
                5 -> {
                    val multIntent = Intent(this, Multiple2Activity::class.java)
                    multIntent.putExtra("progress", progressBarTheory.progress)
                    multIntent.putExtra("progress_true", progressBarTheoryTrue.progress)
                    startActivity(multIntent)
                }
            }
        }

    }
}