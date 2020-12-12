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
import java.lang.Math.abs

class IntegerAct3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headTask.text=getString(R.string.zone_integer_name)
        subheadTask.text=getString(R.string.act_integer_3_name)
        var taskNames=arrayOf("act_integer_3_descr_1","act_integer_3_task_1","act_integer_3_task_2","act_integer_3_task_3","act_integer_3_task_4","act_integer_3_task_5","act_integer_3_task_6")
        var taskTypes=arrayOf(0,1,1,2,2,2,1)
        var taskQuantity=arrayOf(0,2,2,3,2,2,2)
        descrText.text=getText(resources.getIdentifier(taskNames[0], "string", packageName))
        var step =0
        var score =0
        var max =6
        progressBarTask.max=100
        progressBarTaskTrue.max=100
        var i=0
        var variants= IntArray(5)
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
                Log.d("Act3","Input was modified: $a $aint")
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
                    Log.d("Act3","Score is now $score")
                }
                if (taskTypes[i]==2){
                    Log.d("Act3","Text task, answer is ${nums[0]}")
                    if (aint==nums[0]) score++
                    Log.d("Act3","Score is now $score")
                }
                step++
                Log.d("Act3","Proceeding to navigation_forth task, step=${step}, score=${score}")
            }
            i++
            radioGroupTask.clearCheck()
            if (i==taskTypes.size) {
                Log.d("Act3", "The act is over, launching to the start menu")
                val intt = Intent(this, ActResult::class.java)
                intt.putExtra("score", score)
                intt.putExtra("max", max)
                intt.putExtra("zone", "modulo")
                intt.putExtra("act", 2)
                startActivity(intt)
            }
            Log.d("Act3","Strings updated")
            Log.d("Act3","Now showing frame $i")
            editTextTask.setText("")
            Log.d("Act3","Variants unchecked, input cleaned")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) nums = correctNumberMakerForIntegerAct3(i)
                variants = radioButtonTaskMakerForIntegerAct3(nums)
                Log.d("Act3", "Answer: ${nums[0]}")
                Log.d("Act3", "Variants deployed")
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
                } else {
                    name = getString(resources.getIdentifier(taskNames[i], "string", packageName))
                }
                taskText.text = name
            }
        }
    }
}
fun correctNumberMakerForIntegerAct3(i: Int): IntArray {
    var num1=0; var num2=0; var num3=0; var ans:Int; var hid:Int;
    when (i) {
        1->{
            num1=(3..20).random(); do num2=(10..50).random()*2 while (num2%num1==0 || num2<2*num1); ans=num2/num1
        }
        2->{
            num1=(4..10).random()*5; do num2=(40..150).random() while(num2%num1==0 || num2<2*num1); ans=num2%num1
        }
        3->{
            num1=(4..10).random()*50; do num2=(5..25).random()*20 while (num2%num1==0 || num1%num2==0); do num3=(5..7).random() while(num3*num1%num2==0); ans=(num3*num1)/num2+1
        }
        4->{
            num1=(5..15).random()*20; do num2=(2..5).random()*10 while (num1%num2==0); if ((num1/num2)%2==0) ans=num1/num2-1 else ans=num1/num2;
        }
        5->{
            num1=(5..15).random()*20; do num2=(2..5).random()*10 while (num1%num2==0); if ((num1/num2)%2==0) num3=num1/num2-1 else num3=num1/num2; ans=num1-num3*num2;
        }
        6->{
            num1=(6..15).random(); do{num2=(4..20).random()*5} while(num2%num1==0); ans=num2%num1+num2/num1;
        }
        else -> {num1=0; num2=0; ans=0}
    }
    return intArrayOf(ans,num1,num2,num3)
    Log.d("Act1","Correct numbers created")
}
fun radioButtonTaskMakerForIntegerAct3 (a: IntArray): IntArray {
    var ans=a[0]
    var b: MutableList<Int>
    do {b= mutableListOf(0,(-25..25).random(),(-25..25).random(),(-25..25).random())} while (b[1]==0 || b[2]==0 || b[3]==0 || b[1]==b[2] || b[1]==b[3] || b[2]==b[3])
    var i_corr=0
    b.shuffle()
    for (i in 0..3) {if (b[i]==0) {i_corr=i}; b[i]+=ans; b[i]= abs(b[i])
    }
    for (i in 0..3) {if (i!=i_corr && b[i]==ans)b[i]+=1}
    var aa= intArrayOf(b[0],b[1],b[2],b[3],i_corr)
    Log.d("Act1","Answer array returned: ${aa[0]} ${aa[1]} ${aa[2]} ${aa[3]} ${aa[4]}")
    return aa
}