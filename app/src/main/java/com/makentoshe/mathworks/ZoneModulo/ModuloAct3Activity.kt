package com.makentoshe.mathworks.ZoneModulo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import androidx.preference.PreferenceManager
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import com.makentoshe.mathworks.hideKeyboard
import kotlinx.android.synthetic.main.layout_act_tasks.*
import java.util.Collections.shuffle
import kotlin.math.pow

class ModuloAct3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headSetup.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
        editTextTask.inputType= InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        var step =0
        var score =0
        val max =5
        progressBarTask.max=100
        progressBarTaskTrue.max=100
        var i=0
        val taskTypes=arrayOf(0,1,1,2,0,2,2)
        val taskQuantity=arrayOf(0,1,2,2,0,2,3)
        val taskNames=arrayOf("act_modulo_3_descr_1","act_modulo_3_task_1","act_modulo_3_task_2","act_modulo_3_task_2","act_modulo_3_descr_2","act_modulo_3_task_4","act_modulo_3_task_5")
        descrText.text=getText(resources.getIdentifier(taskNames[0], "string", packageName))
        var values=arrayOf("","","","")
        var variants=arrayOf("","","","","")
        var choice: String
        var a=""
        taskText.visibility = View.GONE; descrText.visibility= View.VISIBLE
        radioGroupTask.visibility = View.GONE; editTextTask.visibility= View.GONE
        editTextTask.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {a=editTextTask.text.toString().trim()}
        })
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
            if(i<taskTypes.size) if(taskTypes[i]!=2) hideKeyboard(this)
            if (i==taskTypes.size) {
                val intt = Intent(this, ActResult::class.java)
                intt.putExtra("score", score)
                intt.putExtra("max", max)
                intt.putExtra("zone", "modulo")
                intt.putExtra("act", 2)
                if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusModuloAct3",0)<(score.toDouble()/max.toDouble()*100.0).toInt()) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusModuloAct3",(score.toDouble()/max.toDouble()*100.0).toInt()).apply()
                if ((score.toDouble()/max.toDouble()*100.0).toInt()>=50 && PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusModuloAct4",-1)<0) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusModuloAct4",0).apply()
                startActivity(intt)
            }
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) values = valueMakerForModuloAct3(i)
                variants = answerMakerForModuloAct3(values,i)
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

fun valueMakerForModuloAct3(i: Int): Array<String>{
    var ans="";var n1=""; var n2=""; var n3=""; val num1: Int; var num2=0; var num3=0; val sub=arrayOf("₀","₁","₂","₃","₄","₅","₆","₇","₈","₉","₁₀","₁₁","₁₂","₁₃","₁₄","₁₅","₁₆");
    val sup=arrayOf("⁰","¹","²","³","⁴")
    when(i){
        1->{num1=((4..9)+(11..16)).random();num2=(2..3).random();num3=(num1.toFloat().pow(2).toInt()+2 until num1.toFloat().pow(num2+1).toInt()).random();n2="$num1 $num2 $num3"
        n1=num3.toString(num1)
        val arr=n1.split("").slice(1..n1.length).toMutableList(); n1+=sub[num1];for (i in(arr.indices)){arr[i]=arr[i].toInt(16).toString();arr[i]+="·$num1"+sup[arr.size-1-i]}; ans=arr.joinToString(separator=" + ")}
        2->{num2=((4..9)).random();n2="$num2";num1=(num2*num2+2..num2*num2*num2).random();n1=num1.toString(num2);ans="$num1"}
        3->{num2=(11..16).random();n2="$num2";num1=(num2+2..num2*num2).random();n1=num1.toString(num2);ans="$num1"}
        5->{num2=((4..9)+(11..16)).random();num1=(num2+2..num2*num2).random();n1="$num1";n2="$num2";ans=num1.toString(num2)}
        6->{num2=((4..9)+(11..16)).random();do{num3=((4..9)+(11..16)).random()}while(num3==num2);num1=(num2+2..num2*num2).random();n1="$num1";n2="$num2";n3="$num3";ans=n1.toInt(num2).toString(num3)}
    }
    return arrayOf(ans,n1,n2,n3)}
fun answerMakerForModuloAct3(values: Array<String>, i: Int): Array<String> {
    val ans=values[0]
    val sub=arrayOf("₀","₁","₂","₃","₄","₅","₆","₇","₈","₉","₁₀","₁₁","₁₂","₁₃","₁₄","₁₅","₁₆");
    val sup=arrayOf("⁰","¹","²","³","⁴")
    val b= mutableListOf("","","","")
    b[0]=ans
    when(i){
        1->{val num1=values[2].split(" ")[0].toInt(); val num2=values[2].split(" ")[1].toInt(); val num3=values[2].split(" ")[2].toInt()
            var base:Int; do{base=((4..9)+(11..16)).random()}while(base==num1)
            var arr= num3.toString(num1).split("").slice(1..num3.toString(num1).length).toMutableList(); for (i in(arr.indices)){arr[i]=arr[i].toInt(16).toString();arr[i]+="·$base"+sup[arr.size-1-i]}; b[1]=arr.joinToString(separator=" + ")
            arr =(num3-1).toString(num1).split("").slice(1..(num3-1).toString(num1).length).toMutableList(); for (i in(arr.indices)){arr[i]=arr[i].toInt(16).toString();arr[i]+="·$num1"+sup[arr.size-1-i]}; b[2]=arr.joinToString(separator=" + ")
            arr =(num3-2).toString(num1).split("").slice(1..(num3-2).toString(num1).length).toMutableList(); for (i in(arr.indices)){arr[i]=arr[i].toInt(16).toString();arr[i]+="·$num1"+sup[arr.size-1-i]}; b[3]=arr.joinToString(separator=" + ")
        }
        2->{b[1]=(ans.toInt()+1).toString();b[2]="${values[1].toInt(values[2].toInt()+1)}";b[3]="${values[1].toInt(values[2].toInt()+2)}";}
        3->{}
        5->{}
        6->{}
    }
    shuffle(b)
    return arrayOf(b[0],b[1],b[2],b[3],"${b.indexOf(ans)}")
}