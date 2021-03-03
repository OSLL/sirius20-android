package com.makentoshe.mathworks.ZoneDeriv

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.layout_act_tasks.*
import java.util.Collections.shuffle


class DerivAct4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headSetup.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
        mathview.visibility= View.VISIBLE
        mathview.fontSize=40.0F
        val formulas=mapOf(
                0 to "\\\\(1)\\int f(x)dx=F(x)+C, C=const\\\\(2)\\int f\\prime(x)dx=f(x)+c\\\\(\\int f(x)dx)\\prime=f(x)",
                3 to "\\\\(1)\\int_a^b f(x)dx=F(b)-F(a)\\\\(2)\\int_a^b f(x)dx=\\sum_{i=1}^n f(c_i)\\Delta x_i;\\\\x_0=a;x_n=b;\\\\c_i\\in[x_{i-1};x_i];\\\\\\Delta x_i=x_i-x_{i-1}\\\\(3)\\int_{a}^{b}f(x)dx+\\int_{b}^{c}f(x)dx=\\\\=\\int_{a}^{c}f(x)dx; a<b<c.\\\\(4)\\int_b^a f(x)dx=-\\int_a^b f(x)dx"
        )
        mathview.latex=formulas[0].toString()
        var step =0
        var score =0
        val max =4
        var i=0
        val taskTypes=arrayOf(0, 1, 1, 0, 1, 2)
        val taskNames=arrayOf("act_deriv_4_descr_1", "act_deriv_4_task_1", "act_deriv_4_task_1", "act_deriv_4_descr_2", "act_deriv_4_task_3", "act_deriv_4_task_4")
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
            taskImage.visibility= View.GONE
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
                intt.putExtra("act", 3)
                startActivity(intt)
            }
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) values = valueMakerForDerivAct4(i)
                when(i) {
                    1 -> {
                        mathview.latex="f(x)=${values[2]}x^{${values[1]}}\\\\\\int x^pdx=\\frac{x^{p+1}}{p+1}+C"
                    }
                    2 -> {
                        mathview.latex="\\\\f(x)=${values[1]}\\\\(sin x)\\prime=cos x\\\\(cos x)\\prime=-sin x\\\\(Cx)'=C; C=const"
                    }
                    3 -> {
                        mathview.latex = formulas[3].toString()
                    }
                    4 -> {
                        mathview.latex="\\\\S=F(c)-F(b);\\\\\\int f(x)dx=F(x)+C\\\\\\int_a^bf(x)dx=${values[1]}\\\\\\int_a^cf(x)dx=${values[2]}"
                    }
                    5 -> {
                        mathview.latex = "\\\\Y=\\int_b^af(x)dx;\\\\f(x)=${values[3]}x;a=${values[1]};b=${values[2]};\\\\\\int px\\; dx=\\frac{p}{2}x^2+C"
                    }
                }
                variants = answerMakerForDerivAct4(values, i)
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
                if (step <= max) {
                    progressBarTask.progress = ((step.toDouble() / max.toDouble()) * 100.0).toInt()
                }
                descrText.text = getText((resources.getIdentifier(taskNames[i], "string", packageName)))
                taskText.text = getString(resources.getIdentifier(taskNames[i], "string", packageName))
            }
        }
    }
}
fun valueMakerForDerivAct4(i: Int): Array<String>{
    var ans="\\"; val num1: Int; val num2: Int; val num3: Int; var n1=""; var n2=""; var n3=""
    when(i) {
        1 -> {num1=(3..9).random();num2=(1..5).random();val dic = mapOf(3 to "³", 4 to "⁴", 5 to "⁵", 6 to "⁶", 7 to "⁷", 8 to "⁸", 9 to "⁹", 10 to "¹⁰")
            ans="${num2}x${dic[num1]}+C";n1="${num1-1}";n2="${num1*num2}"
            n3="$n1 $n2"
        }
        2 -> {
            when((0..3).random()){
                0->{ans="sin x+x+C";n1="cos x+1"; n2="-sin x+x"; n3="-cos x+1"}
                1->{ans="cos x+x+C"; n1="-sin x+1"; n2="-cos x+1";n3="sin x+x"}
                2->{ans="-sin x+x+C";n1="-cos x+1"; n2="sin x+x"; n3="cos x+1"}
                3->{ans="-cos x+x+C";n1="sin x+1"; n2="cos x+1"; n3="-sin x+x"}
            }
        }
        4 -> {
            num1=(2..100).random(); num2=(num1+1..101).random(); ans="${num2-num1}";n1="$num1";n2="$num2"
        }
        5 -> {num1=(1..10).random();num2=(num1+1..11).random();num3=when((num2-num1)%2){0->(1..10).random();else->(1..5).random()*2};ans="${-(num3*(num2*num2-num1*num1))/2}";n1="$num1";n2="$num2";n3="$num3"
        }
    }
    return arrayOf(ans, n1, n2, n3)
}
fun answerMakerForDerivAct4(values: Array<String>, i: Int): Array<String> {
    val ans=values[0]
    val b= MutableList<String>(4){""}
    b[0]=ans
    when(i){
        1 -> {
            val dic = mapOf(2 to "²", 3 to "³", 4 to "⁴", 5 to "⁵", 6 to "⁶", 7 to "⁷", 8 to "⁸", 9 to "⁹", 10 to "¹⁰")
            b[1]="${values[2].toInt()}x${dic[values[1].toInt()]}+C"
            b[2]="${values[2].toInt()/(values[1].toInt()+1)}x${dic[values[1].toInt()]}+C"
            b[3]="${values[2].toInt()}x${dic[values[1].toInt()+1]}+C"
        }
        2 -> {
            b[1]=values[1]+"+C"
            b[2]=values[2]+"+C"
            b[3]=values[3]+"+C"
        }
        4 -> {
            b[1]=values[2]
            b[2]="${values[1].toInt()*values[2].toInt()}"
            b[3]="${values[1].toInt()+values[2].toInt()}"
        }
        5 -> {}
    }
    shuffle(b)
    val p="${b.indexOf(ans)}"
    return arrayOf(b[0], b[1], b[2], b[3], p)
}