package com.makentoshe.mathworks.ZoneDeriv

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.preference.PreferenceManager
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.PointsGraphSeries
import com.makentoshe.mathworks.*
import kotlinx.android.synthetic.main.layout_act_tasks.*
import kotlinx.android.synthetic.main.layout_act_tasks_graph.*
import kotlinx.android.synthetic.main.layout_act_tasks_graph.continueButtonTask
import kotlinx.android.synthetic.main.layout_act_tasks_graph.descrText
import kotlinx.android.synthetic.main.layout_act_tasks_graph.editTextTask
import kotlinx.android.synthetic.main.layout_act_tasks_graph.headSetup
import kotlinx.android.synthetic.main.layout_act_tasks_graph.heart1
import kotlinx.android.synthetic.main.layout_act_tasks_graph.heart2
import kotlinx.android.synthetic.main.layout_act_tasks_graph.heart3
import kotlinx.android.synthetic.main.layout_act_tasks_graph.hearts
import kotlinx.android.synthetic.main.layout_act_tasks_graph.mathview
import kotlinx.android.synthetic.main.layout_act_tasks_graph.progressBarTask
import kotlinx.android.synthetic.main.layout_act_tasks_graph.progressBarTaskTrue
import kotlinx.android.synthetic.main.layout_act_tasks_graph.radioButtonTask1
import kotlinx.android.synthetic.main.layout_act_tasks_graph.radioButtonTask2
import kotlinx.android.synthetic.main.layout_act_tasks_graph.radioButtonTask3
import kotlinx.android.synthetic.main.layout_act_tasks_graph.radioButtonTask4
import kotlinx.android.synthetic.main.layout_act_tasks_graph.radioGroupTask
import kotlinx.android.synthetic.main.layout_act_tasks_graph.subheadTask
import kotlinx.android.synthetic.main.layout_act_tasks_graph.taskImage
import kotlinx.android.synthetic.main.layout_act_tasks_graph.taskText
import kotlin.math.*

class DerivBossActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks_graph)
        val prefs = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)
        var lives= prefs.getInt("lives", 0)
        headSetup.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
        mathview.fontSize=40.0F
        mathview.textColor=descrText.currentTextColor
        hearts.visibility=View.VISIBLE
        graph.gridLabelRenderer.gridColor=mathview.textColor
        graph.gridLabelRenderer.horizontalLabelsColor=mathview.textColor
        graph.gridLabelRenderer.verticalLabelsColor=mathview.textColor
        var step =0
        var score =0
        val max =6
        var i=0
        val taskTypes=arrayOf(0,1,2,2,1,2,1)
        val taskNames=arrayOf("boss_descr", "act_deriv_boss_task_1", "act_deriv_boss_task_2", "act_deriv_boss_task_3",  "act_deriv_boss_task_4","act_deriv_boss_task_5","act_deriv_boss_task_6")
        descrText.text=getString(R.string.boss_descr,headSetup.text)
        var values=Array(2) { "" }
        var variants=Array(5) { "" }
        var choice: String
        var a=""
        taskText.visibility = View.GONE; descrText.visibility= View.VISIBLE
        radioGroupTask.visibility = View.GONE; editTextTask.visibility= View.GONE
        graph.visibility= View.GONE
        editTextTask.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                a = editTextTask.text.toString().trim()
            }
        })
        hearts.setOnClickListener {
            Log.d("BOSS","Lives is now ${prefs.getInt("lives",-1)}")
        }
        continueButtonTask.setOnClickListener {
            lives= prefs.getInt("lives", 0)
            when(lives){
                3->{heart1.setImageResource(R.drawable.ic_favorite_24px)
                    heart2.setImageResource(R.drawable.ic_favorite_24px)
                    heart3.setImageResource(R.drawable.ic_favorite_24px)
                }
                2->{heart1.setImageResource(R.drawable.ic_favorite_24px)
                    heart2.setImageResource(R.drawable.ic_favorite_24px)
                    heart3.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                }
                1->{heart1.setImageResource(R.drawable.ic_favorite_24px)
                    heart2.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                    heart3.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                }
                0->{heart1.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                    heart2.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                    heart3.setImageResource(R.drawable.ic_favorite_border_black_18dp)}
            }
            taskImage.visibility= View.GONE
            if (taskTypes[i]!=0) {
                if (taskTypes[i]==1){
                    if (radioButtonTask1.isChecked) choice="0"
                    else if (radioButtonTask2.isChecked) choice="1"
                    else if (radioButtonTask3.isChecked) choice="2"
                    else if (radioButtonTask4.isChecked) choice="3"
                    else choice="4"
                    if (choice==variants[4]) score++ else {
                        lives--
                        prefs.edit().putInt("lives",lives).apply()
                        actionOnService(applicationContext,Actions.START)
                        when(lives){
                            3->{heart1.setImageResource(R.drawable.ic_favorite_24px)
                                heart2.setImageResource(R.drawable.ic_favorite_24px)
                                heart3.setImageResource(R.drawable.ic_favorite_24px)
                            }
                            2->{heart1.setImageResource(R.drawable.ic_favorite_24px)
                                heart2.setImageResource(R.drawable.ic_favorite_24px)
                                heart3.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                            }
                            1->{heart1.setImageResource(R.drawable.ic_favorite_24px)
                                heart2.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                                heart3.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                            }
                            0->{heart1.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                                heart2.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                                heart3.setImageResource(R.drawable.ic_favorite_border_black_18dp)}
                        }
                        }
                }
                if (taskTypes[i]==2){
                    if (a==values[0]) score++ else  {
                        lives--
                        prefs.edit().putInt("lives",lives).apply()
                        actionOnService(applicationContext,Actions.START)
                        when(lives){
                            3->{heart1.setImageResource(R.drawable.ic_favorite_24px)
                                heart2.setImageResource(R.drawable.ic_favorite_24px)
                                heart3.setImageResource(R.drawable.ic_favorite_24px)
                            }
                            2->{heart1.setImageResource(R.drawable.ic_favorite_24px)
                                heart2.setImageResource(R.drawable.ic_favorite_24px)
                                heart3.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                            }
                            1->{heart1.setImageResource(R.drawable.ic_favorite_24px)
                                heart2.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                                heart3.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                            }
                            0->{heart1.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                                heart2.setImageResource(R.drawable.ic_favorite_border_black_18dp)
                                heart3.setImageResource(R.drawable.ic_favorite_border_black_18dp)}
                        }
                        }
                }
                if (prefs.getInt("lives",-1)==0){finish();val intt_=(Intent(this, ActFailure::class.java)); intt_.putExtra("zone", "deriv");startActivity(intt_)}
                step++
            }
            i++
            if(i<taskTypes.size) if(taskTypes[i]!=2) hideKeyboard(this)
            if (i==taskTypes.size) {
                val intt = Intent(this, ActResult::class.java)
                intt.putExtra("score", score)
                intt.putExtra("max", max)
                intt.putExtra("zone", "deriv")
                intt.putExtra("act", 5)
                if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusDerivBoss",0)<(score.toDouble()/max.toDouble()*100.0).toInt()) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusDerivBoss",(score.toDouble()/max.toDouble()*100.0).toInt()).apply()
                //if ((score.toDouble()/max.toDouble()*100.0).toInt()>=50 && PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusComplex",-1)<0) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusComplex",0).apply()
                startActivity(intt)
            }
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) values = valueMakerForDerivActBoss(i)
                when(i){
                    1->{
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
                    2->{
                        graph.visibility=View.GONE
                        mathview.visibility=View.VISIBLE
                        mathview.latex="\\lim_{x \\to "+values[1].split(' ')[0]+"}"+values[1].split(' ')[1]+"x^2+\\lim_{x \\to "+values[1].split(' ')[0]+"}"+values[1].split(' ')[2]+"x"
                    }
                    3->{mathview.visibility= View.GONE
                        graph.visibility= View.VISIBLE
                        graph.run {
                            removeAllSeries()
                            viewport.setMinY(-1.1)
                            viewport.setMaxY(10.1)
                            viewport.setMinX(-5.1)
                            viewport.setMaxX(5.1)
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
                    4->{
                        graph.visibility=View.GONE
                        mathview.visibility=View.VISIBLE
                        mathview.latex="f(x)=${values[1].split(' ')[1]}x^{${values[1].split(' ')[0]}}"
                    }
                    5->{
                        mathview.latex = "\\\\Y=\\int_b^af(x)dx;\\\\f(x)=${values[1].split(' ')[2]}x;a=${values[1].split(' ')[0]};b=${values[1].split(' ')[1]}"
                    }
                    6->{mathview.visibility=View.GONE
                        graph.visibility=View.VISIBLE
                        graph.removeAllSeries()
                        graph.run{
                            viewport.isYAxisBoundsManual = true
                            viewport.setMinY(-5.1)
                            viewport.setMaxY(5.1)
                            viewport.isXAxisBoundsManual = true
                            viewport.setMinX(-5.1)
                            viewport.setMaxX(5.1)
                        }
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
                            "ln|χ|"->{
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
                        }}
                    }
                }
                variants = answerMakerForDerivActBoss(values,i)
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
    private fun actionOnService(context: Context,action: Actions) {
        if (getServiceState(context) == ServiceState.STOPPED && action == Actions.STOP) return
        Intent(this, EndlessService::class.java).also {
            it.action = action.name
            startService(it)
        }
    }
}


fun valueMakerForDerivActBoss(i: Int): Array<String>{
    var ans="\\"; val num1: Int; val num2: Int; val num3: Int; var pas=""
    when(i) {
        1 -> {num1=(0..3).random()
            ans=arrayOf("Разрыв типа «полюс»","Разрыв типа «скачок»","Существенный разрыв","Устранимый разрыв")[num1]
            pas="$num1"
        }
        2 -> {num1=(2..10).random(); num2=(2..4).random(); num3=(2..10).random(); ans="${num1*num2*num1+num1*num3}"; pas="$num1 $num2 $num3"}
        3 -> {ans="${(0..3).random()}"
        }
        4 -> {num1=(3..9).random();num2=(1..5).random();val dic = mapOf(3 to "³", 4 to "⁴", 5 to "⁵", 6 to "⁶", 7 to "⁷", 8 to "⁸", 9 to "⁹", 10 to "¹⁰")
            ans="${num2}x${dic[num1]}+C"
            pas="${num1-1} ${num1*num2}"}
        5 -> {num1=(1..10).random();num2=(num1+1..11).random();num3=when((num2-num1)%2){0->(1..10).random();else->(1..5).random()*2};ans="${-(num3*(num2*num2-num1*num1))/2}";pas="$num1 $num2 $num3"}
        6 -> {val arr=arrayOf("χ","χ²","sin χ+χ","cos χ+χ","ln|χ|","C","eᵡ")
            ans=arr[(0..6).random()]}
    }
    return arrayOf(ans,pas)
}
fun answerMakerForDerivActBoss(values: Array<String>, i: Int): Array<String> {
    val ans=values[0]
    val b= MutableList(4){""}
    b[0]=ans
    when(i) {
        1 -> {
            do{b[1]=arrayOf("Разрыв типа «полюс»","Разрыв типа «скачок»","Существенный разрыв","Устранимый разрыв")[(0..3).random()]}while(b[1]==b[0])
            do{b[2]=arrayOf("Разрыв типа «полюс»","Разрыв типа «скачок»","Существенный разрыв","Устранимый разрыв")[(0..3).random()]}while(b[2]==b[0]||b[2]==b[1])
            do{b[3]=arrayOf("Разрыв типа «полюс»","Разрыв типа «скачок»","Существенный разрыв","Устранимый разрыв")[(0..3).random()]}while(b[3]==b[0]||b[3]==b[1]||b[3]==b[2])
        }
        2 -> {}
        3 -> {}
        4 -> {val dic = mapOf(2 to "²", 3 to "³", 4 to "⁴", 5 to "⁵", 6 to "⁶", 7 to "⁷", 8 to "⁸", 9 to "⁹", 10 to "¹⁰")
            b[1]="${values[1].split(' ')[1].toInt()}x${dic[values[1].split(' ')[0].toInt()]}+C"
            b[2]="${values[1].split(' ')[1].toInt()/(values[1].split(' ')[0].toInt()+1)}x${dic[values[1].split(' ')[0].toInt()]}+C"
            b[3]="${values[1].split(' ')[1].toInt()}x${dic[values[1].split(' ')[0].toInt()+1]}+C"
        }
        5 -> {}
        6 -> {val arr=arrayOf("χ","χ²","sin χ+χ","cos χ+χ","ln|χ|","C","eᵡ","eⁱᵡ","tan χ")
            do{b[1]=arr[(0..8).random()]}while(b[1]==b[0]);do{b[2]=arr[(0..8).random()]}while(b[2]==b[1]||b[2]==b[0]);do{b[3]=arr[(0..8).random()]}while(b[3]==b[2]||b[3]==b[1]||b[3]==b[0])}
    }
    b.shuffle()
    val p="${b.indexOf(ans)}"
    return arrayOf(b[0],b[1],b[2],b[3],p)
}
