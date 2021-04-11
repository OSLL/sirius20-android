package com.makentoshe.mathworks.ZoneCombine

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.annotation.RequiresApi
import androidx.preference.PreferenceManager
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.layout_act_tasks.*
import java.util.Collections.shuffle

class CombineAct2Activity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headSetup.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
        mathview.visibility= View.VISIBLE
        mathview.fontSize=40.0F
        mathview.textColor=descrText.currentTextColor
        val formulas=mapOf(0 to "(1)P_n=n!\\\\(2)n!=\\prod_{i=1}^{n}i=1\\cdot 2\\cdot \\ldots\\cdot n", 3 to "(1)P_n=\\frac{n!}{k_1\\cdot k_2\\cdot \\ldots\\cdot k_m}")
        val formula_steps=arrayOf(0,3)
        mathview.latex=formulas[0].toString()
        var step =0
        var score =0
        val max =5
        var i=0
        val taskTypes=arrayOf(0,2,1,0,1,2,2)
        val taskQuantity=arrayOf(0,1,1,0,3,3,3)
        val taskNames=arrayOf("act_combine_2_descr_1","act_combine_2_task_1","act_combine_2_task_2","act_combine_2_descr_2","act_combine_2_task_3","act_combine_2_task_4","act_combine_2_task_5")
        descrText.text=getText(resources.getIdentifier(taskNames[0], "string", packageName))
        var values=IntArray(5)
        var variants=IntArray(5)
        var choice: Int
        var a=""
        taskText.visibility = View.GONE; descrText.visibility= View.VISIBLE
        radioGroupTask.visibility = View.GONE; editTextTask.visibility= View.GONE
        editTextTask.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { a=editTextTask.text.toString().trim() }
        })
        continueButtonTask.setOnClickListener {
            taskImage.visibility=View.GONE
            if (taskTypes[i]!=0) {
                if (taskTypes[i]==1){
                    if (radioButtonTask1.isChecked) choice=0
                    else if (radioButtonTask2.isChecked) choice=1
                    else if (radioButtonTask3.isChecked) choice=2
                    else if (radioButtonTask4.isChecked) choice=3
                    else choice=4
                    if (choice==variants[4]) score++
                }
                if (taskTypes[i]==2){
                    if (a=="${values[0]}") score++
                }
                step++
            }
            i++
            if (i in formula_steps) {mathview.visibility=View.VISIBLE; mathview.latex= formulas[i].toString()} else mathview.visibility=View.GONE
            if (i==taskTypes.size) {
                val intt = Intent(this, ActResult::class.java)
                intt.putExtra("score", score)
                intt.putExtra("max", max)
                intt.putExtra("zone", "combine")
                intt.putExtra("act", 1)
                if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusCombineAct2",0)<(score.toDouble()/max.toDouble()*100.0).toInt()) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusCombineAct2",(score.toDouble()/max.toDouble()*100.0).toInt()).apply()
                startActivity(intt)
            }
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) values = valueMakerForCombineAct2(i)
                variants = answerMakerForCombineAct2(values,i)
                when (taskTypes[i]) {
                    0 -> {
                        radioGroupTask.visibility = View.GONE; editTextTask.visibility = View.GONE; descrText.visibility = View.VISIBLE; taskText.visibility = View.GONE
                    }
                    2 -> {
                        radioGroupTask.visibility = View.GONE; editTextTask.visibility = View.VISIBLE; descrText.visibility = View.GONE; taskText.visibility = View.VISIBLE
                    }
                    1 -> {
                        radioGroupTask.visibility = View.VISIBLE; editTextTask.visibility = View.GONE; descrText.visibility = View.GONE; taskText.visibility = View.VISIBLE
                        radioButtonTask1.text = "${variants[0]}"
                        radioButtonTask2.text = "${variants[1]}"
                        radioButtonTask3.text = "${variants[2]}"
                        radioButtonTask4.text = "${variants[3]}"
                    }
                }
                if (step <= max) progressBarTaskTrue.progress = ((score.toDouble() / max.toDouble()) * 100.0).toInt()
                if (step <= max) progressBarTask.progress = ((step.toDouble() / max.toDouble()) * 100.0).toInt()
                var name: String
                descrText.text = getText((resources.getIdentifier(taskNames[i], "string", packageName)))
                if (taskQuantity[i] == 1) {
                    name = getString(resources.getIdentifier(taskNames[i], "string", packageName), values[1])
                } else if (taskQuantity[i] == 2) {
                    name = getString(resources.getIdentifier(taskNames[i], "string", packageName), values[1], values[2])
                } else if (taskQuantity[i] == 3) {
                    name = getString(resources.getIdentifier(taskNames[i], "string", packageName), values[1], values[2], values[3])
                } else {
                    name = getString(resources.getIdentifier(taskNames[i], "string", packageName))
                }
                taskText.text = name
            }
        }
    }
}
fun valueMakerForCombineAct2(i: Int): IntArray{
    var ans=0; var num1=0; var num2=0; var num3=0; var passable=0
    when(i) {
        1 -> {num1=(4..6).random();ans= factorial(num1)
        }
        2 -> {num1=(4..6).random();ans=factorial(num1)+factorial(num1-1);passable=num1
        }
        4 -> {num1=(2..4).random();num3=6;num2=num3-num1;ans= factorial(num3)/(factorial(num1)*factorial(num2));passable=num1
        }
        5 -> { num1=(1..9).random();num2=(1..9).random();num3=(1..9).random();
            if (num1==num2&&num2==num3) ans=1
            else if (num1==num2||num2==num3||num1==num3) ans=15
            else ans=90
        }
        6 -> {num1=(1..3).random(); do {num2=(1..3).random();num3=6-num1-num2}while(num1+num2==6);ans=720/ factorial(num1)/ factorial(num2)/ factorial(num3)
        }
    }
    return intArrayOf(ans,num1,num2,num3,passable)
}
fun answerMakerForCombineAct2(values: IntArray, i: Int): IntArray {
    val ans=values[0]
    var b= MutableList<Int>(4){0}
    b[0]=ans
    when(i){
        1->{}
        2->{b[1]=values[4]*(values[4]-1); b[2]= factorial(values[4]); b[3]= factorial(values[4]-1)}
        4->{if (values[4]==2 || values[4]==4) b[1]=20 else b[1]=15; b[2]=48; b[3]=36}
        5->{}
        6->{}
    }
    shuffle(b)
    var p=b.indexOf(ans)
    return intArrayOf(b[0],b[1],b[2],b[3],p)
}
fun factorial (x: Int): Int {
    return if (x<=1) 1 else x*factorial(x-1)
}

