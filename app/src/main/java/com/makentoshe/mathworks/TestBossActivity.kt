package com.makentoshe.mathworks

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.preference.PreferenceManager
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.PointsGraphSeries
import com.makentoshe.mathworks.ZoneCombine.factorial
import com.makentoshe.mathworks.ZoneComplex.complex
import kotlinx.android.synthetic.main.layout_act_tasks_graph.*
import kotlin.math.pow

class TestBossActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks_graph)
        headSetup.text=getString(R.string.app_name)
        subheadTask.text=getString(R.string.act_test_boss_welcome)
        progressBarTask.visibility=View.GONE
        progressBarTaskTrue.visibility=View.GONE
        editTextTask.visibility=View.GONE
        descrText.visibility=View.VISIBLE
        descrText.text=getText(R.string.act_test_boss_descr)
        taskText.visibility=View.GONE
        mathview.fontSize=40.0F
        mathview.textColor=descrText.currentTextColor
        graph.gridLabelRenderer.gridColor=mathview.textColor
        graph.gridLabelRenderer.horizontalLabelsColor=mathview.textColor
        graph.gridLabelRenderer.verticalLabelsColor=mathview.textColor
        var i=-1
        val max=10
        var score=0
        val taskTypes=arrayOf(2,2,2,1,1,2,1,1,2,1)
        val taskQuantity=arrayOf(2,2,2,0,0,3,0,0,0,0)
        var taskCorrect=Array(10){false}
        val taskTexts=arrayOf(
            R.string.act_test_boss_task_1,
            R.string.act_test_boss_task_2,
            R.string.act_modulo_3_task_4,
            R.string.act_modulo_4_task_2,
            R.string.act_test_boss_task_5,
            R.string.act_combine_boss_task_4,
            R.string.act_deriv_boss_task_1,
            R.string.act_deriv_boss_task_4,
            R.string.act_complex_3_task_2,
            R.string.act_complex_boss_task_6
        )
        var choice=""
        var a=""
        var values=Array(5){""}
        var variants = Array(5){""}
        editTextTask.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                a = editTextTask.text.toString().trim()
            }
        })
        continueButtonTask.setOnClickListener {
            if (i>=0) {
                    if (taskTypes[i]==1){
                        choice = when {
                            radioButtonTask1.isChecked -> "0"
                            radioButtonTask2.isChecked -> "1"
                            radioButtonTask3.isChecked -> "2"
                            radioButtonTask4.isChecked -> "3"
                            else -> editTextTask.toString()
                        }
                        if (choice==variants[4]) {taskCorrect[i]=true; score++; Log.d("TestBoss","score: $score")}
                        else Toast.makeText(applicationContext, getString(R.string.toast_careful), Toast.LENGTH_SHORT).show()
                    }
                    else{
                        if (a == values[0]) {taskCorrect[i]=true; score++; Log.d("TestBoss","score: $score")}
                        else Toast.makeText(applicationContext, getString(R.string.toast_careful), Toast.LENGTH_SHORT).show()
                    }
            }
            i++
            Log.d("TestBoss","$i")
            subheadTask.text=getString(R.string.act_test_boss_name)
            taskText.visibility=View.VISIBLE
            descrText.visibility=View.GONE
            progressBarTask.visibility=View.VISIBLE
            progressBarTaskTrue.visibility=View.VISIBLE
            progressBarTaskTrue.progress = score*10
            progressBarTask.progress = i*10
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            if(i==max){
                val intt=Intent(this,ActResultTest::class.java)
                intt.putExtra("integer",true)
                intt.putExtra("modulo",taskCorrect[2]&&taskCorrect[3])
                intt.putExtra("combine",taskCorrect[4]&&taskCorrect[5])
                intt.putExtra("deriv",taskCorrect[6]&&taskCorrect[7])
                intt.putExtra("complex",taskCorrect[8]&&taskCorrect[9])
                startActivity(intt)
                if (true in taskCorrect) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putBoolean("visitedTestBoss",true).apply()
                finish()
            }
            values=valueMakerForTestBoss(i)
            variants=answerMakerForTestBoss(values,i)
            taskText.text= when (taskQuantity[i]){
                2 -> {
                    getString(taskTexts[i], values[1], values[2])
                }
                3 -> {
                    getString(taskTexts[i], values[1], values[2], values[3])
                }
                else -> getString(taskTexts[i])
            }
            when (taskTypes[i]) {
                2 -> {
                    radioGroupTask.visibility = View.GONE; editTextTask.visibility = View.VISIBLE; descrText.visibility = View.GONE; taskText.visibility = View.VISIBLE
                }
                1 -> {
                    radioGroupTask.visibility = View.VISIBLE; editTextTask.visibility = View.GONE; descrText.visibility = View.GONE; taskText.visibility = View.VISIBLE
                    radioButtonTask1.text = variants[0]
                    radioButtonTask2.text = variants[1]
                    radioButtonTask3.text = variants[2]
                    radioButtonTask4.text = variants[3]
                    hideKeyboard(this)
                }
            }
            when(i){
                4->{mathview.visibility=View.VISIBLE
                mathview.latex=when(values[0]){
                    getString(R.string.act_test_boss_task_5_variant_0)->"n!"
                    getString(R.string.act_test_boss_task_5_variant_1)->"\\frac{n!}{(n-k)!}"
                    else->"\\frac{n!}{k!(n-k)!}"
                }}
                5->{mathview.visibility=View.GONE}
                6->{
                    graph.visibility=View.VISIBLE
                    graph.run {
                        viewport.isYAxisBoundsManual = true
                        viewport.setMinY(-1.0)
                        viewport.setMaxY(10.1)
                        viewport.isXAxisBoundsManual = true
                        viewport.setMinX(-0.1)
                        viewport.setMaxX(10.1)
                    }
                    graph.run{
                        removeAllSeries()
                        when(values[1]){
                            "0"->{
                                val series6=
                                    LineGraphSeries<DataPoint>(Array(240){ i-> DataPoint(i.toDouble()/50+5.2,1/(i.toDouble()/50+0.2)+5) })
                                val series7=
                                    LineGraphSeries<DataPoint>(Array(240){ i-> DataPoint(i.toDouble()/50,5-(1/((240-i).toDouble()/50+0.2))) })
                                graph.addSeries(series6)
                                graph.addSeries(series7)
                            }
                            "1"->{
                                val series6=
                                    LineGraphSeries<DataPoint>(arrayOf(DataPoint(0.0,4.0), DataPoint(5.0,4.0)))
                                val series7= LineGraphSeries<DataPoint>(arrayOf(
                                    DataPoint(5.0,6.0),
                                    DataPoint(10.0,6.0)
                                ))
                                val series8 = PointsGraphSeries<DataPoint>(arrayOf(
                                    DataPoint(5.0,4.0),
                                    DataPoint(5.0,5.0),
                                    DataPoint(5.0,6.0)
                                ))
                                series6.color = Color.BLUE
                                series7.color = Color.BLUE
                                series8.color = Color.BLUE
                                series8.size= 10.0F
                                val series9 = PointsGraphSeries<DataPoint>(arrayOf(
                                    DataPoint(5.0,4.0),
                                    DataPoint(5.0,6.0)
                                ))
                                series9.shape= PointsGraphSeries.Shape.POINT
                                series9.size= 8.0F
                                series9.color = Color.WHITE
                                graph.addSeries(series6)
                                graph.addSeries(series7)
                                graph.addSeries(series8)
                                graph.addSeries(series9)
                            }
                            "2"->{
                                val series6=
                                    LineGraphSeries<DataPoint>(Array(100){ i-> DataPoint(5.0-2.0.pow(2-i),(i%2)*2+4.toDouble()) })
                                val series8 = PointsGraphSeries<DataPoint>(arrayOf(DataPoint(5.0,5.0)))
                                series6.color = Color.BLUE
                                series8.color = Color.BLUE
                                series8.size= 10.0F
                                graph.addSeries(series6)
                                graph.addSeries(series8)
                            }
                            else->{
                                val series6 = LineGraphSeries<DataPoint>(Array(600){ i-> DataPoint(i.toDouble()/100+1.5,((i.toDouble()-300)/100).pow(2)) })
                                series6.color = Color.BLUE
                                val series8 = PointsGraphSeries<DataPoint>(arrayOf(DataPoint(4.5,0.0), DataPoint(4.5,1.0)))
                                series8.shape= PointsGraphSeries.Shape.POINT
                                series8.size= 10.0F
                                series8.color = Color.BLUE
                                val series9 = PointsGraphSeries<DataPoint>(arrayOf(DataPoint(4.5,0.0)))
                                series9.shape= PointsGraphSeries.Shape.POINT
                                series9.size= 8.0F
                                series9.color = Color.WHITE
                                graph.addSeries(series6)
                                graph.addSeries(series8)
                                graph.addSeries(series9)
                            }
                        }
                    }
                }
                7->{
                    graph.visibility=View.GONE
                    mathview.visibility=View.VISIBLE
                    mathview.latex="f(x)=${values[1].split(' ')[1]}x^{${values[1].split(' ')[0]}}"}
                8->{mathview.visibility=View.GONE
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
                        }}
                }
                9->{graph.visibility=View.GONE
                mathview.visibility=View.VISIBLE
                mathview.latex=with(Array(4){i->values[1].split(" ")[i].toInt()}){"z_1="+ complex(this[0],this[1]) +"\\\\z_2="+ complex(this[2],this[3]) }
                continueButtonTask.text=getString(R.string.check)
                }
            }
        }
    }
    private fun valueMakerForTestBoss(i: Int): Array<String>{
        var ans=""; var n1="";var n2="";var n3=""; var num1: Int; var num2: Int; var num3: Int
        when(i) {
            0->{num1=(2..9).random(); num2=(11..15).random(); ans="${num2*num1}"; n1="$num1"; n2="$num2"}
            1->{num1=(1..10).random()*10+(5..10).random(); do{num2=(2..num1).random()}while(num2%10<5); ans="${num1%num2}"; n1="$num1"; n2="$num2"}
            2->{num2=(4..9).random();num1=(num2+2..num2*num2/2).random();n1="$num1";n2="$num2";ans=num1.toString(num2)}
            3->{ans="A=1; B=1"}
            4->{val arr=Array(7){i->getString(resources.getIdentifier("act_test_boss_task_5_variant_$i", "string", packageName))}
            ans=arr[(0..2).random()]}
            5->{ num1=(1..2).random(); num2=(1..2).random(); num3=(1..2).random(); n1="$num1"; n2="$num2"; n3="$num3"; ans="${ factorial(num1 + num2 + num3) / factorial(num1) / factorial(num2) / factorial(num3) }"
            }
            6-> {num1=(0..3).random()
            ans=arrayOf("Разрыв типа «полюс»","Разрыв типа «скачок»","Существенный разрыв","Устранимый разрыв")[num1]
            n1="$num1" }
            7->{num1=(3..9).random();num2=(1..5).random();val dic = mapOf(3 to "³", 4 to "⁴", 5 to "⁵", 6 to "⁶", 7 to "⁷", 8 to "⁸", 9 to "⁹", 10 to "¹⁰")
                ans="${num2}x${dic[num1]}+C"
                n1="${num1-1} ${num1*num2}"}
            8->{ans="${(3..7).random()}"}
            9->{var num4: Int; do {num1=(-10..10).random(); num2=(-10..10).random(); num3=(-10..10).random(); num4=(-10..10).random()}while(num1-num3==0 || num2-num4==0); n1="$num1 $num2 $num3 $num4"
                ans=if (num1-num3>0&&num2-num4>0)"(0; π/2)" else if (num1-num3<0&&num2-num4>0) "(π/2; π)" else if (num1-num3<0&&num2-num4<0) "(π; 3π/2)" else "(3π/2; 2π)"}
        }
        Log.d("TestBoss","valueMakerForTestBoss($i)=$ans $n1 $n2 $n3")
        return arrayOf(ans,n1,n2,n3,"")
    }
    private fun answerMakerForTestBoss(values: Array<String>, i: Int): Array<String> {
        val ans=values[0]
        val b= MutableList(4){""}
        b[0]=ans
        when(i){
            0->{}
            1->{}
            2->{}
            3->{b[1]="A=1; B=0"; b[2]="A=0; B=1"; b[3]="A=0; B=0"}
            4->{val arr=Array(7){i->getString(resources.getIdentifier("act_test_boss_task_5_variant_$i", "string", packageName))}
                do {b[1]=arr[(0..6).random()]}while(b[1]==b[0])
                do {b[2]=arr[(0..6).random()]}while(b[2]==b[1]||b[2]==b[0])
                do {b[3]=arr[(0..6).random()]}while(b[3]==b[2]||b[3]==b[1]||b[3]==b[0])
            }
            5->{}
            6->{
                do{b[1]=arrayOf("Разрыв типа «полюс»","Разрыв типа «скачок»","Существенный разрыв","Устранимый разрыв")[(0..3).random()]}while(b[1]==b[0])
                do{b[2]=arrayOf("Разрыв типа «полюс»","Разрыв типа «скачок»","Существенный разрыв","Устранимый разрыв")[(0..3).random()]}while(b[2]==b[0]||b[2]==b[1])
                do{b[3]=arrayOf("Разрыв типа «полюс»","Разрыв типа «скачок»","Существенный разрыв","Устранимый разрыв")[(0..3).random()]}while(b[3]==b[0]||b[3]==b[1]||b[3]==b[2])
            }
            7-> {
                val dic = mapOf(2 to "²", 3 to "³", 4 to "⁴", 5 to "⁵", 6 to "⁶", 7 to "⁷", 8 to "⁸", 9 to "⁹", 10 to "¹⁰")
                b[1]="${values[1].split(' ')[1].toInt()}x${dic[values[1].split(' ')[0].toInt()]}+C"
                b[2]="${values[1].split(' ')[1].toInt()/(values[1].split(' ')[0].toInt()+1)}x${dic[values[1].split(' ')[0].toInt()]}+C"
                b[3]="${values[1].split(' ')[1].toInt()}x${dic[values[1].split(' ')[0].toInt()+1]}+C"
            }
            8->{}
            9->{b[0]="(0; π/2)";b[1]="(π/2; π)";b[2]="(π; 3π/2)";b[3]="(3π/2; 2π)"}
        }
        b.shuffle()
        Log.d("TestBoss","answerMakerForTestBoss($i)=${b[0]} ${b[1]} ${b[2]} ${b[3]}")
        return arrayOf(b[0],b[1],b[2],b[3],"${b.indexOf(ans)}")
    }

}
