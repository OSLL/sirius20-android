package com.makentoshe.mathworks.ZoneCombine


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import com.makentoshe.mathworks.hideKeyboard
import kotlinx.android.synthetic.main.layout_act_tasks.*
import java.util.Collections.max
import java.util.Collections.shuffle
class CombineAct1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headSetup.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
        var step =0
        var score =0
        val max =5
        var i=0
        val taskTypes=arrayOf(0,1,1,0,2,0,2,2)
        val taskQuantity=arrayOf(0,2,3,0,2,0,3,3)
        val taskNames=arrayOf("act_combine_1_descr_1","act_combine_1_task_1","act_combine_1_task_2","act_combine_1_descr_2","act_combine_1_task_3","act_combine_1_descr_3","act_combine_1_task_4","act_combine_1_task_5")
        descrText.text=getText(resources.getIdentifier(taskNames[0], "string", packageName))
        var values=arrayOf("","","","","")
        var variants=arrayOf("","","","","")
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
                intt.putExtra("zone", "combine")
                intt.putExtra("act", 0)
                if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusCombineAct1",0)<(score.toDouble()/max.toDouble()*100.0).toInt()) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusCombineAct1",(score.toDouble()/max.toDouble()*100.0).toInt()).apply()
                startActivity(intt)
            }
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) values = valueMakerForCombineAct1(i)
                variants = answerMakerForCombineAct1(values,i)
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
                val name : String
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
fun valueMakerForCombineAct1(i: Int): Array<String>{
    var ans="";var n1=""; var n2=""; var n3=""; var num1=0; var num2=0; var arr1: IntArray; var arr2: IntArray; var set1: Set<Int>; var set2: Set<Int>; var passable="0";
    when(i){
        1->{num1=(0..1).random();num2=((8..9)+(11..16)).random(); n2="$num2";n1=when(num1){0->"чётных";1->"нечётных";else->""};ans= setDecoratorString((num1..num2-1 step 2).toList().map{it.toString(16)}.toTypedArray());passable="$num1 $n2"}
        2->{
            num1=(0..3).random()
            when (num1){
                0 -> {arr1=IntArray((3..7).random()){ i -> (0..255).random()};arr2=IntArray((3..7).random()){ i -> (0..255).random()}
                    ans="Все множества содержат больше одного элемента."
                }
                1 -> {arr2=intArrayOf((0..255).random());arr1=IntArray((2..5).random()){ i -> (0..255).random()}
                    ans="Только одно множество является единичным (содержит единственный элемент)."
                }
                2 -> {arr1=IntArray((3..7).random()){ i -> (0..255).random()};arr2=intArrayOf()
                    ans="Одно из множеств является пустым."
                }
                else -> {arr1=intArrayOf();arr2=intArrayOf()
                    ans="Оба множества являются пустыми."
                }
            }
            n1= setDecoratorInt(arr1)
            n2= setDecoratorInt(arr2)
        }
        4->{num1=(-10..10).random();num2=(num1+1..100).random(); n1="$num1"; n2="$num2"; ans="${num2-num1+1}"}
        6->{set1=IntArray((3..7).random()){(0..5).random()}.toSet();set2=IntArray((3..7).random()){(0..5).random()}.toSet()
            n2= setDecoratorInt(set1.toIntArray())
            n3= setDecoratorInt(set2.toIntArray())
            when((0..3).random()){
                0 -> {n1="A ⋂ B";ans="${(set1 intersect set2).toIntArray().size}"}
                1 -> {n1="A ∪ B";ans="${(set1 union set2).toIntArray().size}"}
                2 -> {n1="A \\ B";if((set1 subtract set2).toIntArray().isNotEmpty())ans="${(set1 subtract set2).toIntArray().size}" else ans="0"}
                3 -> {n1="B \\ A";if((set2 subtract set1).toIntArray().isNotEmpty())ans="${(set2 subtract set1).toIntArray().size}" else ans="0"}
            }
        }
        7->{set1=IntArray((3..7).random()){(0..5).random()}.toSet();set2=IntArray((3..7).random()){(0..5).random()}.toSet()
            n2= setDecoratorInt(set1.toIntArray())
            n3= setDecoratorInt(set2.toIntArray())
            ans="-1"
            when((0..3).random()){
                0 -> {n1="A ⋂ B";ans="${max((set1 intersect set2).toMutableList())}"}
                1 -> {n1="A ∪ B";ans="${max((set1 union set2).toMutableList())}"}
                2 -> {n1="A \\ B";ans="${max((set1 subtract set2).toMutableList())}"}
                3 -> {n1="B \\ A";ans="${max((set2 subtract set1).toMutableList())}"}
            }}
        else->{ans=""}
    }
    return arrayOf(ans,n1,n2,n3,"$passable")
}
fun answerMakerForCombineAct1(values: Array<String>, i: Int): Array<String> {
    var ans=values[0]
    var b= mutableListOf("","","","")
    b[0]=ans
    when(i){
        1->{when(values[4].split(" ")[0].toInt()){
            0 -> {b[1]= setDecoratorString((1 until (values[4].split(" ")[1].toInt()) step 2).toList().map{it.toString(18)}.toTypedArray())
                b[2]= setDecoratorString((0 until (values[4].split(" ")[1].toInt()+2) step 2).toList().map{it.toString(18)}.toTypedArray())
                b[3]= setDecoratorString((0 until (values[4].split(" ")[1].toInt()-2) step 2).toList().map{it.toString(18)}.toTypedArray())
            }
            1 -> {b[1]= setDecoratorString((0 until (values[4].split(" ")[1].toInt()) step 2).toList().map{it.toString(18)}.toTypedArray())
                b[2]= setDecoratorString((1 until (values[4].split(" ")[1].toInt()+2) step 2).toList().map{it.toString(18)}.toTypedArray())
                b[3]= setDecoratorString((1 until (values[4].split(" ")[1].toInt()-2) step 2).toList().map{it.toString(18)}.toTypedArray())
            }
        }}
        2->{
            b[0]="Все множества содержат больше одного элемента."
            b[1]="Только одно множество является единичным (содержит единственный элемент)."
            b[2]="Одно из множеств является пустым."
            b[3]="Оба множества являются пустыми."
        }
        4->{}
        6->{}
        7->{}
    }
    shuffle(b)
    val p="${b.indexOf(ans)}"
    return arrayOf(b[0],b[1],b[2],b[3],p)
}
fun setDecoratorInt(set: IntArray): String {
    return "{"+set.joinToString(separator=", ")+"}"
}
fun setDecoratorString(set: Array<String>): String {
    return "{"+set.joinToString(separator=", ")+"}"
}
