package com.makentoshe.mathworks.ZoneModulo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.*
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.layout_act_tasks.*

val digitToBase=arrayOf("0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f")
class ModuloAct1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headTask.text = intent.getStringExtra("zone")
        subheadTask.text = intent.getStringExtra("act")
        editTextTask.inputType=InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        var score=0
        var taskNames=arrayOf("act_modulo_1_task_1","act_modulo_1_task_2","act_modulo_1_task_3","act_modulo_1_task_4")
        var taskTypes=arrayOf(2,2,2,2)
        var taskQuantity=arrayOf(3,3,3,3)
        Log.d("Act2","Imported")
        radioGroupTask.visibility = View.GONE;
        var i=0; var step=0
        taskText.text=getText(resources.getIdentifier(taskNames[0], "string", packageName))
        var max=4
        var nums= correctNumberMakerForModuloAct1(0)
        var answer= answerGetterForModuloAct1(nums,0)
        var choice=""
        var a=""
        taskText.text=getString(resources.getIdentifier(taskNames[i], "string", packageName), convertToBase(nums[0],nums[2]), convertToBase(nums[1],nums[2]),nums[2].toString())
        editTextTask.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                a=editTextTask.text.toString().trim()
            }

        })
        continueButtonTask.setOnClickListener{
            Log.d("Act2","Button pressed")
            if (taskTypes[i]!=0) {
                choice = a.toLowerCase()
                if (choice == answer) score++
                Log.d("Act1", "Score is now $score")
            }
            step++
            i++
            if (i==taskTypes.size) {
                Log.d("Act1","The act is over, launching to the start menu")
                val intt= Intent(this, ActResult::class.java)
                intt.putExtra("score",score)
                intt.putExtra("max",max)
                intt.putExtra("zone", "modulo")
                intt.putExtra("act", 0)
                startActivity(intt)
            }

            Log.d("Act1","Strings updated")
            Log.d("Act1","Now showing frame $i")
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            Log.d("Act1","Variants unchecked, input cleaned")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) nums = correctNumberMakerForModuloAct1(i);
                answer=answerGetterForModuloAct1(nums,i)
                Log.d("Act1", "Answer: ${nums[0]}")
                Log.d("Act1", "Variants deployed")
                when (taskTypes[i]) {
                    0 -> {
                        radioGroupTask.visibility = View.GONE; editTextTask.visibility = View.GONE; descrText.visibility = View.VISIBLE; taskText.visibility = View.GONE;
                    }
                    2 -> {
                        radioGroupTask.visibility = View.GONE; editTextTask.visibility = View.VISIBLE; descrText.visibility = View.GONE; taskText.visibility = View.VISIBLE
                    }
                }
                Log.d("Act1", "Variants deployed")
                if (step <= max) progressBarTaskTrue.progress = ((score.toDouble() / max.toDouble()) * 100.0).toInt()
                if (step <= max) progressBarTask.progress = ((step.toDouble() / max.toDouble()) * 100.0).toInt()
                var name = ""
                if (i==0) name = getString(resources.getIdentifier(taskNames[i], "string", packageName), convertToBase(nums[0],nums[2]), convertToBase(nums[1],nums[2]),nums[2].toString())
                else name = getString(resources.getIdentifier(taskNames[i], "string", packageName), nums[0].toString(),convertToBase(nums[1],nums[0]), convertToBase(nums[2],nums[0]))
                taskText.text = name
            }
        }
}}
fun correctNumberMakerForModuloAct1 (i: Int): IntArray {
    var num1=0; var num2=0; var num3=0;
    when (i) {
        0->{
            do{num3=(8..16).random()}while(num3==10); num2=(num3..2*num3).random(); num1=(num3..2*num3).random()
        }
        1->{
            do{num1=(8..16).random()}while(num1==10); num2=(num1..num1*2).random(); num3=(2..num2-1).random()
        }
        2->{
            do{num1=(8..16).random()}while(num1==10); num2=(4..num1).random(); num3=(4..num1).random()
        }
        3 -> {
            do{num1=(8..16).random()}while(num1==10); num2=(4..20).random(); num3=(4..num1).random()*num2
        }
        else -> {}
    }
    return intArrayOf(num1,num2,num3)
    Log.d("Act1","Correct numbers created")
}
fun answerGetterForModuloAct1(nums: IntArray,i: Int):String{
    when (i) {
        0->{
            return convertToBase(nums[0]+nums[1],nums[2])
        }
        1->{
            return convertToBase(nums[1]-nums[2],nums[0])
        }
        2->{
            return convertToBase(nums[2]*nums[1],nums[0])
        }
        3->{
            return convertToBase(nums[2]/nums[1],nums[0])
        }
        else -> {return ""}
    }
}
fun convertToBase(dec: Int, base: Int): String {
    var dec = dec
    var i = ""
    while (dec != 0) {
        i= digitToBase[dec % base]+i
        dec /= base
    }
    if (i=="") i="0"
    return i
}
