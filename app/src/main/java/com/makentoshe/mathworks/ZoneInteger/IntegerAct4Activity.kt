package com.makentoshe.mathworks.ZoneInteger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.layout_act_tasks.*
import kotlin.random.Random

class IntegerAct4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headTask.text = intent.getStringExtra("zone")
        subheadTask.text = intent.getStringExtra("act")
        var score = 0
        var taskNames = arrayOf("act_integer_4_descr_1", "act_integer_4_task_1", "act_integer_4_task_2", "act_integer_4_task_3", "act_integer_4_task_4")
        var taskTypes = arrayOf(0, 2, 1, 2, 2)
        var taskQuantity = arrayOf(0, 1, 2, 0, 0)
        Log.d("Act1", "Imported")
        radioGroupTask.visibility = View.GONE; editTextTask.visibility = View.GONE
        var i = 0;
        var step = 0
        descrText.text = getText(resources.getIdentifier(taskNames[0], "string", packageName))
        taskText.visibility = View.GONE; descrText.visibility= View.VISIBLE
        var max = 4
        var choice = ""
        var a = ""
        var aint = 0
        var answer = ""
        var nums = intArrayOf(0, 0)
        editTextTask.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                a = editTextTask.text.toString().trim()
                Log.d("Act1", "Input was modified: $a")
            }

        })
        continueButtonTask.setOnClickListener {
            Log.d("Act1", "Button pressed")
            if (taskTypes[i] != 0) {
                if (taskTypes[i] == 1) {
                    if (radioButtonTask1.isChecked) choice = "0"
                    else if (radioButtonTask2.isChecked) choice = "1"
                    else choice = editTextTask.toString()
                    if (choice == answer) score++
                    Log.d("Act1", "Score is now $score")
                }
                if (taskTypes[i] == 2) {
                    Log.d("Act1", "Text task, answer is ${nums[0]}")
                    if (a == answer) score++
                    Log.d("Act1", "Score is now $score")
                }
                step++
                Log.d("Act1", "Proceeding to  task, step=${step}, score=${score}")
            }
            i++
            if (i == taskTypes.size) {
                Log.d("Act1", "The act is over, launching to the start menu")
                val intt = Intent(this, ActResult::class.java)
                intt.putExtra("score", score)
                intt.putExtra("max", max)
                intt.putExtra("zone", "integer")
                intt.putExtra("act", 3)
                startActivity(intt)
            }

            Log.d("Act1", "Strings updated")
            Log.d("Act1", "Now showing frame $i")
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            Log.d("Act1", "Variants unchecked, input cleaned")
            if (i < taskTypes.size) {
                if (taskTypes[i] != 0) {nums = correctNumberMakerForIntegerAct4(i); answer=AnswerGetterForIntegerAct4(i,nums)}
                Log.d("Act1", "Answer: ${nums[0]}")
                Log.d("Act1", "Variants deployed")
                when (taskTypes[i]) {
                    0 -> {
                        radioGroupTask.visibility = View.GONE; editTextTask.visibility = View.GONE; descrText.visibility = View.VISIBLE; taskText.visibility = View.GONE;
                    }
                    2 -> {
                        radioGroupTask.visibility = View.GONE; editTextTask.visibility = View.VISIBLE; descrText.visibility = View.GONE; taskText.visibility = View.VISIBLE
                    }
                    1 -> {
                        radioGroupTask.visibility = View.VISIBLE; editTextTask.visibility = View.GONE; descrText.visibility = View.GONE; taskText.visibility = View.VISIBLE
                        radioButtonTask3.visibility = View.GONE
                        radioButtonTask4.visibility = View.GONE
                        radioButtonTask1.text = getText(R.string.act_integer_4_task_2_variant_0)
                        radioButtonTask2.text = getText(R.string.act_integer_4_task_2_variant_1)
                    }
                }
                Log.d("Act1", "Variants deployed")
                if (step <= max) progressBarTaskTrue.progress = ((score.toDouble() / max.toDouble()) * 100.0).toInt()
                if (step <= max) progressBarTask.progress = ((step.toDouble() / max.toDouble()) * 100.0).toInt()
                var name = ""
                descrText.text = getText(resources.getIdentifier(taskNames[i], "string", packageName))
                if (taskQuantity[i] == 2) {
                    name = getString(resources.getIdentifier(taskNames[i], "string", packageName), nums[0], nums[1])
                } else if (taskQuantity[i] == 1) {
                    name = getString(resources.getIdentifier(taskNames[i], "string", packageName), nums[0])
                } else {
                    name = getString(resources.getIdentifier(taskNames[i], "string", packageName))
                }
                taskText.text = name
            }
        }
    }
}
fun Decomposition (x: Int): String {
        var str = ""
        var x1: Int = x
        for (i in 2..x) {
            if (x1 == 1) {
                break
            }
            while (x1 % i == 0) {
                x1 /= i
                str += i.toString()
            }

        }
        return str
}
fun correctNumberMakerForIntegerAct4 (i: Int): IntArray {
    var b= intArrayOf(0,0)
    when(i) {
        1 -> {b[0]=(10..30).random()}
        2 -> {b[0]=(10..999).random()*10+1;b[1]=(10..9999).random()}
        else -> b= intArrayOf(0,0)
    }
    return b
}
fun AnswerGetterForIntegerAct4 (i: Int, n: IntArray): String {
    when (i) {
        1 -> {return Decomposition(n[0])}
        2 -> {return "1"}
        3 -> {return "2"}
        4 -> {return "2"}
        else -> return "-1"
    }
}