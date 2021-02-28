package com.makentoshe.mathworks.ZoneDeriv

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.PointsGraphSeries
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.layout_act_tasks_graph.*
import java.util.Collections.shuffle
import kotlin.math.pow
import kotlin.math.sin

class DerivAct3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks_graph)
        headSetup.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
        mathview.visibility= View.VISIBLE
        mathview.fontSize=40.0F
        graph.visibility= View.VISIBLE
        val formulas=mapOf(
                0 to "\\\\(1){f}\\prime(x_0)=\\lim_{x \\to x_0}\\frac{f(x)-f(x_0)}{x-x_0}\\\\(2)f\\in D(x_0) \\Leftrightarrow\\exists f\\prime(x_0)\\in(-\\infty;+\\infty)\\\\(3)y=f(x_0)+f\\prime(x_0)(x-x_0)\n",
                2 to "y=f(x_0)+{f}\\prime(x_0)(x-x_0)",
                3 to "\\\\(1)(f+g)\\prime=f\\prime+g\\prime\\\\(2)(f-g)\\prime=f\\prime-g\\prime\\\\(3)(fg)\\prime=f\\prime g+fg\\prime\\\\(4)(\\frac{f}{g})\\prime=\\frac{f\\prime g-fg\\prime}{g^2}\\\\(5)(f(g(x)))\\prime=f\\prime(g(x))\\cdot g\\prime(x)\\\\(6)C\\prime=0;\\;C=const",
                6 to "\\\\(1)f(x)\\leq f(x_0); x \\neq x_0\\\\(2)f(x)\\geq f(x_0); x \\neq x_0"
        )
        val graph = findViewById<View>(R.id.graph) as GraphView
        graph.run {
            viewport.isYAxisBoundsManual = true
            viewport.setMinY(-1.1)
            viewport.setMaxY(10.1)
            viewport.isXAxisBoundsManual = true
            viewport.setMinX(-5.1)
            viewport.setMaxX(5.1)
            val series1= LineGraphSeries<DataPoint>(Array(601){ i-> DataPoint((i - 301).toDouble() / 100, ((i - 301).toDouble() / 100).pow(2)) }); series1.color= Color.rgb(255,128,0)
            val series2= LineGraphSeries<DataPoint>(arrayOf(DataPoint(0.0,-1.0), DataPoint(5.0,9.0))); series2.color= Color.RED
            val series3= PointsGraphSeries<DataPoint>(arrayOf(DataPoint(1.0,1.0))); series3.size=10.0F; series3.color= Color.BLACK
            addSeries(series1)
            addSeries(series2)
            addSeries(series3)
        }
        var step =0
        var score =0
        val max =6
        var i=0
        val taskTypes=arrayOf(0,2,1,0,1,1,0,2,1)
        val taskQuantity=arrayOf(0,2,0,0,0,0,0,0,1)
        val taskNames=arrayOf("act_deriv_3_descr_1", "act_deriv_3_task_1", "act_deriv_3_task_2", "act_deriv_3_descr_2", "act_deriv_3_task_3", "act_deriv_3_task_4", "act_deriv_3_descr_3","act_deriv_3_task_5","act_deriv_3_task_6")
        descrText.text=getText(resources.getIdentifier(taskNames[0], "string", packageName))
        var values=Array(4) { "" }
        var variants=Array(5) { "" }
        var choice: String
        var a=""
        formulas[0].toString().also { mathview.latex = it }
        taskText.visibility = View.GONE; descrText.visibility= View.VISIBLE
        radioGroupTask.visibility = View.GONE; editTextTask.visibility= View.GONE
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
                intt.putExtra("act", 2)
                startActivity(intt)
            }
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) values = valueMakerForDerivAct3(i)
                when(i){
                    1->{graph.visibility=View.GONE
                        mathview.visibility=View.GONE}
                    2->{mathview.visibility=View.VISIBLE;mathview.latex=formulas[2].toString()}
                    3->{mathview.latex=formulas[3].toString()}
                    4->{mathview.latex="\\\\h(x)=f(x)\\cdot g(x)\\\\f(x)=${values[3].split(' ')[0]} x\\\\g(x)=${values[3].split(' ')[1]} x\\\\(px)\\prime=p;\\;(x^p)\\prime=px^{p-1}"}
                    5->{mathview.latex="\\\\h(\\chi)=${values[3].split(' ')[1]}^{${values[3].split(' ')[0]}\\chi}\\\\(a^\\chi)=a^\\chi\\ln a"}
                    6->{mathview.latex=formulas[6].toString()}
                    7->{graph.visibility=View.VISIBLE
                        mathview.visibility=View.GONE
                        graph.run{
                            removeAllSeries()
                            when(values[0]){
                                "0"->{
                                    val series1=LineGraphSeries<DataPoint>(Array(1001){i->DataPoint((i-501).toDouble()/100,i.toDouble()/100+sin(i.toDouble()/200))})
                                    series1.color=Color.BLUE
                                    graph.addSeries(series1)
                                }
                                "1"->{
                                    val series1=LineGraphSeries<DataPoint>(Array(601){ i-> DataPoint((i - 301).toDouble() / 100, ((i - 301).toDouble() / 100).pow(2)) })
                                    series1.color=Color.BLACK
                                    graph.addSeries(series1)
                                }
                                "2"->{
                                    val series1=LineGraphSeries<DataPoint>(Array(491){i->DataPoint((i-246).toDouble()/100,((i-246).toDouble()/100).pow(3)/2-((i-246).toDouble()/100)+5)})
                                    series1.color=Color.BLUE
                                    graph.addSeries(series1)
                                }
                                "3"->{
                                    val series1=LineGraphSeries<DataPoint>(Array<DataPoint>(731){i->DataPoint((i-360).toDouble()/100,((i-360).toDouble()/100).pow(4)/8+2-((i-360).toDouble()/100).pow(2))})
                                    series1.color=Color.BLACK
                                    graph.addSeries(series1)
                                }
                            }
                        }
                    }
                    8->{graph.visibility=View.GONE}
                }
                variants = answerMakerForDerivAct3(values,i)
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
                val name = when(taskQuantity[i]){
                    1 -> getString(resources.getIdentifier(taskNames[i], "string", packageName),values[1])
                    2 -> getString(resources.getIdentifier(taskNames[i], "string", packageName),values[1],values[2])
                    else -> getString(resources.getIdentifier(taskNames[i], "string", packageName))
                }
                taskText.text = name
            }
        }
    }
}
fun valueMakerForDerivAct3(i: Int): Array<String>{
    var ans="\\"; val num1: Int; var num2: Int; val num3: Int; val num4: Int; var n1=""; var n2=""; var pas=""
    when(i) {
        1 -> {num1=(-10..10).random(); num2=num1+(5..10).random(); num3=(-10..10).random(); ans="${(-5..5).random()}"; num4=num3+ans.toInt()*(num2-num1); n1="$num1; $num3"; n2="$num2; $num4"}
        2 -> {ans="геометрический"}
        4 -> {num1=(2..10).random();do{num2=(2..10).random()}while(num2==num1); ans="${num1*num2*2}x";pas="$num1 $num2 ${num1*num2}"}
        5 -> {
            val dic= mapOf(2 to "²",3 to "³",4 to "⁴",5 to "⁵",6 to "⁶",7 to "⁷",8 to "⁸",9 to "⁹",10 to "¹⁰")
            num1=(2..10).random();do{num2=(4..10).random()}while(num2==num1); ans="$num1·$num2${dic[num1]}ᵡ ln $num2"; pas="$num1 $num2"
        }
        7 -> {ans="${(0..3).random()}"}
        8 -> {num1=(0..2).random(); ans=arrayOf("точка стационарного перегиба","точка минимума","точка максимума")[num1]; n1=arrayOf("не меняет знак","меняет знак с  «-» на «+»","меняет знак с «+» на «-»")[num1]}
    }
    return arrayOf(ans,n1,n2,pas)
}
fun answerMakerForDerivAct3(values: Array<String>, i: Int): Array<String> {
    val ans=values[0]
    val b= MutableList<String>(4){""}
    b[0]=ans
    when(i){
        1->{}
        2->{b[1]="физический";b[2]="интегральный";b[3]="смысловой"}
        4->{b[1]=values[3].split(' ')[0]+"x";b[2]=values[3].split(' ')[1]+"x";b[3]="${values[3].split(' ')[0].toInt()*values[3].split(' ')[1].toInt()}"+"x"}
        5 -> {
            val dic = mapOf(2 to "²", 3 to "³", 4 to "⁴", 5 to "⁵", 6 to "⁶", 7 to "⁷", 8 to "⁸", 9 to "⁹", 10 to "¹⁰")
            b[1] = "${values[3].split(' ')[0]}·${values[3].split(' ')[1]}ᵡ ln ${values[3].split(' ')[1]}"
            b[2]= "${values[3].split(' ')[1]}${dic[values[3].split(' ')[0].toInt()]}ᵡ ln ${values[3].split(' ')[1]}"
            b[3]= "${values[3].split(' ')[1]}·${values[3].split(' ')[0]}${dic[values[3].split(' ')[1].toInt()]}ᵡ ln ${values[3].split(' ')[0]}"
        }
        7->{}
        8->{b[1]="точка разрыва"
            val arr=arrayOf("точка стационарного перегиба","точка минимума","точка максимума");do{b[2]=arr[(0..2).random()]}while(b[2]==b[0]);do{b[3]=arr[(0..2).random()]}while(b[2]==b[3]||b[3]==b[0])}
    }
    shuffle(b)
    val p="${b.indexOf(ans)}"
    return arrayOf(b[0],b[1],b[2],b[3],p)
}