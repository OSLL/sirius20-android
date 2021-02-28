package com.makentoshe.mathworks.ZoneInteger

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.layout_act_tasks.*


class IntegerAct1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headSetup.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
        val lives: Int
        lives=intent.getIntExtra("lives",3)
        var score=0
        val taskNames=arrayOf("act_integer_1_descr_1","act_integer_1_task_1","act_integer_1_task_2","act_integer_1_task_3","act_integer_1_descr_2","act_integer_1_task_4","act_integer_1_task_5","act_integer_1_task_6")
        val taskTypes=arrayOf(0,1,1,2,0,1,1,2)
        val taskQuantity=arrayOf(0,2,2,2,0,3,2,3)
        radioGroupTask.visibility = View.GONE; editTextTask.visibility= View.GONE
        var i=0; var step=0
        descrText.text=getText(resources.getIdentifier(taskNames[0], "string", packageName))
        taskText.visibility = View.GONE; descrText.visibility= View.VISIBLE
        val max=6
        var variants=IntArray(5)
        var nums=IntArray(4)
        var choice: Int
        var a: String
        var aint=0

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
        continueButtonTask.setOnClickListener{
            if (taskTypes[i]!=0) {
                if (taskTypes[i]==1){
                    choice = when {
                        radioButtonTask1.isChecked -> 0
                        radioButtonTask2.isChecked -> 1
                        radioButtonTask3.isChecked -> 2
                        radioButtonTask4.isChecked -> 3
                        else -> editTextTask.toString().toIntOrNull()?:-1
                    }
                    if (choice==variants[4]) score++
                }
                if (taskTypes[i]==2){
                    if (aint==nums[0]) score++
                }
                step++
            }
            i++
            if (i==taskTypes.size) {
                val intt= Intent(this, ActResult::class.java)
                intt.putExtra("score",score)
                intt.putExtra("max",max)
                intt.putExtra("lives",lives)
                intt.putExtra("zone", "integer")
                intt.putExtra("act", 0)
                startActivity(intt)
            }
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) nums = correctNumberMakerForIntegerAct1(i)
                variants = answerMakerForIntegerAct1(nums)
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
                val name: String
                descrText.text=getText(resources.getIdentifier(taskNames[i], "string", packageName))
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

fun correctNumberMakerForIntegerAct1 (i: Int): IntArray {
    var num1:Int; var num2:Int; var num3=0; val ans:Int
    when (i) {
        1->{
            num1=99; num2=99; while (num1%10+num2%10>9) {num1= (10..49).random(); num2=(10..49).random()}; ans=num1+num2
        }
        2->{
            num1=100; num2=99; while (num2%10-num1%10<0||num2-num1<=0) {num1= (10..99).random(); num2=(10..99).random()}; ans=num2-num1
        }
        3->{
            num1=99; num2=99; while (2*(num1%10)+num2%10>9){num1=(20..39).random(); num2=(5..15).random()}; ans=num2+2*num1
        }
        5->{
            num1=90; num2=90; num3=90; while (num1%10+num2%10+num3%10<10){num1=(20..49).random();num2=(20..49).random();num3=(20..49).random()};ans=num1+num2+num3
        }
        6->{
            num1=(20..49).random(); num2=(10..num1-1).random(); ans=(-num2+2*num1)
        }
        7->{
            ans=(200..299).random(); num1=(10..199).random(); num2=num1+(-19..19).random(); num3=ans-num2+num1
        }
        else -> {num1=0; num2=0; ans=0}
    }
    return intArrayOf(ans,num1,num2,num3)
}
fun answerMakerForIntegerAct1 (a: IntArray): IntArray {
    val ans=a[0]
    val b = mutableListOf<Int>(ans,0,0,0)
    do{b[1]=b[0]+(-25..25).random()}while(b[1]==b[0]||b[1]<=0);do{b[2]=b[0]+(-25..25).random()}while(b[2]==b[0]||b[2]==b[1]||b[2]<=0);do{b[3]=b[0]+(-25..25).random()}while(b[3]==b[0]||b[3]==b[1]||b[3]==b[2]||b[3]<=0)
    b.shuffle()
    val i_corr=b.indexOf(ans)
    return intArrayOf(b[0],b[1],b[2],b[3],i_corr)
}