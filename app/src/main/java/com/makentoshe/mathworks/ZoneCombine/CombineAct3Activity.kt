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
import kotlin.math.pow

class CombineAct3Activity : AppCompatActivity() {
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
        val formulas=mapOf(0 to "(1)A^k_n=n\\cdot (n-1)*\\ldots\\cdot (n-k+1)\\\\(2)A^k_n=\\frac{n!}{n-k!}\\\\(3)A^n_n=\\frac{n!}{0!}=n!", 4 to "(1)A^k_n=n^k")
        val formula_steps=arrayOf(0,4)
        mathview.latex=formulas[0].toString()
        var step =0
        var score =0
        val max=6
        var i=0
        val taskTypes=arrayOf(0,1,2,2,0,1,2,1)
        val taskQuantity=arrayOf(0,2,1,2,0,2,1,3)
        val taskNames=arrayOf("act_combine_3_descr_1","act_combine_3_task_1","act_combine_3_task_2","act_combine_3_task_3","act_combine_3_descr_2","act_combine_3_task_4","act_combine_3_task_5","act_combine_3_task_6")
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
            taskImage.visibility= View.GONE
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
                intt.putExtra("act", 2)
                if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusCombineAct3",0)<(score.toDouble()/max.toDouble()*100.0).toInt()) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusCombineAct3",(score.toDouble()/max.toDouble()*100.0).toInt()).apply()

                startActivity(intt)
            }
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) values = valueMakerForCombineAct3(i)
                variants = answerMakerForCombineAct3(values,i)
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
                val name: String
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
fun valueMakerForCombineAct3(i: Int): IntArray{
    var ans=0; var num1=0; var num2=0; var num3=0; var passable=0
    when(i) {
        1 -> {num2=(2..4).random();num1=(num2+2..num2+3).random(); ans=arrangements(num2,num1);passable=num1*10+num2
        }
        2 -> {num1=(6..9).random();ans=arrangements(3,num1)
        }
        3 -> {num1=(3..5).random();num2=(7..9).random();ans=arrangements(num1,num2)
        }
        5 -> {num1=(6..8).random();num2=(2..4).random();ans=num2.toDouble().pow(num1).toInt(); passable=num1*10+num2
        }
        6 -> {num1=(4..10).random();ans=2.0.pow(num1).toInt()
        }
        7 -> {num1=(2..4).random();num3=6-num1;num2=(3..4).random()*2;ans=(10.0.pow(num3).toInt())*(num2.toDouble().pow(num1).toInt());passable=num1*100+num2
        }
    }
    return intArrayOf(ans,num1,num2,num3,passable)
}
fun answerMakerForCombineAct3(values: IntArray, i: Int): IntArray {
    val ans=values[0]
    var b= MutableList<Int>(4){0}
    b[0]=ans
    when(i){
        1->{b[1]=factorial(values[4]/10);b[2]=factorial(values[4]%10);b[3]=(values[4]/10).toDouble().pow(values[4]%10).toInt()}
        2->{}
        3->{}
        5->{b[1]=(values[4]/10).toDouble().pow(values[4]%10).toInt();b[2]= arrangements(values[4]%10,values[4]/10); b[3]=factorial(values[4]/10)}
        6->{}
        7->{b[1]=2*ans; b[2]=(10.0.pow(6-values[4]/100).toInt()); b[3]=((values[4]%100).toDouble().pow(values[4]/100).toInt())}
    }
    shuffle(b)
    var p=b.indexOf(ans)
    return intArrayOf(b[0],b[1],b[2],b[3],p)
}
fun arrangements (k: Int, n: Int): Int {
    return factorial(n)/factorial(n-k)
}