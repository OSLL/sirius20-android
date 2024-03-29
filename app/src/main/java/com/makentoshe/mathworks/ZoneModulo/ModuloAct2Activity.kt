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
import com.makentoshe.mathworks.hideKeyboard
import kotlinx.android.synthetic.main.layout_act_tasks.*

class ModuloAct2Activity : AppCompatActivity() {
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
        val taskTypes=arrayOf(0,1,2,0,1,2,0,1,2)
        val taskQuantity=arrayOf(0,2,3,0,3,2,0,3,2)
        val taskNames=arrayOf("act_modulo_2_descr_1","act_modulo_2_task_1","act_modulo_2_task_2","act_modulo_2_descr_2","act_modulo_2_task_3","act_modulo_2_task_4","act_modulo_2_descr_3","act_modulo_2_task_5","act_modulo_2_task_6")
        descrText.text=getText(resources.getIdentifier(taskNames[0], "string", packageName))
        var variants= IntArray(5)
        var nums=IntArray(4)
        var choice: Int
        var a: String
        var aint=0
        taskText.visibility = View.GONE; descrText.visibility= View.VISIBLE
        radioGroupTask.visibility = View.GONE; editTextTask.visibility= View.GONE
        editTextTask.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                a=editTextTask.text.toString().trim()
                if (a.toIntOrNull()!=null) aint=a.toInt()
            }

        })
        continueButtonTask.setOnClickListener {
            if (taskTypes[i]!=0) {
                if (taskTypes[i]==1){
                    if (radioButtonTask1.isChecked) choice=0
                    else if (radioButtonTask2.isChecked) choice=1
                    else if (radioButtonTask3.isChecked) choice=2
                    else if (radioButtonTask4.isChecked) choice=3
                    else choice=editTextTask.toString().toIntOrNull()?:-1
                    if (choice==variants[4]) score++
                }
                if (taskTypes[i]==2){
                    if (aint==nums[0]) score++
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
                intt.putExtra("act", 1)
                if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusModuloAct2",0)<(score.toDouble()/max.toDouble()*100.0).toInt()) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusModuloAct2",(score.toDouble()/max.toDouble()*100.0).toInt()).apply()
                if ((score.toDouble()/max.toDouble()*100.0).toInt()>=50 && PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusModuloAct3",-1)<0) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusModuloAct3",0).apply()
                startActivity(intt)
            }
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) nums = correctNumberMakerForModuloAct2(i)
                variants = answerMakerForModuloAct2(nums)
                when (taskTypes[i]) {
                    0 -> {
                        radioGroupTask.visibility = View.GONE; editTextTask.visibility = View.GONE; descrText.visibility = View.VISIBLE; taskText.visibility = View.GONE
                    }
                    2 -> {
                        radioGroupTask.visibility = View.GONE; editTextTask.visibility = View.VISIBLE; descrText.visibility = View.GONE; taskText.visibility = View.VISIBLE
                    }
                    1 -> {
                        radioGroupTask.visibility = View.VISIBLE; editTextTask.visibility = View.GONE; descrText.visibility = View.GONE; taskText.visibility = View.VISIBLE
                        radioButtonTask1.text = variants[0].toString()
                        radioButtonTask2.text = variants[1].toString()
                        radioButtonTask3.text = variants[2].toString()
                        radioButtonTask4.text = variants[3].toString()
                    }
                }
                if (step <= max) progressBarTaskTrue.progress = ((score.toDouble() / max.toDouble()) * 100.0).toInt()
                if (step <= max) progressBarTask.progress = ((step.toDouble() / max.toDouble()) * 100.0).toInt()
                val name : String
                descrText.text = getText((resources.getIdentifier(taskNames[i], "string", packageName)))
                if (taskQuantity[i] == 2) {
                    name = getString(resources.getIdentifier(taskNames[i], "string", packageName), nums[1], nums[2])
                } else if (taskQuantity[i] == 3) {
                    name = getString(resources.getIdentifier(taskNames[i], "string", packageName), nums[1], nums[2], nums[3])
                } else {
                    name = getString(resources.getIdentifier(taskNames[i], "string", packageName))
                }
                taskText.text = name
            }
        }
    }
}
fun GCD (A: Int, B: Int): Int {
    var a=A; var b=B; var c: Int
    if (b>a) {a+=b; b=a-b; a-=b}
    while(a>0 && b>0){c=a; a=b; b=c%b;}
    return a
}
fun LCM (A: Int, B: Int): Int {
    return A*B/GCD(A,B)
}
fun correctNumberMakerForModuloAct2(i: Int): IntArray {
    var num1:Int; var num2:Int; var num3=0; var ans:Int; val hid:Int
    when (i) {
        1->{
            do {num1=(20..120).random();num2=(20..120).random()} while (num1==num2 || GCD (num1,num2)<10); ans= GCD(num1,num2)
        }
        2->{
            hid=(2..5).random()*10; num1=(2..9).random()*hid; num2=(2..9).random()*hid; while (num3<2 || ((num1+num2+num3)/GCD(GCD(num1,num2),num3)%2==0)) {num3=(2..9).random()*hid}; ans=(num1+num2+num3)/GCD(GCD(num1,num2),num3)
        }
        4->{
            do {num1=(4..7).random()*25; num2=(2..9).random()*25; num3=(3..8).random()*25;} while ((num1==num2) or (num2==num3) or (num1==num3)); ans=LCM(LCM(num1,num2),num3)
        }
        5->{
            num2=(5..9).random()*10; num1=num2+(1..4).random()*10; ans=LCM(num1,num2)
        }
        7->{
            num1=(2..15).random(); do num2=(2..15).random() while (num2==num1); do num3=(2..15).random() while (num2==num3 || num1==num3); ans=LCM(LCM(num1,num2),num3)*GCD(GCD(num1,num2),num3)
        }
        8->{
            num1=(10..100).random(); num2=(11..100).random(); ans=num1+1; while(GCD(num2,ans)>1 || ans<=num1) {ans++}
        }
        else -> {num1=0; num2=0; ans=0}
    }
    return intArrayOf(ans,num1,num2,num3)
}
fun answerMakerForModuloAct2 (a: IntArray): IntArray {
    val ans=a[0]
    val b = mutableListOf<Int>(ans,0,0,0)
    do{b[1]=b[0]+(-25..25).random()}while(b[1]==b[0]||b[1]<=0);do{b[2]=b[0]+(-25..25).random()}while(b[2]==b[0]||b[2]==b[1]||b[2]<=0);do{b[3]=b[0]+(-25..25).random()}while(b[3]==b[0]||b[3]==b[1]||b[3]==b[2]||b[3]<=0)
    b.shuffle()
    val i_corr=b.indexOf(ans)
    return intArrayOf(b[0],b[1],b[2],b[3],i_corr)
}