package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.multiple_1.*
import kotlinx.android.synthetic.main.multiple_2.*
import kotlin.random.Random


class MultipleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.multiple_1)
        ContinueMultiple.setOnClickListener {
            val multIntent = Intent(this, Multiple2Activity::class.java)
            startActivity(multIntent)
        }

    }
}