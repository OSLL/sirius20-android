package com.makentoshe.mathworks.ZoneModulo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.*
import android.view.View
import androidx.preference.PreferenceManager
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.layout_act_tasks.*
import java.util.*

class ModuloAct1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headSetup.text = intent.getStringExtra("zone")
        subheadTask.text = intent.getStringExtra("act")
        editTextTask.inputType=InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        var score=0
        val taskNames=arrayOf("act_modulo_1_task_1","act_modulo_1_task_2","act_modulo_1_task_3","act_modulo_1_task_4")
        val taskTypes=arrayOf(2,2,2,2)
        radioGroupTask.visibility = View.GONE
        var i=0; var step=0
        taskText.text=getText(resources.getIdentifier(taskNames[0], "string", packageName))
        val max=4
        var nums= correctNumberMakerForModuloAct1(0)
        var answer= answerGetterForModuloAct1(nums,0)
        var choice: String
        var a=""
        taskText.text=getString(resources.getIdentifier(taskNames[i], "string", packageName), nums[0].toString(nums[2]), nums[1].toString(nums[2]),nums[2].toString())
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
            if (taskTypes[i]!=0) {
                choice = a.toLowerCase(Locale.ROOT)
                if (choice == answer) score++
            }
            step++
            i++
            if (i==taskTypes.size) {
                val intt= Intent(this, ActResult::class.java)
                intt.putExtra("score",score)
                intt.putExtra("max",max)
                intt.putExtra("zone", "modulo")
                intt.putExtra("act", 0)
                if (score==max) {PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusModuloAct1",1).apply()}
                startActivity(intt)
            }
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) nums = correctNumberMakerForModuloAct1(i)
                answer=answerGetterForModuloAct1(nums,i)
                when (taskTypes[i]) {
                    0 -> {
                        radioGroupTask.visibility = View.GONE; editTextTask.visibility = View.GONE; descrText.visibility = View.VISIBLE; taskText.visibility = View.GONE
                    }
                    2 -> {
                        radioGroupTask.visibility = View.GONE; editTextTask.visibility = View.VISIBLE; descrText.visibility = View.GONE; taskText.visibility = View.VISIBLE
                    }
                }
                if (step <= max) progressBarTaskTrue.progress = ((score.toDouble() / max.toDouble()) * 100.0).toInt()
                if (step <= max) progressBarTask.progress = ((step.toDouble() / max.toDouble()) * 100.0).toInt()
                val name: String
                if (i==0) name = getString(resources.getIdentifier(taskNames[i], "string", packageName), nums[0].toString(nums[2]), nums[1].toString(nums[2]),nums[2].toString())
                else name = getString(resources.getIdentifier(taskNames[i], "string", packageName), nums[0].toString(),nums[1].toString(nums[0]), nums[2].toString(nums[0]))
                taskText.text = name
            }
        }
}}
fun correctNumberMakerForModuloAct1 (i: Int): IntArray {
    var num1=0; var num2=0; var num3=0
    when (i) {
        0->{
            num3=((8..9)+(11..16)).random(); num2=(num3..2*num3).random(); num1=(num3..2*num3).random()
        }
        1->{
            num1=((8..9)+(11..16)).random(); num2=(num1..num1*2).random(); num3=(2..num2-1).random()
        }
        2->{
            num1=((8..9)+(11..16)).random(); num2=(4..num1).random(); num3=(4..num1).random()
        }
        3 -> {
            num1=((8..9)+(11..16)).random(); num2=(4..20).random(); num3=(4..num1).random()*num2
        }
        else -> {}
    }
    return intArrayOf(num1,num2,num3)
}
fun answerGetterForModuloAct1(nums: IntArray,i: Int):String{
    when (i) {
        0->{
            return (nums[0]+nums[1]).toString(nums[2])
        }
        1->{
            return (nums[1]-nums[2]).toString(nums[0])
        }
        2->{
            return (nums[2]*nums[1]).toString(nums[0])
        }
        3->{
            return (nums[2]/nums[1]).toString(nums[0])
        }
        else -> {return ""}
    }
}

