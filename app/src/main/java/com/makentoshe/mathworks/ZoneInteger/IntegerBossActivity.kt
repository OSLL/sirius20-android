package com.makentoshe.mathworks.ZoneInteger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.layout_act_tasks.*

class IntegerBossActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headTask.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
        var score=0
        var lives:Int
        lives=intent.getIntExtra("lives",3)
        var taskNames=arrayOf("act_integer_boss_theory","act_integer_boss_task_1","act_integer_boss_task_2","act_integer_boss_task_3","act_integer_boss_task_4","act_integer_boss_task_5")
        var taskTypes=arrayOf(0,1,2,2,1,2)
        var taskQuantity=arrayOf(0,2,3,2,1,2)
        var i=0; var step=0
        descrText.text=getString(R.string.boss_descr,headTask.text)
        var max=5
        var variants=IntArray(5)
        var nums=IntArray(4)
        var choice=0
        var a=""
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
                Log.d("Act1","Input was modified: $a $aint")
            }

        })

        continueButtonTask.setOnClickListener{
            Log.d("Act1","Button pressed")
            if (taskTypes[i]!=0) {
                if (taskTypes[i]==1){
                    if (radioButtonTask1.isChecked) choice=0
                    else if (radioButtonTask2.isChecked) choice=1
                    else if (radioButtonTask3.isChecked) choice=2
                    else if (radioButtonTask4.isChecked) choice=3
                    else choice=editTextTask.toString().toIntOrNull()?:-1
                    if (choice==variants[4]) score++ else lives--
                    Log.d("Act1","Score is now $score")
                }
                if (taskTypes[i]==2){
                    Log.d("Act1","Text task, answer is ${nums[0]}")
                    if (aint==nums[0]) score++ else lives--
                    Log.d("Act1","Score is now $score")
                }
                step++
                Log.d("Act1","Proceeding to navigation_forth task, step=${step}, score=${score}")
            }
            i++
            if (i==taskTypes.size) {
                Log.d("Act1","The act is over, launching to the start menu")
                val intt= Intent(this, ActResult::class.java)
                intt.putExtra("score",score)
                intt.putExtra("max",max)
                intt.putExtra("lives",lives)
                intt.putExtra("zone", "integer")
                intt.putExtra("act", 4)
                startActivity(intt)
            }

            Log.d("Act1","Strings updated")
            Log.d("Act1","Now showing frame $i")
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            Log.d("Act1","Variants unchecked, input cleaned")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) nums = correctNumberMakerForIntegerBoss(i)
                variants = radioButtonTaskMakerForIntegerBoss(nums,i)
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
                        radioButtonTask1.text = variants[0].toString()
                        radioButtonTask2.text = variants[1].toString()
                        radioButtonTask3.text = variants[2].toString()
                        radioButtonTask4.text = variants[3].toString()
                    }
                }
                Log.d("Act1", "Variants deployed")
                if (step <= max) progressBarTaskTrue.progress = ((score.toDouble() / max.toDouble()) * 100.0).toInt()
                if (step <= max) progressBarTask.progress = ((step.toDouble() / max.toDouble()) * 100.0).toInt()
                var name = ""
                descrText.text = getText(resources.getIdentifier(taskNames[i], "string", packageName))
                if (taskQuantity[i] == 2) {
                    name = getString(resources.getIdentifier(taskNames[i], "string", packageName), nums[1], nums[2])
                } else if (taskQuantity[i] == 3) {
                    name = getString(resources.getIdentifier(taskNames[i], "string", packageName), nums[1], nums[2], nums[3])
                }
                else if (taskQuantity[i] == 1) {
                    name = getString(resources.getIdentifier(taskNames[i], "string", packageName), nums[1])
                }
                else {
                    name = getString(resources.getIdentifier(taskNames[i], "string", packageName))
                }
                taskText.text = name
            }
        }
    }
}
fun correctNumberMakerForIntegerBoss (i: Int): IntArray {
    var num1:Int; var num2=0; var num3=0; var ans:Int;
    when (i) {
        1->{
            num2=(5..9).random(); num1=(5..15).random()*num2;  ans=num1/num2
        }
        2->{
            num1=(20..39).random(); num2=(20..39).random(); num3=(5..9).random(); ans=num1*2+num2*2+num3
        }
        3->{
            num1=(5..9).random(); num2=(11..15).random(); ans=num2*num1
        }
        4->{
            num1=99; while (!isPrime(num1) or !isPrime(num1 + 2)){num1=(11..25).random()}; ans=num1+2
        }
        5->{
            num1=(10..100).random(); num2=(2..num1).random(); ans=num1%num2
        }
        else -> {num1=0; num2=0; ans=0}
    }
    return intArrayOf(ans,num1,num2,num3)
}
fun radioButtonTaskMakerForIntegerBoss(a: IntArray,i:Int): IntArray {
    var ans=a[0]
    var b= mutableListOf<Int>(ans,0,0,0)
    when (i) {
        1 -> {do {b[1]=b[0]+(-15..15).random()}while (b[1]==b[0]||b[1]<=0);do {b[2]=b[0]+(-15..15).random()}while (b[2]==b[1]||b[2]==b[0]||b[2]<=0); do {b[3]=b[0]+(-15..15).random()}while (b[3]==b[2]||b[3]==b[1]||b[3]==b[0]||b[2]<=0)}
        2 -> {b[1]=a[1];b[2]=a[2]+a[1]+a[3];do{b[3]=b[0]+(-15..15).random()}while(b[3]==b[1]||b[3]==b[2]||b[3]==b[0])}
        3 -> {b[1]=a[1]; b[2]=a[2];do{b[3]=b[0]+(-15..15).random()}while(b[3]==b[1]||b[3]==b[2]||b[3]==b[0])}
        4 -> {b[1]=a[1];b[2]=b[0]+(2..23).random();do{b[3]=b[0]+(2..23).random()}while(b[3]==b[2]||b[3]==b[1]||b[3]==b[0])}
        5 -> {b[1]=a[1]/a[2];b[2]=b[0]+(2..23).random();do{b[3]=b[0]+(2..23).random()}while(b[3]==b[2]||b[3]==b[1]||b[3]==b[0])}
        else -> {b= mutableListOf<Int>(ans,0,0,0)}
    }
    b.shuffle()
    var point: Int =0
    for (j in b) if (j==ans) {point=b.indexOf(j)}
    return intArrayOf(b[0],b[1],b[2],b[3],point)
}
fun isPrime(a: Int): Boolean {
    var i: Int; var b= true;
    for (i in (2..a-1)) {if (a%i==0) b=false}
    return b
}