package com.makentoshe.mathworks.ZoneInteger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.example_theory.*

class IntegerAct4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.example_theory)
        val H: String = this.getString(R.string.Head1_4)
        val subH: String = this.getString(R.string.Subhead1_4)
        head.text = H
        subhead.text = subH
        val task: String = this.getString(R.string.textComposite)
        theoryText.text = task
        progressBarTheory.max = 5
        progressBarTheoryTrue.max = 5
        continueButtonTheory.setOnClickListener {
            progressBarTheory.progress += 1
            progressBarTheoryTrue.progress += 1
            val multIntent = Intent(this, CompositeActivity2::class.java)
            multIntent.putExtra("progress", progressBarTheory.progress)
            multIntent.putExtra("progress_true", progressBarTheoryTrue.progress)
            startActivity(multIntent)
        }

    }
}