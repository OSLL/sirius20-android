package com.makentoshe.mathworks.ZoneDeriv

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.annotation.RequiresApi
import androidx.preference.PreferenceManager
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.PointsGraphSeries
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.layout_act_tasks_graph.*
import kotlin.math.*

class DerivAct5Activity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks_graph)
        headSetup.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
        mathview.visibility= View.VISIBLE
        mathview.fontSize=40.0F
        mathview.textColor=descrText.currentTextColor
        graph.gridLabelRenderer.gridColor=mathview.textColor
        graph.gridLabelRenderer.horizontalLabelsColor=mathview.textColor
        graph.gridLabelRenderer.verticalLabelsColor=mathview.textColor
        val formulas=mapOf(
            0 to "\\\\C\\prime=0,C=const\\\\(x^n)\\prime=nx^{n-1}\\\\(\\sqrt{x})\\prime=\\frac{1}{2\\sqrt{x}}\\\\(a^x)\\prime=a^x\\ln a\\\\(e^x)\\prime=e^x\\\\(\\log_ax)\\prime=\\frac{1}{x\\ln a}\\\\(\\ln x)\\prime=\\frac{1}{x}\\\\(\\sin x)\\prime=\\cos x\\\\(\\cos x)\\prime=-\\sin x\\\\(\\tan x)\\prime=\\frac{1}{\\cos^2x}\\\\(\\cot x)'=-\\frac{1}{\\sin^2x}",
            4 to "\\\\\\int 0\\;dx=C,C=const\\\\\\int dx=x+C\\\\\\int x^p dx=\\frac{x^{p+1}}{p+1}+C,\\\\p\\neq-1, x>0\\\\\\int(kx+b)^pdx=\\frac{(kx+b)^{p+1}}{k(p+1)}+C\\\\\\int\\frac{dx}{x}=\\ln{|x|}+C\\\\\\int a^xdx=\\frac{a^x}{\\ln a}+C\\\\\\int e^x dx=e^x+C\\\\\\int \\sin x\\;dx=-\\cos x+C\\\\\\int \\cos x\\;dx=\\sin x+C\\\\\\int\\frac{dx}{\\cos^2x}=\\tan x+C\\\\\\int\\frac{dx}{\\sin^2x}=-\\cot x+C"
        )
        var step =0
        var score =0
        val max =6
        var i=0
        val taskTypes=arrayOf(0,1,2,1,0,2,1,1)
        val taskNames=arrayOf("act_deriv_5_descr_1", "act_deriv_5_task_1", "act_deriv_5_task_2", "act_deriv_5_task_3", "act_deriv_5_descr_2", "act_deriv_5_task_4","act_deriv_5_task_5","act_deriv_5_task_6")
        descrText.text=getText(resources.getIdentifier(taskNames[0], "string", packageName))
        var values=Array(2) { "" }
        var variants=Array(5) { "" }
        var choice: String
        var a=""
        formulas[0].toString().also { mathview.latex = it }
        taskText.visibility = View.GONE; descrText.visibility= View.VISIBLE
        radioGroupTask.visibility = View.GONE; editTextTask.visibility= View.GONE
        graph.visibility=View.GONE
        editTextTask.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                a = editTextTask.text.toString().trim()
            }
        })
        continueButtonTask.setOnClickListener {
            taskImage.visibility=View.GONE
            if (taskTypes[i]!=0) {
                if (taskTypes[i]==1){
                    if (radioButtonTask1.isChecked) choice="0"
                    else if (radioButtonTask2.isChecked) choice="1"
                    else if (radioButtonTask3.isChecked) choice="2"
                    else if (radioButtonTask4.isChecked) choice="3"
                    else choice="4"
                    if (choice==variants[4]) score++
                }
                if (taskTypes[i]==2){
                    if (a==values[0]) score++
                }
                step++
            }
            i++
            if (i==taskTypes.size) {
                val intt = Intent(this, ActResult::class.java)
                intt.putExtra("score", score)
                intt.putExtra("max", max)
                intt.putExtra("zone", "deriv")
                intt.putExtra("act", 4)
                if (score==max) {PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusDerivAct5",1).apply()}
                startActivity(intt)
            }
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) values = valueMakerForDerivAct5(i)
                when(i){
                    1->{mathview.latex="\\\\f(x)=${values[1].split(' ')[1]}x^{${values[1].split(' ')[2]}}"
                    }
                    2->{
                        when(values[0]){
                            "-2" -> {
                                when ((0..1).random()) {
                                    0-> mathview.latex="\\\\f(x)=x^2\\\\\\xi=-1"
                                    1-> mathview.latex="\\\\f(x)=\\-sin 2x\\\\\\xi=0"
                                }
                            }
                            "-1" -> {
                                when ((0..1).random()) {
                                    0-> mathview.latex="\\\\f(x)=-\\sin x\\\\\\xi=${((-5..-1)+(1..5)).random()*2}\\pi"
                                    1-> mathview.latex="\\\\f(x)=\\tan x\\\\\\xi=${((-5..-1)+(1..5)).random()}\\pi"
                                }
                            }
                            "0" -> {
                                when ((0..1).random()) {
                                    0-> mathview.latex="\\\\f(x)=\\sin x\\\\\\xi=\\frac{\\pi}{2}"
                                    1-> mathview.latex="\\\\f(x)=\\cos x\\\\\\xi=${(2..10).random()}\\pi"
                                }
                            }
                            "1" -> {
                                when ((0..1).random()) {
                                    0-> mathview.latex="\\\\f(x)=-\\ln (-${(2..10).random()}x)\\\\\\xi=-1"
                                    1-> mathview.latex="\\\\f(x)=\\ln ${(2..10).random()}x\\\\\\xi=1"
                                }
                            }
                            else -> {
                                val a=(2..10).random()
                                when ((0..1).random()) {
                                    0-> mathview.latex="\\\\f(x)=2\\cos x\\\\\\xi=\\frac{${1+((-3..-1)+(1..3)).random()*4}\\pi}{2}"
                                    1-> mathview.latex="\\\\f(x)=$a x^2\\\\\\xi=\\frac{1}{$a}"
                                }
                            }
                        }
                    }
                    3->{mathview.visibility=View.GONE
                        graph.visibility=View.VISIBLE
                        graph.run {
                            viewport.isYAxisBoundsManual = true
                            viewport.setMinY(-5.1)
                            viewport.setMaxY(5.1)
                            viewport.isXAxisBoundsManual = true
                            viewport.setMinX(-5.1)
                            viewport.setMaxX(5.1)
                            val series1= when(values[0]){
                                "sin x" -> {LineGraphSeries(Array(1000){ i->DataPoint(i.toDouble()/100-5.0,-cos(i.toDouble()/100-5.0)+1.0)})}
                                "cos x" -> {LineGraphSeries(Array(1000){ i->DataPoint(i.toDouble()/100-5.0, sin(i.toDouble()/100-5.0))})}
                                "-sin x" -> {LineGraphSeries(Array(1000){ i->DataPoint(i.toDouble()/100-5.0,cos(i.toDouble()/100-5.0)-1.0)})}
                                else -> {LineGraphSeries(Array(1000){ i->DataPoint(i.toDouble()/100-5.0,-sin(i.toDouble()/100-5.0))})}
                            }
                            val series2= PointsGraphSeries<DataPoint>(arrayOf(DataPoint(0.0,0.0))); series2.size=10.0F; series2.color= Color.RED
                            addSeries(series1)
                            addSeries(series2)
                        }
                    }
                    4->{mathview.visibility=View.VISIBLE
                        graph.visibility=View.GONE
                        mathview.latex=formulas[4].toString()
                    }
                    5->{val a=when((0..1).random()){0->"";else->"-"}
                        mathview.latex=when(values[1].split(' ')[0]){
                        "0"-> "\\\\f(x)=${values[1].split(' ')[3].toInt()+1}x^${values[1].split(' ')[3]},\\\\a=${values[1].split(' ')[1]},b=${values[1].split(' ')[2]}"
                        "1"-> "\\\\f(x)=\\frac{1}{x},\\\\a=$a 1,\\;b=$a e"
                        "2"-> "\\\\f(x)=\\cos x,\\\\a=${values[1].split(' ')[1]}\\cdot\\frac{\\pi}{2},b=${values[1].split(' ')[2]}\\cdot\\frac{\\pi}{2}"
                            else -> ""
                    }}
                    6->{mathview.latex="\\\\f(x)="+when(values[0]){
                        "sin x+C"->"\\cos x"
                        "cos x+C"->"-\\sin x"
                        "ln|x|+C"->"\\frac{1}{x}"
                        "tan x+C"->"\\frac{1}{\\cos^2x}"
                        "cot x+C"->"-\\frac{1}{\\sin^2x}"
                        "-x+C"->"-1"
                        "-sin x+C"->"-\\cos x"
                        else->"2\\cos 2x"
                    }}
                    7->{mathview.visibility=View.GONE
                        graph.visibility=View.VISIBLE
                        graph.removeAllSeries()
                        when (values[0]){
                            "χ"->{
                                graph.run{
                                    val series1=LineGraphSeries(arrayOf(DataPoint(-5.0,1.0),DataPoint(5.0,1.0)))
                                    addSeries(series1)
                                }
                            }
                            "χ²"->{
                                graph.run{
                                    val series1=LineGraphSeries(arrayOf(DataPoint(-2.5,-5.0),DataPoint(2.5,5.0)))
                                    addSeries(series1)
                                }
                            }
                            "sin χ+χ"->{
                                graph.run{
                                    viewport.setMinX(-4.1)
                                    viewport.setMaxX(4.1)
                                    val series1=LineGraphSeries(Array(800){ i->DataPoint(i.toDouble()/100-4.0,cos(i.toDouble()/100-4.0)+1.0)})
                                    addSeries(series1)
                                }

                            }
                            "cos χ+χ"->{
                                graph.run{
                                    viewport.setMinX(-4.1)
                                    viewport.setMaxX(4.1)
                                    val series1=LineGraphSeries(Array(800){ i->DataPoint(i.toDouble()/100-4.0,-sin(i.toDouble()/100-4.0)+1.0)})
                                    addSeries(series1)
                                }
                            }
                            "ln χ"->{
                                graph.run{
                                    viewport.setMinX(0.0)
                                    viewport.setMaxX(5.1)
                                    viewport.setMinY(0.0)
                                    viewport.setMaxY(5.1)
                                    val series1=LineGraphSeries(Array(480){ i->DataPoint(i.toDouble()/100+0.2,1/(i.toDouble()/100+0.2))})
                                    addSeries(series1)
                                }
                            }
                            "C"->{
                                graph.run{
                                    val series1=LineGraphSeries(arrayOf(DataPoint(-5.0,0.0),DataPoint(5.0,0.0)))
                                    addSeries(series1)
                                }
                            }
                            else->{
                                graph.run{
                                    viewport.setMaxY(E.pow(5)+0.1)
                                    viewport.setMinY(-0.1)
                                    val series1=LineGraphSeries(Array(1000){ i->DataPoint(i.toDouble()/100-5.0,E.pow(i.toDouble()/100-5.0))})
                                    addSeries(series1)
                                }
                            }
                        }
                    }
                }
                variants = answerMakerForDerivAct5(values,i)
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
                taskText.text = getString(resources.getIdentifier(taskNames[i], "string", packageName))
            }
        }
    }
}
fun valueMakerForDerivAct5(i: Int): Array<String>{
    var ans="\\"; val num1: Int; val num2: Int; val num3: Int; var pas=""
    when(i) {
        1 -> {val dic = mapOf(2 to "²", 3 to "³", 4 to "⁴", 5 to "⁵", 6 to "⁶", 7 to "⁷", 8 to "⁸", 9 to "⁹")
                num1=(2..10).random();num2=(3..10).random();ans="${num1*num2}x${dic[num2-1]}";pas="0 $num1 $num2"
        }
        2 -> {ans="${(-2..2).random()}"}
        3 -> {when((0..3).random()){
            0->{ans="sin x"; pas="cos x -sin x -cos x"}
            1->{ans="cos x"; pas="sin x -sin x -cos x"}
            2->{ans="-sin x"; pas="cos x sin x -cos x"}
            3->{ans="-cos x"; pas="cos x -sin x sin x"}
        }}
        5 -> {when((0..2).random()){
            0->{num1=(0..4).random();num2=(0..4).random();num3=(1..3).random();ans="${(num2.toDouble().pow(num3+1)-num1.toDouble().pow(num3+1)).toInt()}"; pas="0 $num1 $num2 $num3"}
            1->{ans="1";pas="1"}
            2->{num1=(-10..10).random();num2=(num1..10).random()
                ans="${round(sin(num2.toDouble()*PI/2)-sin(num1.toDouble()*PI/2)).toInt()}"
                pas="2 $num1 $num2"
            }
            }
        }
        6 -> {val arr=arrayOf("sin x","cos x","ln|x|","tan x","cot x","-x", "-sin x")
            ans=arr[(0..6).random()]+"+C"}
        7 -> {val arr=arrayOf("χ","χ²","sin χ+χ","cos χ+χ","ln χ","C","eᵡ")
            ans=arr[(0..6).random()]}
    }
    return arrayOf(ans,pas)
}
fun answerMakerForDerivAct5(values: Array<String>, i: Int): Array<String> {
    val ans=values[0]
    val b= MutableList(4){""}
    b[0]=ans
    when(i) {
        1 -> { val dic = mapOf(2 to "²", 3 to "³", 4 to "⁴", 5 to "⁵", 6 to "⁶", 7 to "⁷", 8 to "⁸", 9 to "⁹", 10 to "¹⁰")
                b[1]="${values[1].split(' ')[1].toInt()*values[1].split(' ')[2].toInt()}x${dic[values[1].split(' ')[2].toInt()]}"
                b[2]="${values[1].split(' ')[1].toInt()}x${dic[values[1].split(' ')[2].toInt()]}"
                b[3]="${values[1].split(' ')[1].toInt()}x${dic[values[1].split(' ')[2].toInt()-1]}"
            }
        2 -> {}
        3 -> {b[1]=values[1].split(' ')[0]+" "+values[1].split(' ')[1]; b[2]=values[1].split(' ')[2]+" "+values[1].split(' ')[3]; b[3]=values[1].split(' ')[4]+" "+values[1].split(' ')[5];}
        5 -> {}
        6 -> {val arr=arrayOf("sin x","cos x","ln|x|","tan x","cot x","-x", "-sin x")
            do{b[1]=arr[(0..6).random()]+"+C"}while(b[1]==b[0]);do{b[2]=arr[(0..6).random()]+"+C"}while(b[2]==b[1]||b[2]==b[0]);do{b[3]=arr[(0..6).random()]+"+C"}while(b[3]==b[2]||b[3]==b[1]||b[3]==b[0])}
        7 -> {val arr=arrayOf("χ","χ²","sin χ+χ","cos χ+χ","ln χ","C","eᵡ")
            do{b[1]=arr[(0..6).random()]}while(b[1]==b[0]);do{b[2]=arr[(0..6).random()]}while(b[2]==b[1]||b[2]==b[0]);do{b[3]=arr[(0..6).random()]}while(b[3]==b[2]||b[3]==b[1]||b[3]==b[0])}
    }
    b.shuffle()
    val p="${b.indexOf(ans)}"
    return arrayOf(b[0],b[1],b[2],b[3],p)
}