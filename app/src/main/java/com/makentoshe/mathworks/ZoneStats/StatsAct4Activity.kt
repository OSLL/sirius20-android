package com.makentoshe.mathworks.ZoneStats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.layout_act_tasks.*

class StatsAct4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headSetup.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
    }
}