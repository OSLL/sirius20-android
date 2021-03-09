package com.makentoshe.mathworks.ZoneCombine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.preference.PreferenceManager
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.MainActivity
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.layout_act_tasks.*
import java.util.Collections.shuffle

class CombineBossActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headSetup.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
        headSetup.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
        var lives=PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("lives",3)
        var step=0
        var score=0
        val max=5
        var i=0
        val taskTypes=arrayOf(0,1,2,1,2,2)
        val taskQuantity=arrayOf(0,1,2,2,3,3)
        val taskNames=arrayOf("boss_descr","act_combine_boss_task_1","act_combine_boss_task_2","act_combine_boss_task_3","act_combine_boss_task_4","act_combine_boss_task_5")
        descrText.text=getString(R.string.boss_descr,headSetup.text)
        var values=Array(5){""}
        var variants=Array(5){""}
        var choice: String
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
                    if (radioButtonTask1.isChecked) choice="0"
                    else if (radioButtonTask2.isChecked) choice="1"
                    else if (radioButtonTask3.isChecked) choice="2"
                    else if (radioButtonTask4.isChecked) choice="3"
                    else choice="4"
                    if (choice==variants[4]) score++ else  {lives--;livesIndicator.text=getText(R.string.marker_heart).repeat(lives);PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("lives",lives).apply()}

                }
                if (taskTypes[i]==2){
                    if (a== values[0]) score++ else  {lives--;livesIndicator.text=getText(R.string.marker_heart).repeat(lives);PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("lives",lives).apply()}
                }
                if (lives==0){finish();startActivity(Intent(this, MainActivity::class.java))}
                step++
            }
            i++
            if (i==taskTypes.size) {
                val intt = Intent(this, ActResult::class.java)
                intt.putExtra("score", score)
                intt.putExtra("max", max)
                intt.putExtra("zone", "combine")
                intt.putExtra("act", 4)
                startActivity(intt)
            }
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) values = valueMakerForCombineBoss(i)
                variants = answerMakerForCombineBoss(values,i)
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
fun valueMakerForCombineBoss(i: Int): Array<String>{
    var ans=""; var n1=""; var n2=""; var n3=""; var passable="";val num1: Int; val num2: Int; val num3: Int
    when(i) {
        1 -> { num1=(5..10).random(); num3=(2..6).random(); num2=num1+(5..10).random()*num3; n1=setDecoratorInt((num1..num2 step num3).toMutableSet().toIntArray()); passable="$num1 $num2 $num3";ans="{ x = ${num1%num3} (mod $num3) ⋀ $num1≤x≤$num2 ⋀ x∈ℤ}"
        }
        2 -> { num1=(5..10).random()+(1..2).random()*10; num2=if (num1%5==0) 3 else 2; n1="$num1"; n2="$num2"; ans="${factorialDivider(num1,num1-num2)/factorial(num2)}"
        }
        3 -> { num2=(2..4).random(); n2="$num2"; num3=(num2+2..7).random(); ans="$num3"; num1= factorialDivider(num3,num3-num2); n1="$num1"; passable=n1+n2
        }
        4 -> { num1=(1..3).random(); num2=(1..3).random(); num3=(1..3).random(); n1="$num1"; n2="$num2"; n3="$num3"; ans="${ factorial(num1 + num2 + num3) / factorial(num1) / factorial(num2) / factorial(num3) }"
        }
        5 -> { num1=(3..7).random(); num2=(0..15).random(); num3=(3..10).random()*num1+num2; n1="$num1";  n2="$num2"; n3="$num3"; ans=if (num2%num1==0) "${(num3-num2)/num1+1}" else "${(num3-num2)/num1}"
        }
    }
    return arrayOf(ans,n1,n2,n3,passable)
}
fun answerMakerForCombineBoss(values: Array<String>, i: Int): Array<String> {
    val ans=values[0]
    val b= MutableList(4){""}
    val pas=values[4].split(" ")
    b[0]=ans
    when(i){
        1->{b[1]="{ x = ${(pas[0].toInt()-1)%pas[2].toInt()} (mod ${pas[2]}) ⋀ ${pas[0]}≤x≤${pas[1]} ⋀ x∈ℤ}"
            b[2]="{ x = ${(pas[0].toInt())%pas[2].toInt()} (mod ${pas[2]}) ⋀ ${pas[0]}≤x≤${pas[1].toInt()+pas[2].toInt()} ⋀ x∈ℤ}"
            b[3]="{ x = ${(pas[0].toInt())%pas[2].toInt()} (mod ${pas[2]}) ⋀ ${pas[0].toInt()+pas[2].toInt()}≤x≤${pas[1]} ⋀ x∈ℤ}"
        }
        2->{}
        3->{b[1]="${ans.toInt()+((-3..-1)+(1..4)).random()}";do{b[2]="${ans.toInt()+((-3..-1)+(1..4)).random()}"}while(b[2]==b[1]);do{b[3]="${ans.toInt()+((-3..-1)+(1..4)).random()}"}while(b[3]==b[1]||b[3]==b[2])}
        4->{}
        5->{}
    }
    shuffle(b)
    val p=b.indexOf(ans)
    return arrayOf(b[0],b[1],b[2],b[3],"$p")
}
