package com.makentoshe.mathworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.activity_arithmetic_act1.*

class ArithmeticAct5Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arithmetic_act1)
        var score=0
        var taskNames=arrayOf("act_1_5_task_1","act_1_5_task_2","act_1_5_task_3","act_1_5_task_4","act_1_5_task_5")
        var taskTypes=arrayOf(1,2,2,1,1)
        var taskQuantity=arrayOf(1,2,3,0,2)
        var i=0; var step=0
        taskText.text=getString(resources.getIdentifier(taskNames[0], "string", packageName))
        var max=5
        var variants=IntArray(5)
        var nums=IntArray(4)
        var choice=0
        var a=""
        var aint=0
        answerNumberInput.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                a=answerNumberInput.text.toString().trim()
                if (a.toIntOrNull()!=null) aint=a.toInt()
                Log.d("Act1","Input was modified: $a $aint")
            }

        })
    }
}