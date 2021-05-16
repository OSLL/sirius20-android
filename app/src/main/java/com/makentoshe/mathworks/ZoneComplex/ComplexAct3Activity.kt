package com.makentoshe.mathworks.ZoneComplex

import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.preference.PreferenceManager
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.PointsGraphSeries
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import com.makentoshe.mathworks.hideKeyboard
import kotlinx.android.synthetic.main.layout_act_tasks_graph.*
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

class ComplexAct3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks_graph)
        headSetup.text = intent.getStringExtra("zone")
        subheadTask.text = intent.getStringExtra("act")
        mathview.visibility = View.GONE
        mathview.fontSize = 40.0F
        mathview.textColor = descrText.currentTextColor
        val graph = findViewById<View>(R.id.graph) as GraphView
        graph.gridLabelRenderer.gridColor = mathview.textColor
        graph.gridLabelRenderer.horizontalLabelsColor = mathview.textColor
        graph.gridLabelRenderer.verticalLabelsColor = mathview.textColor
        graph.run {
            viewport.isYAxisBoundsManual = true
            viewport.setMinY(-5.1)
            viewport.setMaxY(5.1)
            viewport.isXAxisBoundsManual = true
            viewport.setMinX(-5.1)
            viewport.setMaxX(5.1)
            gridLabelRenderer.horizontalAxisTitle="Re(z)"
            gridLabelRenderer.verticalAxisTitle="Im(z)"
            visibility=View.VISIBLE
            addSeries(LineGraphSeries(arrayOf(DataPoint(0.0,0.0), DataPoint(1.0,3.0))).also{it.color=Color.BLUE})
            addSeries(LineGraphSeries(arrayOf(DataPoint(0.0,0.0),DataPoint(2.0,2.0))).also{it.color=Color.BLUE})
            addSeries(LineGraphSeries(arrayOf(DataPoint(0.0,0.0), DataPoint(3.0,5.0))).also{it.color=Color.BLUE})
            addSeries(LineGraphSeries(arrayOf(DataPoint(-4.33,-2.5),DataPoint(0.0,5.0),DataPoint(4.33,-2.5))).also{it.color=mathview.textColor})
            addSeries(LineGraphSeries(arrayOf(DataPoint(-4.33,-2.5),DataPoint(4.33,-2.5))).also{it.color=mathview.textColor})
        }
        var step = 0
        var score = 0
        val max = 6
        var i = 0
        val taskTypes = arrayOf(0, 2,2, 1, 0, 2,1,1)
        val taskNames = arrayOf("act_complex_3_descr_1", "act_complex_3_task_1", "act_complex_3_task_2", "act_complex_3_task_3", "act_complex_3_descr_2", "act_complex_3_task_4", "act_complex_3_task_5", "act_complex_3_task_6")
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
            values= valueMakerForComplexAct3(i)
            variants= answerMakerForComplexAct3(values,i)
            graph.run{
                when(i){
                    1->{visibility=View.GONE}
                    2->{visibility=View.VISIBLE
                        removeAllSeries()
                        when(values[0]){
                            "3"->{addSeries(LineGraphSeries(arrayOf(DataPoint(-4.33,2.5),DataPoint(0.0,-5.0),DataPoint(4.33,2.5))).also{it.color=mathview.textColor})
                                addSeries(LineGraphSeries(arrayOf(DataPoint(-4.33,2.5),DataPoint(4.33,2.5))).also{it.color=mathview.textColor})}
                            "4"->{addSeries(LineGraphSeries(arrayOf(DataPoint(-2.0,-1.0),DataPoint(-1.0,2.0),DataPoint(2.0,1.0))).also{it.color=mathview.textColor})
                                addSeries(LineGraphSeries(arrayOf(DataPoint(-2.0,-1.0),DataPoint(1.0,-2.0),DataPoint(2.0,1.0))).also{it.color=mathview.textColor})}
                            "5"->{addSeries(LineGraphSeries(arrayOf(DataPoint(-4.045,-2.939),DataPoint(-4.045,2.939),DataPoint(1.545,4.755),DataPoint(5.0,0.0))).also{it.color=mathview.textColor})
                                addSeries(LineGraphSeries(arrayOf(DataPoint(-4.045,-2.939),DataPoint(1.545,-4.755),DataPoint(5.0,0.0))).also{it.color=mathview.textColor})}
                            "6"->{addSeries(LineGraphSeries(arrayOf(DataPoint(-2.897,-0.776),DataPoint(-2.121,2.121),DataPoint(0.776,2.897),DataPoint(2.897,0.776))).also{it.color=mathview.textColor})
                                addSeries(LineGraphSeries(arrayOf(DataPoint(-2.897,-0.776),DataPoint(-0.776,-2.897),DataPoint(2.121,-2.121),DataPoint(2.897,0.776))).also{it.color=mathview.textColor})}
                            "7"->{addSeries(LineGraphSeries(arrayOf(DataPoint(-3.9,-0.89),DataPoint(-3.127,2.494),DataPoint(0.0,4.0),DataPoint(3.127,2.494),DataPoint(3.9,-0.89))).also{it.color=mathview.textColor})
                                addSeries(LineGraphSeries(arrayOf(DataPoint(-3.9,-0.89),DataPoint(-1.736,-3.603),DataPoint(1.736,-3.603),DataPoint(3.9,-0.89))).also{it.color=mathview.textColor})}
                        }
                    }
                    3->{removeAllSeries()
                        when(values[0]){
                            "а) да, б) да"->{
                                addSeries(LineGraphSeries(arrayOf(DataPoint(-1.0,0.0),DataPoint(0.0,1.0),DataPoint(1.0,0.0))).also{it.color=mathview.textColor})
                                addSeries(LineGraphSeries(arrayOf(DataPoint(-1.0,0.0),DataPoint(0.0,-1.0),DataPoint(1.0,0.0))).also{it.color=mathview.textColor})
                            }
                            "а) да, б) нет"->{
                                addSeries(LineGraphSeries(arrayOf(DataPoint(-2.0,-3.464),DataPoint(-2.0,3.464),DataPoint(4.0,0.0))).also{it.color=mathview.textColor})
                                addSeries(LineGraphSeries(arrayOf(DataPoint(-2.0,-3.464),DataPoint(4.0,0.0))).also{it.color=mathview.textColor})
                            }
                            "а) нет, б) нет"->{
                                addSeries(LineGraphSeries(arrayOf(DataPoint(-3.535,3.535),DataPoint(-1.294,-4.829),DataPoint(4.829,1.294))).also{it.color=mathview.textColor})
                                addSeries(LineGraphSeries(arrayOf(DataPoint(-3.535,3.535),DataPoint(4.829,1.294))).also{it.color=mathview.textColor})
                            }
                            "а) нет, б) да"->{
                                addSeries(LineGraphSeries(arrayOf(DataPoint(-3.9,-0.89),DataPoint(-3.127,2.494),DataPoint(0.0,4.0),DataPoint(3.127,2.494),DataPoint(3.9,-0.89))).also{it.color=mathview.textColor})
                                addSeries(LineGraphSeries(arrayOf(DataPoint(-3.9,-0.89),DataPoint(-1.736,-3.603),DataPoint(1.736,-3.603),DataPoint(3.9,-0.89))).also{it.color=mathview.textColor})
                            }
                        }
                    }
                    4->{visibility=View.GONE}
                    5->{visibility=View.VISIBLE
                    removeAllSeries()
                        addSeries(LineGraphSeries(when(values[0]){
                            "0"->arrayOf(DataPoint(0.0,0.0),DataPoint(5.0,0.0))
                            "30"->arrayOf(DataPoint(0.0,0.0),DataPoint(4.33,2.5))
                            "45"->arrayOf(DataPoint(0.0,0.0),DataPoint(3.535,3.535))
                            "60"->arrayOf(DataPoint(0.0,0.0),DataPoint(2.5,4.33))
                            "90"->arrayOf(DataPoint(0.0,0.0),DataPoint(0.0,5.0))
                            else->arrayOf(DataPoint(-5.0,0.0),DataPoint(0.0,0.0))
                        }).also{it.color=mathview.textColor})
                        addSeries(PointsGraphSeries(arrayOf(when(values[0]){
                            "0"->DataPoint(5.0,0.0)
                            "30"->DataPoint(4.33,2.5)
                            "45"->DataPoint(3.535,3.535)
                            "60"->DataPoint(2.5,4.33)
                            "90"->DataPoint(0.0,5.0)
                            else->DataPoint(-5.0,0.0)
                        })).also{it.color=Color.GREEN})
                    }
                    6->{visibility=View.GONE
                    }
                    7->{}
                }
            }
            mathview.latex=when(i){
                1->with(Array<Int>(4){i->values[1].split(" ")[i].toInt()}){"z_1="+complex(this[0],this[1])+"\\\\z_2="+complex(this[2],this[3])}
                4->"\\\\z=x+iy=r(\\cos\\varphi+i\\sin\\varphi)=re^{i\\varphi}\\\\r=|z|=\\sqrt{x^2+y^2}\\\\x=r\\cos\\varphi\\\\y=r\\sin\\varphi"
                6->when(values[0]){
                    "2(cos 60°+i sin 60°)"->{
                        when((0..1).random()){
                            0->"1+\\sqrt3i"
                            else->"2e^{i\\frac{\\pi}{3}}"
                        }
                    }
                    "2(cos 45°+i sin 45°)"->{
                        when((0..1).random()){
                            0->"\\sqrt2+\\sqrt2i"
                            else->"2e^{i\\frac{\\pi}{4}}"
                        }
                    }
                    "5(cos 90°+i sin 90°)"->{
                        when((0..1).random()){
                            0->"5i"
                            else->"5e^{i\\frac{\\pi}{2}}"
                        }
                    }
                    "cos π+i sin π"->{
                        when((0..1).random()){
                            0->"-1"
                            else->"e^{i\\pi}"
                        }
                    }
                    else->""
                }
                7->with(Array<Int>(3){i->values[1].split(" ")[i].toInt()}){
                    when(this[1]){
                        -1->{
                            when(this[2]){
                                -1->"${this[0]}\\sqrt2e^{i\\frac{5\\pi}{4}}"
                                0->"${this[0]}e^{i\\pi}"
                                else->"${this[0]}\\sqrt2e^{i\\frac{3\\pi}{4}}"
                            }
                        }
                        0->{
                            when(this[2]){
                                -1->"${this[0]}e^{i\\frac{3\\pi}{2}}"
                                else->"${this[0]}e^{i\\frac{\\pi}{2}}"
                            }
                        }
                        else->{
                            when(this[2]){
                                -1->"${this[0]}\\sqrt2e^{i\\frac{7\\pi}{4}}"
                                0->"${this[0]}e^{0i}"
                                else->"${this[0]}\\sqrt2e^{i\\frac{\\pi}{4}}"
                            }
                        }
                    }
                }
                else->""
            }
            mathview.visibility=when(i){
                1->View.VISIBLE
                2->View.GONE
                4->View.VISIBLE
                5->View.GONE
                6->View.VISIBLE
                else->mathview.visibility
            }
            if (i == taskTypes.size) {
                val intt = Intent(this, ActResult::class.java)
                intt.putExtra("score", score)
                intt.putExtra("max", max)
                intt.putExtra("zone", "complex")
                intt.putExtra("act", 2)
                if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusComplexAct3", 0) < (score.toDouble() / max.toDouble() * 100.0).toInt()) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusComplexAct3", (score.toDouble() / max.toDouble() * 100.0).toInt()).apply()
                if (score>=max/2 && PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusComplexBoss",-1)<0) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusComplexBoss",0).apply()
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
fun valueMakerForComplexAct3(i: Int): Array<String>{
    var ans=""; var n1=""; var n2=""; var n3=""; var passable="";val num1: Int; var num2: Int; var num3: Int
    when(i) {
        1 -> {
            num1 = (-50..50).random();num2 = (-50..50).random();ans = "${num1+num2}";n1 = "${(-100..100).random()} $num1 ${(-100..100).random()} $num2"
        }
        2->{ans="${(3..7).random()}"}
        3->{ans=arrayOf("а) да, б) да","а) да, б) нет","а) нет, б) нет","а) нет, б) да")[(0..3).random()]}
        5->{ans="${arrayOf(0,30,45,60,90,180)[(0..5).random()]}"}
        6->{val arr=arrayOf("2(cos 60°+i sin 60°)","2(cos 45°+i sin 45°)","5(cos 90°+i sin 90°)","cos π+i sin π")
            ans=arr[(0..3).random()]}
        7->{num1=(1..15).random();do{num2=(-1..1).random();num3=(-1..1).random()}while(num2==0&&num3==0);ans= complex(num1*num2,num1*num3);n1="$num1 $num2 $num3"}
    }
    Log.d("ok",ans)
    return arrayOf(ans,n1,n2,n3,passable)
}
fun answerMakerForComplexAct3(values: Array<String>, i: Int): Array<String> {
    val ans=values[0]
    val b= MutableList(4){""}
    val pas=values[4]
    b[0]=ans
    when(i){
        1->{}
        2->{}
        3->{b[0]="а) да, б) да";b[1]="а) да, б) нет";b[2]="а) нет, б) нет";b[3]="а) нет, б) да"}
        5->{}
        6->{val arr=arrayOf("2(cos 60°+i sin 60°)","2(cos 45°+i sin 45°)","5(cos 90°+i sin 90°)","cos π+i sin π","2(sin 30°+i cos 30°)","2(sin 45°+i cos 45°)","5(sin 0+i cos 0)","5eⁱ²")
        do b[1]=arr[(0..7).random()] while (b[1]==b[0]); do b[2]=arr[(0..6).random()] while (b[2]==b[1]||b[2]==b[0]); do b[3]=arr[(0..5).random()] while (b[3]==b[2]||b[3]==b[1]||b[3]==b[0])}
        7->{with(Array<Int>(3){j->values[1].split(" ")[j].toInt()}){b[1]=complex(-this[0]*this[1],-this[0]*this[2]);b[2]= complex(-this[0]*this[1]*2,-this[0]*this[2]*2);b[3]= complex(this[0]*this[1]*2,this[0]*this[2]*2)}}
    }
    b.shuffle()
    return arrayOf(b[0],b[1],b[2],b[3],"${b.indexOf(ans)}")
}
