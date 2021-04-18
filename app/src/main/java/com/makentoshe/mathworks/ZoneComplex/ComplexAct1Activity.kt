package com.makentoshe.mathworks.ZoneComplex

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import androidx.annotation.RequiresApi
import androidx.preference.PreferenceManager
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.PointsGraphSeries
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.layout_act_tasks_graph.*
import kotlin.math.*

class ComplexAct1Activity : AppCompatActivity() {
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onCreate(savedInstanceState: Bundle?) {
            setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid", R.style.AppTheme))
            super.onCreate(savedInstanceState)
            setContentView(R.layout.layout_act_tasks_graph)
            headSetup.text = intent.getStringExtra("zone")
            subheadTask.text = intent.getStringExtra("act")
            mathview.visibility = View.VISIBLE
            mathview.fontSize = 40.0F
            mathview.textColor = descrText.currentTextColor
            val graph = findViewById<View>(R.id.graph) as GraphView
            graph.visibility=View.GONE
            graph.gridLabelRenderer.gridColor = mathview.textColor
            graph.gridLabelRenderer.horizontalLabelsColor = mathview.textColor
            graph.gridLabelRenderer.verticalLabelsColor = mathview.textColor
            val formulas = mapOf(
                    0 to "\\\\z=1+\\sqrt3i\\\\Re(z)=1; Im(z)=\\sqrt3\\\\\\bar{z}=1-\\sqrt3i",
                    3 to "\\\\(-\\frac{3}{2};\\frac{3\\sqrt3}{2})\\\\z=-\\frac{3}{2}+\\frac{3\\sqrt3}{2}i\\\\z=3(\\cos\\frac{2\\pi}{3}+i\\sin\\frac{2\\pi}{3})\\\\z=3e^{\\frac{2i\\pi}{3}}"
            )
            graph.run {
                viewport.isYAxisBoundsManual = true
                viewport.setMinY(-5.0)
                viewport.setMaxY(5.1)
                viewport.isXAxisBoundsManual = true
                viewport.setMinX(-5.0)
                viewport.setMaxX(5.1)
                gridLabelRenderer.horizontalAxisTitle="Re(z)"
                gridLabelRenderer.verticalAxisTitle="Im(z)"
            }
            graph.run {
                addSeries(LineGraphSeries<DataPoint>(Array(181){i-> DataPoint(-3*cos(i.toDouble()*3.14159265/180),3*sin(i.toDouble()*3.14159265/180))}))
                addSeries(LineGraphSeries<DataPoint>(Array(181){i-> DataPoint(-3*cos(i.toDouble()*3.14159265/180),-3*sin(i.toDouble()*3.14159265/180))}))
                addSeries(PointsGraphSeries<DataPoint>(arrayOf(DataPoint(-1.5, 2.598))).also{it.color=Color.RED})
            }
            formulas[0].toString().also { mathview.latex = it }
            var step = 0
            var score = 0
            val max = 4
            var i = 0
            val taskTypes = arrayOf(0, 2, 1, 0, 1, 2)
            val taskQuantity = arrayOf(0,2,2,0,2,0)
            val taskNames = arrayOf("act_complex_1_descr_1", "act_complex_1_task_1", "act_complex_1_task_2", "act_complex_1_descr_2", "act_complex_1_task_3", "act_complex_1_task_4")
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
                values= valueMakerForComplexAct1(i)
                variants= answerMakerForComplexAct1(values,i)
                if(i==3)graph.visibility=View.VISIBLE else graph.visibility=View.GONE
                mathview.latex=when(i){
                    1->values[1]
                    2->if(values[3].toInt()>=0)"${values[2]}+${values[3]}i" else "${values[2]}${values[3]}i"
                    3-> formulas[3].toString()
                    4->if(values[3].toInt()>=0)"${values[2]}+${values[3]}i" else "${values[2]}${values[3]}i"
                    5->when(values[0].toInt()){
                        -2->{"Re(-2+2i)"}
                        -1->{"Im(-i)"}
                        0->{"e^{i\\pi}+1."}
                        1->{"e^{0i}"}
                        else->{"|1+\\sqrt3i|"}
                    }
                    else->""
                }
                if (i == taskTypes.size) {
                    val intt = Intent(this, ActResult::class.java)
                    intt.putExtra("score", score)
                    intt.putExtra("max", max)
                    intt.putExtra("zone", "complex")
                    intt.putExtra("act", 0)
                    if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusComplexAct1", 0) < (score.toDouble() / max.toDouble() * 100.0).toInt()) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusComplexAct1", (score.toDouble() / max.toDouble() * 100.0).toInt()).apply()
                    startActivity(intt)
                }
                radioGroupTask.clearCheck()
                editTextTask.setText("")
                if (i < taskTypes.size) {
                    if (taskTypes[i] != 0)
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
fun valueMakerForComplexAct1(i: Int): Array<String>{
    var ans=""; var n1=""; var n2=""; var n3=""; var passable="";val num1: Int; val num2: Int; val num3: Int
    when(i) {
        1 -> {num1=((-100..-1)+(1..100)).random();num2=((-100..-1)+(1..100)).random();ans="$num2";n1=if(num2>=0)"$num1+${num2}i" else "$num1${num2}i"}
        2 -> {num1=((-100..-1)+(1..100)).random();num2=((-100..-1)+(1..100)).random();ans=if(num2>=0)"$num1-${num2}i" else "$num1+${-num2}i";n2="$num1";n3="$num2"}
        4 -> {num1=((-100..-1)+(1..100)).random();num2=((-100..-1)+(1..100)).random()
            if(num1>0 && num1>0)ans="(0; π/2)" else if (num1<0&&num2>0) ans="(π/2; π)" else if (num1<0&&num2<0) ans="(π; 3π/2)" else ans="(3π/2;2π)";n2="$num1";n3="$num2"
        }
        5 -> {ans= (-2..2).random().toString() }
    }
    return arrayOf(ans,n1,n2,n3,passable)
}
fun answerMakerForComplexAct1(values: Array<String>, i: Int): Array<String> {
    val ans=values[0]
    val b= MutableList(4){""}
    val pas=values[4]
    b[0]=ans
    when(i){
        1->{}
        2->{val Re=values[2].toInt(); val Im=values[3].toInt();b[1]=if(Im>=0)"$Re+${Im}i" else "$Re${Im}i"; b[2]=if(Im>=0)"-$Re+${Im}i" else "${-Re}${Im}i"; b[3]=if(Im>=0)"-$Re-${Im}i" else "${-Re}+${-Im}i"}
        4->{b[0]="(0; π/2)";b[1]="(π/2; π)";b[2]="(π; 3π/2)";b[3]="(3π/2;2π)"}
        5->{}
    }
    b.shuffle()
    return arrayOf(b[0],b[1],b[2],b[3],"${b.indexOf(ans)}")
}