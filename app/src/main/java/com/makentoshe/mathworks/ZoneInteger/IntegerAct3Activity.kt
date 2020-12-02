package com.makentoshe.mathworks.ZoneInteger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.activity_act_type1.*

class IntegerAct3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_type1)
        zoneName.text=getString(resources.getIdentifier("zone_integer_name", "string", packageName))
        actName.text=getString(resources.getIdentifier("act_integer_3_name", "string", packageName))
        var taskNames=arrayOf("act_integer_3_descr_1","act_integer_3_task_1","act_integer_3_task_2","act_integer_3_task_3","act_integer_3_task_4","act_integer_3_task_5","act_integer_3_task_6")
        taskText.text=getString(resources.getIdentifier(taskNames[0], "string", packageName))
    }
}