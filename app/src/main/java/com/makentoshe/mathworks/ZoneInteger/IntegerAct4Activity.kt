package com.makentoshe.mathworks.ZoneInteger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.makentoshe.mathworks.CompositeActivity2
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.multiple_1.*

class IntegerAct4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.composite_1)
        ContinueButtonRes.setOnClickListener {
            val multIntent = Intent(this, CompositeActivity2::class.java)
            multIntent.putExtra("progress", 1)
            startActivity(multIntent)

        }

    }
}