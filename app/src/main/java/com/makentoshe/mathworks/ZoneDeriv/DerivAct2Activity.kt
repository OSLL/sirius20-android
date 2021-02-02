package com.makentoshe.mathworks.ZoneDeriv


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.PointsGraphSeries
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.layout_act_tasks_graph.*

import kotlin.math.*


class DerivAct2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks_graph)
        headTask.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
        mathview.visibility= View.VISIBLE
        mathview.fontSize=40.0F
        graph.visibility=View.VISIBLE
        editTextTask.inputType= InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        val formulas=mapOf(
                0 to "\\\\(1)\\lim_{x\\to a}{f(x)}\\neq f(a)\\\\(2)\\lim_{x\\to a+0}{f(x)}\\neq \\lim_{x\\to a-0}{f(x)}\\\\(3)\\lim_{x\\to a+0}{f(x)}=\\pm \\infty \\lor\\\\\\lim_{x\\to a-0}{f(x)}=\\pm \\infty\\\\(4)\\neg\\exists\\lim_{x\\to a+0}{f(x)} \\lor \\neg\\exists\\lim_{x\\to a+0}{f(x)}\n",
                3 to "(1)\\lim_{x \\to a}f(x)+\\lim_{x \\to a}g(x)=\\\\\\lim_{x \\to a}(f(x)+g(x))\\\\(2)\\lim_{x \\to a}f(x)-\\lim_{x \\to a}g(x)=\\\\\\lim_{x \\to a}(f(x)-g(x))\\\\(3)\\lim_{x \\to a}f(x)\\cdot \\lim_{x \\to a}g(x)=\\\\\\lim_{x \\to a}(f(x)g(x))\\\\(4)\\frac{\\lim_{x \\to a}f(x)}{\\lim_{x \\to a}g(x)}=\\lim_{x \\to a}\\frac{f(x)}{g(x)}\\\\(5)\\lim _{{n\\to a }}f(x)=\\lim _{{n\\to a }}g(x)=A\\Rightarrow\\\\\\lim _{{n\\to a }}h(x)=A,\\\\f(x)\\leq h(x)\\leq g(x)"
        )
        val graph = findViewById<View>(R.id.graph) as GraphView
        graph.run {
            viewport.isYAxisBoundsManual = true
            viewport.setMinY(-1.0)
            viewport.setMaxY(10.1)
            viewport.isXAxisBoundsManual = true
            viewport.setMinX(-1.0)
            viewport.setMaxX(10.1)
            viewport.isScalable = true
            viewport.isScrollable = true
            viewport.setScalableY(true)
            viewport.setScrollableY(true)
        }
        val series1 = LineGraphSeries<DataPoint>(Array<DataPoint>(600){i-> DataPoint(i.toDouble()/100+1.5,((i.toDouble()-300)/100).pow(2))})
        series1.color = Color.BLUE
        val series2 = PointsGraphSeries<DataPoint>(arrayOf(DataPoint(4.5,0.0), DataPoint(4.5,1.0)))
        series2.shape=PointsGraphSeries.Shape.POINT
        series2.size= 10.0F
        series2.color = Color.BLUE
        val series3 = LineGraphSeries<DataPoint>(arrayOf(DataPoint(2.125,9.9),DataPoint(2.5,4.0),DataPoint(3.0,3.0),DataPoint(4.0,2.5),DataPoint(9.9,2.125)))
        series3.color = Color.RED
        val series4 = PointsGraphSeries<DataPoint>(arrayOf(DataPoint(2.0,2.0)))
        series4.shape=PointsGraphSeries.Shape.POINT
        series4.size= 10.0F
        series4.color = Color.RED
        val series5 = PointsGraphSeries<DataPoint>(arrayOf(DataPoint(4.5,0.0)))
        series5.shape=PointsGraphSeries.Shape.POINT
        series5.size= 8.0F
        series5.color = Color.WHITE
        graph.run{
            addSeries(series1)
            addSeries(series2)
            addSeries(series3)
            addSeries(series4)
            addSeries(series5)
        }
        formulas[0].toString().also { mathview.latex = it }
        var step =0
        var score =0
        val max =4
        var i=0
        val taskTypes=arrayOf(0, 1, 2, 0, 2, 2)
        val taskNames=arrayOf("act_deriv_2_descr_1", "act_deriv_2_task_1", "act_deriv_2_task_2", "act_deriv_2_descr_2", "act_deriv_2_task_3", "act_deriv_2_task_4")
        descrText.text=getText(resources.getIdentifier(taskNames[0], "string", packageName))
        var value =""
        var variants=Array(5) { "" }
        var choice: String
        var a=""
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
                    if (a==value) score++
                }
                step++
            }
            i++
            if (i==taskTypes.size) {
                val intt = Intent(this, ActResult::class.java)
                intt.putExtra("score", score)
                intt.putExtra("max", max)
                intt.putExtra("zone", "deriv")
                intt.putExtra("act", 1)
                startActivity(intt)
            }
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) value = randomAnswerMakerForDerivAct2(i)
                variants = arrayOf("Разрыв типа «полюс»","Разрыв типа «скачок»","Существенный разрыв","Устранимый разрыв",value)
                when(i){
                    1 -> {
                        mathview.visibility=View.GONE
                        graph.run{
                            removeAllSeries()
                            when(value){
                                "0"->{
                                    val series6=LineGraphSeries<DataPoint>(Array<DataPoint>(240){ i->DataPoint(i.toDouble()/50+5.2,1/(i.toDouble()/50+0.2)+5) })
                                    val series7=LineGraphSeries<DataPoint>(Array<DataPoint>(240){ i->DataPoint(i.toDouble()/50,5-(1/((240-i).toDouble()/50+0.2)))})
                                    graph.addSeries(series6)
                                    graph.addSeries(series7)
                                }
                                "1"->{
                                    val series6=LineGraphSeries<DataPoint>(arrayOf(DataPoint(0.0,4.0), DataPoint(5.0,4.0)))
                                    val series7=LineGraphSeries<DataPoint>(arrayOf(DataPoint(5.0,6.0),DataPoint(10.0,6.0)))
                                    val series8 = PointsGraphSeries<DataPoint>(arrayOf(DataPoint(5.0,4.0),DataPoint(5.0,5.0),DataPoint(5.0,6.0)))
                                    series6.color = Color.BLUE
                                    series7.color = Color.BLUE
                                    series8.color = Color.BLUE
                                    series8.size= 10.0F
                                    val series9 = PointsGraphSeries<DataPoint>(arrayOf(DataPoint(5.0,4.0),DataPoint(5.0,6.0)))
                                    series9.shape=PointsGraphSeries.Shape.POINT
                                    series9.size= 8.0F
                                    series9.color = Color.WHITE
                                    graph.addSeries(series6)
                                    graph.addSeries(series7)
                                    graph.addSeries(series8)
                                    graph.addSeries(series9)
                                }
                                "2"->{
                                    val series6=LineGraphSeries<DataPoint>(Array<DataPoint>(100){i->DataPoint(5.0-2.0.pow(2-i),(i%2)*2+4.toDouble())})
                                    val series8 = PointsGraphSeries<DataPoint>(arrayOf(DataPoint(5.0,5.0)))
                                    series6.color = Color.BLUE
                                    series8.color = Color.BLUE
                                    series8.size= 10.0F
                                    graph.addSeries(series6)
                                    graph.addSeries(series8)
                                }
                                else->{
                                    val series6 = LineGraphSeries<DataPoint>(Array<DataPoint>(600){i-> DataPoint(i.toDouble()/100+1.5,((i.toDouble()-300)/100).pow(2))})
                                    series6.color = Color.BLUE
                                    val series8 = PointsGraphSeries<DataPoint>(arrayOf(DataPoint(4.5,0.0), DataPoint(4.5,1.0)))
                                    series8.shape=PointsGraphSeries.Shape.POINT
                                    series8.size= 10.0F
                                    series8.color = Color.BLUE
                                    val series9 = PointsGraphSeries<DataPoint>(arrayOf(DataPoint(4.5,0.0)))
                                    series9.shape=PointsGraphSeries.Shape.POINT
                                    series9.size= 8.0F
                                    series9.color = Color.WHITE
                                    graph.addSeries(series6)
                                    graph.addSeries(series8)
                                    graph.addSeries(series9)
                                }
                            }
                        }
                    }
                    2 -> {
                        mathview.visibility=View.VISIBLE
                        graph.removeAllSeries()
                        when(value){
                            "первый"->{
                                mathview.latex="f(x)=x^2-9x+13,5"
                                val series6 = LineGraphSeries<DataPoint>(Array<DataPoint>(600){i-> DataPoint(i.toDouble()/100+1.5,((i.toDouble()-300)/100).pow(2))})
                                series6.color = Color.BLUE
                                val series8 = PointsGraphSeries<DataPoint>(arrayOf(DataPoint(4.5,0.0), DataPoint(4.5,1.0)))
                                series8.shape=PointsGraphSeries.Shape.POINT
                                series8.size= 10.0F
                                series8.color = Color.BLUE
                                val series9 = PointsGraphSeries<DataPoint>(arrayOf(DataPoint(4.5,0.0)))
                                series9.shape=PointsGraphSeries.Shape.POINT
                                series9.size= 8.0F
                                series9.color = Color.WHITE
                                graph.addSeries(series6)
                                graph.addSeries(series8)
                                graph.addSeries(series9)
                            }
                            else->{
                                mathview.latex="f(x)=\\frac{1}{x-5}+5"
                                val series6=LineGraphSeries<DataPoint>(Array<DataPoint>(240){ i->DataPoint(i.toDouble()/50+5.2,1/(i.toDouble()/50+0.2)+5) })
                                val series7=LineGraphSeries<DataPoint>(Array<DataPoint>(240){ i->DataPoint(i.toDouble()/50,5-(1/((240-i).toDouble()/50+0.2)))})
                                graph.addSeries(series6)
                                graph.addSeries(series7)
                            }
                        }
                    }
                    3 -> {
                        graph.visibility=View.GONE
                        mathview.visibility=View.GONE
                    }
                    4 -> {
                        graph.visibility=View.VISIBLE
                        graph.viewport.setMinY(-3.14)
                        graph.viewport.setMaxY(3.14)
                        graph.viewport.setMinX(-3.14)
                        graph.viewport.setMaxX(3.14)
                        mathview.visibility=View.VISIBLE
                        graph.removeAllSeries()
                        when ((0..1).random()){
                            0->{mathview.latex="f(x)=\\arccos x"
                                val series6=LineGraphSeries<DataPoint>(Array<DataPoint>(198){i->DataPoint(i.toDouble()/100-1,acos(i.toDouble()/100-0.99))})
                                graph.addSeries(series6)
                            }
                            1->{mathview.latex="f(x)=\\arcsin x"
                                val series6=LineGraphSeries<DataPoint>(Array<DataPoint>(198){i->DataPoint(i.toDouble()/100-1, asin(i.toDouble()/100-0.99))})
                                graph.addSeries(series6)
                            }
                        }
                    }
                    5 -> {
                        graph.viewport.setMinY(-1.0)
                        graph.viewport.setMaxY(10.1)
                        graph.viewport.setMinX(-1.0)
                        graph.viewport.setMaxX(10.1)
                        graph.removeAllSeries()
                        when ((0..1).random()){
                            0->{mathview.latex="f(x)=\\cos x"
                                val series6=LineGraphSeries<DataPoint>(Array<DataPoint>(99){i->DataPoint(i.toDouble()/10,cos(i.toDouble()/10))})
                                graph.addSeries(series6)
                            }
                            1->{mathview.latex="f(x)=\\sin x"
                                val series6=LineGraphSeries<DataPoint>(Array<DataPoint>(99){i->DataPoint(i.toDouble()/10,sin(i.toDouble()/10))})
                                graph.addSeries(series6)
                            }
                        }
                    }
                }
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
                val name = getString(resources.getIdentifier(taskNames[i], "string", packageName))
                taskText.text = name
            }
        }
    }
}
fun randomAnswerMakerForDerivAct2(i: Int): String{
    var ans="0"
    when(i) {
        1 -> {
            ans="${(0..3).random()}"
        }
        2 -> {
            when((0..1).random()){
                0-> {ans="первый"}
                1-> {ans="второй"}
            }
        }
        4 -> {
            ans="-1"
        }
        5 -> {
            ans="2"
        }
    }
    return ans
}

