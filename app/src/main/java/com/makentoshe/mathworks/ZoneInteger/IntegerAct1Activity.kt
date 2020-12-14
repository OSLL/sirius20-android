package com.makentoshe.mathworks.ZoneInteger

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.layout_act_tasks.*


class IntegerAct1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headTask.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
        var lives: Int
        lives=intent.getIntExtra("lives",3)
        var score=0
        var taskNames=arrayOf("act_integer_1_descr_1","act_integer_1_task_1","act_integer_1_task_2","act_integer_1_task_3","act_integer_1_descr_2","act_integer_1_task_4","act_integer_1_task_5","act_integer_1_task_6")
        var taskTypes=arrayOf(0,1,1,2,0,1,1,2)
        var taskQuantity=arrayOf(0,2,2,2,0,3,2,3)
        Log.d("Act1","Imported")
        radioGroupTask.visibility = View.GONE; editTextTask.visibility= View.GONE
        var i=0; var step=0
        descrText.text=getText(resources.getIdentifier(taskNames[0], "string", packageName))
        taskText.visibility = View.GONE; descrText.visibility= View.VISIBLE
        var max=6
        progressBarTask.max=100
        progressBarTaskTrue.max=100
        var variants=IntArray(5)
        var nums=IntArray(4)
        var choice=0
        var a=""
        var aint=0

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
                    if (choice==variants[4]) score++
                    Log.d("Act1","Score is now $score")
                }
                if (taskTypes[i]==2){
                    Log.d("Act1","Text task, answer is ${nums[0]}")
                    if (aint==nums[0]) score++
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
                intt.putExtra("act", 0)
                startActivity(intt)
            }

            Log.d("Act1","Strings updated")
            Log.d("Act1","Now showing frame $i")
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            Log.d("Act1","Variants unchecked, input cleaned")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) nums = correctNumberMakerForArithmAct1(i)
                variants = radioButtonTaskMakerForArithmAct1(nums)
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

fun correctNumberMakerForArithmAct1 (i: Int): IntArray {
    var num1:Int; var num2:Int; var num3=0; var ans:Int;
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
            num1=0; num2=0; ans=(200..299).random(); num1=(10..199).random(); num2=num1+(-19..19).random(); num3=ans-num2+num1
        }
        else -> {num1=0; num2=0; ans=0}
    }
    return intArrayOf(ans,num1,num2,num3)
    Log.d("Act1","Correct numbers created")
}
fun radioButtonTaskMakerForArithmAct1 (a: IntArray): IntArray {
    var ans=a[0]
    var b = mutableListOf<Int>(0,(-ans..-1).random(),(1..10).random(),(-ans/2..5).random())
    var i_corr=0
    if (b[3]==0) b[3]=1
    b.shuffle()
    for (i in 0..3) {if (b[i]==0) {i_corr=i}; b[i]+=ans; }
    var aa= intArrayOf(b[0],b[1],b[2],b[3],i_corr)
    Log.d("Act1","Answer array returned: ${aa[0]} ${aa[1]} ${aa[2]} ${aa[3]} ${aa[4]}")
    return aa
}
