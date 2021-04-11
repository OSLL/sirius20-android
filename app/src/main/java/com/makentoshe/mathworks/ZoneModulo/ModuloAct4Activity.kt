package com.makentoshe.mathworks.ZoneModulo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.preference.PreferenceManager
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.layout_act_tasks.*
import java.util.Collections.shuffle

class ModuloAct4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headSetup.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
        var step =0
        var score =0
        val max =6
        progressBarTask.max=100
        progressBarTaskTrue.max=100
        var i=0
        val taskTypes=arrayOf(0,1,1,0,1,2,0,2,2)
        val taskQuantity=arrayOf(0,0,0,0,1,3,0,1,2)
        val taskNames=arrayOf("act_modulo_4_descr_1","act_modulo_4_task_1","act_modulo_4_task_2","act_modulo_4_descr_2","act_modulo_4_task_3","act_modulo_4_task_4","act_modulo_4_descr_3","act_modulo_4_task_5","act_modulo_4_task_6")
        descrText.text=getText(resources.getIdentifier(taskNames[0], "string", packageName))
        var values=arrayOf("","","","")
        var variants=arrayOf("","","","","")
        var choice: String
        var a=""
        taskText.visibility = View.GONE; descrText.visibility= View.VISIBLE
        radioGroupTask.visibility = View.GONE; editTextTask.visibility= View.GONE
        editTextTask.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                a=editTextTask.text.toString().trim()
            }

        })
        taskImage.visibility=View.VISIBLE
        taskImage.setImageResource(R.drawable.table_truth)
        continueButtonTask.setOnClickListener {
            taskImage.visibility=View.GONE
            if (taskTypes[i]!=0) {
                if (taskTypes[i]==1){
                    if (radioButtonTask1.isChecked) choice="0"
                    else if (radioButtonTask2.isChecked) choice="1"
                    else if (radioButtonTask3.isChecked) choice="2"
                    else if (radioButtonTask4.isChecked) choice="3"
                    else choice=editTextTask.toString()
                    if (choice==variants[4]) score++
                }
                if (taskTypes[i]==2){
                    if (a==values[0]) score++
                }
                step++
            }
            i++
            if (i==taskTypes.size) {
                val intt = Intent(this, ActResult::class.java)
                intt.putExtra("score", score)
                intt.putExtra("max", max)
                intt.putExtra("zone", "modulo")
                intt.putExtra("act", 3)
                if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusModuloAct4",0)<(score.toDouble()/max.toDouble()*100.0).toInt()) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusModuloAct4",(score.toDouble()/max.toDouble()*100.0).toInt()).apply()
                startActivity(intt)
            }
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) values = valueMakerForModuloAct4(i)
                variants = answerMakerForModuloAct4(values,i)
                when (taskTypes[i]) {
                    0 -> {
                        radioGroupTask.visibility = View.GONE; editTextTask.visibility = View.GONE; descrText.visibility = View.VISIBLE; taskText.visibility = View.GONE
                    }
                    2 -> {
                        radioGroupTask.visibility = View.GONE; editTextTask.visibility = View.VISIBLE; descrText.visibility = View.GONE; taskText.visibility = View.VISIBLE
                    }
                    1 -> {
                        radioGroupTask.visibility = View.VISIBLE; editTextTask.visibility = View.GONE; descrText.visibility = View.GONE; taskText.visibility = View.VISIBLE
                        radioButtonTask1.text = variants[0]
                        radioButtonTask2.text = variants[1]
                        radioButtonTask3.text = variants[2]
                        radioButtonTask4.text = variants[3]
                    }
                }
                if (step <= max) progressBarTaskTrue.progress = ((score.toDouble() / max.toDouble()) * 100.0).toInt()
                if (step <= max) progressBarTask.progress = ((step.toDouble() / max.toDouble()) * 100.0).toInt()
                descrText.text = getText((resources.getIdentifier(taskNames[i], "string", packageName)))
                val name = if (taskQuantity[i] == 1) {
                    getString(resources.getIdentifier(taskNames[i], "string", packageName), values[1])
                } else if (taskQuantity[i] == 3) {
                    getString(resources.getIdentifier(taskNames[i], "string", packageName), values[1], values[2], values[3])
                } else if (taskQuantity[i] == 2) {
                    getString(resources.getIdentifier(taskNames[i], "string", packageName), values[1], values[2])
                } else {
                    getString(resources.getIdentifier(taskNames[i], "string", packageName))
                }
                taskText.text = name
            }
        }
    }
}

fun answerMakerForModuloAct4(values: Array<String>, i: Int): Array<String> {
    val ans=values[0]
    val b= mutableListOf("","","","")
    b[0]=ans
    when(i){
        1->{b[1]="A⋀B"; b[2]="A∨B"; b[3]="A"}
        2->{b[1]="A=1; B=0"; b[2]="A=0; B=1"; b[3]="A=0; B=0"}
        4->{b[1]="A";b[2]="${b[0]}∨A";b[3]="${b[0]}⊕A"}
        5->{b[1]="${1-ans.toInt()}";b[2]="M";b[3]="N"}
        7->{b[1]="X"; b[2]="1"; b[3]="~X"}
        8->{b[1]="${ans.toInt()+(1..5).random()}"; b[2]="${ans.toInt()-(1..5).random()}";b[3]="${ans.toInt()+(6..15).random()}"}
    }
    shuffle(b)
    return arrayOf(b[0],b[1],b[2],b[3],"${b.indexOf(ans)}")
}
fun valueMakerForModuloAct4(i: Int): Array<String>{
    var ans="";var n1=""; var n2=""; var n3=""; val num1: Int; var num2=0; var num3=0
    when(i){
        1->{ans="A⊕B"}
        2->{ans="A=1; B=1"}
        4->{when((0..3).random()){
            0 -> {n1="B⋀(B∨A)"; ans="B"}
            1 -> {n1="A⋀B∨A⋀¬B∨¬A⋀B"; ans="A∨B"}
            2 -> {n1="A⋀B∨¬A⋀¬B"; ans="¬A⊕B"}
            3 -> {n1="A⊕(B∨¬B)";ans="¬A"}
        };n2="$num2";n3="$num3"}
        5->{when((0..3).random()){
            0 -> {n1="¬(M⊕N)⋀N"; num2=(0..1).random(); num3=1; ans="${1-(num2 xor 1)}"}
            1 -> {n1="¬N∨M⋀(M∨N)"; num2=(0..1).random(); num3=(0..1).random(); ans="${1-num3 or num2}"}
            2 -> {n1="(M∨N)⋀(M∨¬N)"; num2=(0..1).random(); num3=(0..1).random(); ans="$num2"}
            3 -> {n1="¬(M⊕N)"; num2=(0..1).random(); num3=(0..1).random(); ans="${1-(num3 xor num2)}"}
        };n2="$num2";n3="$num3"}
        7->{when((0..1).random()){
            0 -> {n1= "X ⋀ ¬X"; ans="0"}
            1 -> {n1= "X ⊕ X"; ans="0"}
        }}
        8->{num1=(0..15).random();do{num2=(1..15).random()}while(num2==num1); n1="$num1"; n2="$num2"; n3="$num3"; ans="${(num1 or num2)}"}
    }
    return arrayOf(ans,n1,n2,n3)
}