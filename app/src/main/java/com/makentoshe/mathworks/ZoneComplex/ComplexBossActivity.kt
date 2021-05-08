package com.makentoshe.mathworks.ZoneComplex

import android.content.Context
import android.content.Intent
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
import com.makentoshe.mathworks.*
import kotlinx.android.synthetic.main.layout_act_tasks_graph.*

class ComplexBossActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks_graph)
        headSetup.text = intent.getStringExtra("zone")
        subheadTask.text = intent.getStringExtra("act")
        mathview.visibility = View.GONE
        mathview.fontSize = 40.0F
        mathview.textColor = descrText.currentTextColor
        hearts.visibility=View.VISIBLE
        val prefs = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)
        var lives= prefs.getInt("lives", 0)
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
        }
        var step = 0
        var score = 0
        val max = 6
        var i = 0
        val taskTypes = arrayOf(0, 2,2, 1,1,2,1)
        val taskNames = arrayOf("boss_descr","act_complex_3_task_2","act_complex_boss_task_2","act_complex_2_task_3","act_complex_3_task_3","act_complex_boss_task_5","act_complex_boss_task_6")
        descrText.text=getString(R.string.boss_descr,headSetup.text)
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
                    if (choice == variants[4]) score++ else  {
                        lives--
                        prefs.edit().putInt("lives",lives).apply()
                        actionOnService(applicationContext, Actions.START)
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
                if (taskTypes[i] == 2) {
                    if (a == values[0]) score++ else  {
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
                step++
            }
            i++
            values= valueMakerForComplexBoss(i)
            variants= answerMakerForComplexBoss(values,i)
            graph.run{
                when(i){
                    1->{ visibility=View.VISIBLE
                        when(values[0]){
                            "3"->{addSeries(LineGraphSeries(arrayOf(DataPoint(-4.33,2.5), DataPoint(0.0,-5.0), DataPoint(4.33,2.5))).also{it.color=mathview.textColor})
                                addSeries(LineGraphSeries(arrayOf(DataPoint(-4.33,2.5), DataPoint(4.33,2.5))).also{it.color=mathview.textColor})}
                            "4"->{addSeries(LineGraphSeries(arrayOf(DataPoint(-2.0,-1.0), DataPoint(-1.0,2.0), DataPoint(2.0,1.0))).also{it.color=mathview.textColor})
                                addSeries(LineGraphSeries(arrayOf(DataPoint(-2.0,-1.0), DataPoint(1.0,-2.0), DataPoint(2.0,1.0))).also{it.color=mathview.textColor})}
                            "5"->{addSeries(LineGraphSeries(arrayOf(DataPoint(-4.045,-2.939), DataPoint(-4.045,2.939), DataPoint(1.545,4.755), DataPoint(5.0,0.0))).also{it.color=mathview.textColor})
                                addSeries(LineGraphSeries(arrayOf(DataPoint(-4.045,-2.939), DataPoint(1.545,-4.755), DataPoint(5.0,0.0))).also{it.color=mathview.textColor})}
                            "6"->{addSeries(LineGraphSeries(arrayOf(DataPoint(-2.897,-0.776), DataPoint(-2.121,2.121), DataPoint(0.776,2.897), DataPoint(2.897,0.776))).also{it.color=mathview.textColor})
                                addSeries(LineGraphSeries(arrayOf(DataPoint(-2.897,-0.776), DataPoint(-0.776,-2.897), DataPoint(2.121,-2.121), DataPoint(2.897,0.776))).also{it.color=mathview.textColor})}
                            "7"->{addSeries(LineGraphSeries(arrayOf(DataPoint(-3.9,-0.89), DataPoint(-3.127,2.494), DataPoint(0.0,4.0), DataPoint(3.127,2.494), DataPoint(3.9,-0.89))).also{it.color=mathview.textColor})
                                addSeries(LineGraphSeries(arrayOf(DataPoint(-3.9,-0.89), DataPoint(-1.736,-3.603), DataPoint(1.736,-3.603), DataPoint(3.9,-0.89))).also{it.color=mathview.textColor})}
                        }
                    }
                    2->{visibility=View.GONE}
                    4->{visibility= View.VISIBLE
                        removeAllSeries()
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
                    5->{visibility= View.GONE
                    }
                    6->{}
                }
            }
            mathview.latex=when(i){
                2->complex(values[1].split(' ')[0].toInt(),values[1].split(' ')[1].toInt())
                3->when(values[0]){
                    complex(3,4)->"(2+i)^2"
                    complex(-1,0)->"(\\frac{1}{2}+\\frac{\\sqrt3}{2}i)^3"
                    complex(256,0)->"(\\sqrt2+\\sqrt2i)^8"
                    complex(0,27)->"(\\frac{3\\sqrt3}{2}+\\frac{3}{2}i)^3"
                    complex(-3,4)->"(1+2i)^2"
                    else->"(1+i)^3"}
                4->""
                5->with(values[1].split(' ')){"z_1\\cdot z_2\\\\z_1="+complex(this[0].toInt(),this[1].toInt())+"\\\\z_2="+complex(this[2].toInt(),this[3].toInt())}
                6->with(Array<Int>(4){i->values[1].split(" ")[i].toInt()}){"z_1="+complex(this[0],this[1])+"\\\\z_2="+complex(this[2],this[3])}
                else->""
            }
            mathview.visibility=when(i){
                1-> View.GONE
                2-> View.VISIBLE
                4-> View.GONE
                5-> View.VISIBLE
                else->mathview.visibility
            }
            if (i == taskTypes.size) {
                val intt = Intent(this, ActResult::class.java)
                intt.putExtra("score", score)
                intt.putExtra("max", max)
                intt.putExtra("zone", "complex")
                intt.putExtra("act", 3)
                if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusComplexBoss", 0) < (score.toDouble() / max.toDouble() * 100.0).toInt()) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusComplexBoss", (score.toDouble() / max.toDouble() * 100.0).toInt()).apply()
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
    private fun actionOnService(context: Context,action: Actions) {
        if (getServiceState(context) == ServiceState.STOPPED && action == Actions.STOP) return
        Intent(context, EndlessService::class.java).also {
            it.action = action.name
            startService(it)
        }
    }
}
fun valueMakerForComplexBoss(i: Int): Array<String>{
    var ans=""; var n1="";var num1: Int; var num2: Int; var num3: Int
    when(i) {
        1->{ans="${(3..7).random()}"}
        2 -> {num1=(-10..10).random();num2=(-10..10).random();n1="$num1 $num2";ans="${num1*num1+num2*num2}"
        }
        3-> {ans=when((0..5).random()){
            0->complex(3,4)
            1->complex(-1,0)
            2->complex(256,0)
            3->complex(0,27)
            4->complex(-3,4)
            else->complex(-2,2)
        }}
        4->{ans=arrayOf("а) да, б) да","а) да, б) нет","а) нет, б) нет","а) нет, б) да")[(0..3).random()]}
        5->{
            num1=(0..5).random()
            num2=(1..5).random()
            num3=(0..5).random()
            val num4 = (1..5).random()
            n1="$num1 $num2 $num3 $num4"
            ans="${num2*num3+num1*num4}"}
        6->{var num4: Int; do {num1=(-10..10).random(); num2=(-10..10).random(); num3=(-10..10).random(); num4=(-10..10).random()}while(num1-num3==0 || num2-num4==0); n1="$num1 $num2 $num3 $num4"
        ans=if (num1-num3>0&&num2-num4>0)"(0; π/2)" else if (num1-num3<0&&num2-num4>0) "(π/2; π)" else if (num1-num3<0&&num2-num4<0) "(π; 3π/2)" else "(3π/2; 2π)"}
    }
    Log.d("ok",ans)
    return arrayOf(ans,n1)
}
fun answerMakerForComplexBoss(values: Array<String>, i: Int): Array<String> {
    val ans=values[0]
    val b= MutableList(4){""}
    b[0]=ans
    when(i){
        1->{}
        2->{}
        3->when(ans){
            complex(3,4)->{b[1]=complex(4,3);b[2]=complex(10,5);b[3]=complex(3,-4)}
            complex(-1,0)->{b[1]=complex(0,1);b[2]=complex(1,-1);b[3]=complex(-1,-1)}
            complex(256,0)->{b[1]=complex(0,16);b[2]=complex(-64,0);b[3]=complex(4,0)}
            complex(0,27)->{b[1]=complex(27,0);b[2]=complex(0,3);b[3]=complex(3,0)}
            complex(-3,4)->{b[1]=complex(-4,3);b[2]=complex(2,4);b[3]=complex(3,4)}
            complex(-2,2)->{b[1]=complex(-1,1);b[2]=complex(-3,3);b[3]=complex(-4,4)}
        }
        4->{b[0]="а) да, б) да";b[1]="а) да, б) нет";b[2]="а) нет, б) нет";b[3]="а) нет, б) да"}
        5->{}
        6->{b[0]="(0; π/2)";b[1]="(π/2; π)";b[2]="(π; 3π/2)";b[3]="(3π/2; 2π)"}
    }
    b.shuffle()
    return arrayOf(b[0],b[1],b[2],b[3],"${b.indexOf(ans)}")
}
