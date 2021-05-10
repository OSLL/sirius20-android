package com.makentoshe.mathworks.ZoneComplex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.preference.PreferenceManager
import com.jjoe64.graphview.GraphView
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import com.makentoshe.mathworks.hideKeyboard
import kotlinx.android.synthetic.main.layout_act_tasks.*

class ComplexAct2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headSetup.text = intent.getStringExtra("zone")
        subheadTask.text = intent.getStringExtra("act")
        mathview.visibility = View.VISIBLE
        mathview.fontSize = 40.0F
        mathview.textColor = descrText.currentTextColor

        val formulas = mapOf(
            0 to "\\\\(1)a=c;b=d\\Leftrightarrow a+bi=c+di\\\\(2)(a+bi)+(c+di)=\\\\=(a+c)+(b+d)i\\\\(3)(a+bi)-(c+di)=\\\\=(a-c)+(b-d)i\\\\(4)(a+bi)\\cdot(c+di)=\\\\=(ac-bd)+(ad+bc)i\\\\(5)\\frac{1}{a+bi}=\\frac{a-bi}{a^2+b^2}",
            3 to "\\\\(1)z^n=r^n(\\cos n\\varphi+i\\sin n\\varphi)\\\\(2)\\sqrt[n]{z}=r^\\frac{1}{n}(\\cos\\frac{\\varphi+2\\pi k}{n}+i\\sin\\frac{\\varphi+2\\pi k}{n}),\\\\k\\in\\mathbb{Z}"
        )
        formulas[0].toString().also { mathview.latex = it }
        var step = 0
        var score = 0
        val max = 4
        var i = 0
        val taskTypes = arrayOf(0, 1, 2, 0, 1, 2)
        val taskNames = arrayOf("act_complex_2_descr_1", "act_complex_2_task_1", "act_complex_2_task_2", "act_complex_2_descr_2", "act_complex_2_task_3", "act_complex_2_task_4")
        descrText.text = getText(resources.getIdentifier(taskNames[0], "string", packageName))
        var values=Array(5){""}
        var variants = Array(5){""}
        var choice: String
        var a = ""
        taskText.visibility = View.GONE; descrText.visibility = View.VISIBLE
        radioGroupTask.visibility = View.GONE; editTextTask.visibility = View.GONE
        editTextTask.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                a = editTextTask.text.toString().trim()
            }
        })
        continueButtonTask.setOnClickListener {
            taskImage.visibility = View.GONE
            if (taskTypes[i] != 0) {
                if (taskTypes[i] == 1) {
                    choice = when {
                        radioButtonTask1.isChecked -> "0"
                        radioButtonTask2.isChecked -> "1"
                        radioButtonTask3.isChecked -> "2"
                        radioButtonTask4.isChecked -> "3"
                        else -> "4"
                    }
                    if (choice == variants[4]) score++
                }
                if (taskTypes[i] == 2) {
                    if (a == values[0]) score++
                }
                step++
            }
            i++
            if(i<taskTypes.size) if(taskTypes[i]!=2) hideKeyboard(this)
            values= valueMakerForComplexAct2(i)
            variants= answerMakerForComplexAct2(values,i)
            mathview.latex=when(i){
                1-> with(values[2].split(' ')){"("+complex(this[0].toInt(),this[1].toInt())+")"+values[1]+"("+complex(this[2].toInt(),this[3].toInt())+")"}.toString()
                2-> with(values[1].split(' ')){"z_1\\cdot z_2\\\\z_1="+complex(this[0].toInt(),this[1].toInt())+"\\\\z_2="+complex(this[2].toInt(),this[3].toInt())}
                3-> formulas[3].toString()
                4->when(values[0]){
                    complex(3,4)->"(2+i)^2"
                    complex(1,0)->"(-\\frac{1}{2}+\\frac{\\sqrt3}{2}i)^3"
                    complex(16,0)->"(1+i)^8"
                    complex(0,27)->"(\\frac{3\\sqrt3}{2}+\\frac{3}{2}i)^3"
                    complex(-3,4)->"(1+2i)^2"
                    else->"(1+i)^3"
                }
                5->"\\sqrt[${values[0]}]{${values[1]}}"
                else->""
            }
            if (i == taskTypes.size) {
                val intt = Intent(this, ActResult::class.java)
                intt.putExtra("score", score)
                intt.putExtra("max", max)
                intt.putExtra("zone", "complex")
                intt.putExtra("act", 1)
                if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusComplexAct2", 0) < (score.toDouble() / max.toDouble() * 100.0).toInt()) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusComplexAct2", (score.toDouble() / max.toDouble() * 100.0).toInt()).apply()
                startActivity(intt)
            }
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            if (i < taskTypes.size) {
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
                taskText.text=getString(resources.getIdentifier(taskNames[i], "string", packageName))
            }
        }
    }
}
fun valueMakerForComplexAct2(i: Int): Array<String>{
    var ans=""; var n1=""; var n2=""; var n3=""; var passable="";
    var num1: Int; var num2: Int; val num3: Int; var num4: Int
    when(i) {
        1 -> {n1="${"-+"[(0..1).random()]}"
            num1=((-100..-1)+(1..100)).random()
            num2=((-100..-1)+(1..100)).random()
            num3=((-100..-1)+(1..100)).random()
            num4=((-100..-1)+(1..100)).random()
            n2="$num1 $num2 $num3 $num4"
            ans=if(n1=="+") complex(num1+num3,num2+num4) else complex(num1-num3,num2-num4)
        }
        2 -> {num1=(0..5).random()
            num2=(1..5).random()
            num3=(0..5).random()
            num4=(1..5).random()
            n1="$num1 $num2 $num3 $num4"
            ans="${num1*num3-num2*num4}"
        }
        4 -> {ans=when((0..5).random()){
            0->complex(3,4)
            1->complex(1,0)
            2->complex(16,0)
            3->complex(0,27)
            4->complex(-3,4)
            else->complex(-2,2)
        }}
        5 -> {do {num1=(-100..100).random();num2=(-100..100).random()}while(num1==0&&num2==0);ans="${(2..100).random()}";n1=complex(num1,num2)}
    }
    Log.d("ok",ans)
    return arrayOf(ans,n1,n2,n3,passable)
}
fun answerMakerForComplexAct2(values: Array<String>, i: Int): Array<String> {
    val ans=values[0]
    val b= MutableList(4){""}
    val pas=values[4]
    b[0]=ans
    when(i){
        1->{b[1]= with(values[2].split(" ")){complex(this[0].toInt(),this[1].toInt())};b[2]= with(values[2].split(" ")){complex(this[2].toInt(),this[3].toInt())};with(values[2].split(" ")){b[3]=if(values[1]=="+")complex(this[0].toInt()-this[2].toInt(),this[1].toInt()-this[3].toInt())else complex(this[0].toInt()+this[2].toInt(),this[1].toInt()+this[3].toInt())}}
        2->{}
        4->when(ans){
            complex(3,4)->{b[1]=complex(4,3);b[2]=complex(10,5);b[3]=complex(3,-4)}
            complex(1,0)->{b[1]=complex(0,1);b[2]=complex(1,-1);b[3]=complex(-1,-1)}
            complex(16,0)->{b[1]=complex(0,4);b[2]=complex(-8,0);b[3]=complex(2,0)}
            complex(0,27)->{b[1]=complex(18,9);b[2]=complex(0,3);b[3]=complex(3,0)}
            complex(-3,4)->{b[1]=complex(-4,3);b[2]=complex(2,4);b[3]=complex(3,4)}
            complex(-2,2)->{b[1]=complex(-1,1);b[2]=complex(-3,3);b[3]=complex(-4,4)}
        }
        5->{}
    }
    b.shuffle()
    return arrayOf(b[0],b[1],b[2],b[3],"${b.indexOf(ans)}")
}