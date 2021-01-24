package com.makentoshe.mathworks.ZoneDeriv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.layout_act_tasks.*

class DerivAct5Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headTask.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
    }
}