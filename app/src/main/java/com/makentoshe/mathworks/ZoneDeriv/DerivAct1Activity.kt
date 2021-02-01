package com.makentoshe.mathworks.ZoneDeriv

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.layout_act_tasks.*

import java.util.Collections.shuffle
import kotlin.math.pow


class DerivAct1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headTask.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
        mathview.visibility= View.VISIBLE
        mathview.fontSize=40.0F
        val formulas=mapOf(
            0 to "(1)\\lim_{n \\to \\infty} x_n=2\\\\(2)\\lim_{x \\to a+} f(x)\\\\(3)\\lim_{x \\to a-} f(x)\\\\(4)\\lim_{x \\to 0}\\frac{\\sin{x}}{x}\\\\(5)\\lim_{x \\to \\infty}(1+\\frac{1}{x})^x",
            3 to "(1)\\lim_{x \\to a}f(x)+\\lim_{x \\to a}g(x)=\\\\\\lim_{x \\to a}(f(x)+g(x))\\\\(2)\\lim_{x \\to a}f(x)-\\lim_{x \\to a}g(x)=\\\\\\lim_{x \\to a}(f(x)-g(x))\\\\(3)\\lim_{x \\to a}f(x)\\cdot \\lim_{x \\to a}g(x)=\\\\\\lim_{x \\to a}(f(x)g(x))\\\\(4)\\frac{\\lim_{x \\to a}f(x)}{\\lim_{x \\to a}g(x)}=\\lim_{x \\to a}\\frac{f(x)}{g(x)}\\\\(5)\\lim _{{n\\to a }}f(x)=\\lim _{{n\\to a }}g(x)=A\\Rightarrow\\\\\\lim _{{n\\to a }}h(x)=A,\\\\f(x)\\leq h(x)\\leq g(x)",
            5 to "(1)\\lim_{x \\to 0}\\frac{\\sin{x}}{x}\\\\(2)\\lim_{x \\to \\infty}(1+\\frac{1}{x})^x"
        )
        mathview.latex=formulas[0].toString()
        var step =0
        var score =0
        val max =4
        var i=0
        val taskTypes=arrayOf(0,1,2,0,2,1)
        val taskNames=arrayOf("act_deriv_1_descr_1","act_deriv_1_task_1","act_deriv_1_task_1","act_deriv_1_descr_2","act_deriv_1_task_3","act_deriv_1_task_4")
        descrText.text=getText(resources.getIdentifier(taskNames[0], "string", packageName))
        var values=Array<String>(4) { "" }
        var variants=Array<String>(5) { "" }
        var choice: String
        var a=""
        taskText.visibility = View.GONE; descrText.visibility= View.VISIBLE
        radioGroupTask.visibility = View.GONE; editTextTask.visibility= View.GONE
        editTextTask.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { a=editTextTask.text.toString().trim() }
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
                intt.putExtra("act", 0)
                startActivity(intt)
            }
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) values = valueMakerForDerivAct1(i)
                mathview.latex=when(i){
                    1->{"\\lim_{x \\to "+values[2]+"}x^"+values[1]}
                    2->{"\\lim_{x \\to \\infty}\\frac{"+values[2]+"x+${(1..99).random()}}{"+values[1]+"x-${(1..99).random()}}"}
                    3->{formulas[3].toString()}
                    4->{"\\lim_{x \\to "+values[1]+"}"+values[2]+"x^2+\\lim_{x \\to "+values[1]+"}"+values[3]+"x"}
                    5->{formulas[5].toString()}
                    else->""
                }
                variants = answerMakerForDerivAct1(values,i)
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
fun valueMakerForDerivAct1(i: Int): Array<String>{
    var ans="0"; val num1: Int; val num2: Int; val num3: Int; var n1=""; var n2=""; var n3=""
    when(i) {
        1 -> {num1=(2..3).random();num2=(4..10).random();n1="$num1";n2="$num2"; ans="${num2.toDouble().pow(num1).toInt()}"}
        2 -> {num1=(2..10).random();num2=(2..10).random()*num1;ans="${num2/num1}";n1="$num1";n2="$num2"}
        4 -> {num1=(2..10).random(); num2=(2..4).random(); num3=(2..10).random(); n1="$num1"; n2="$num2"; n3="$num3"; ans="${num1*num2*num1+num1*num3}"}
        5 -> {ans= "1; e"}
    }
    return arrayOf(ans,n1,n2,n3)
}
fun answerMakerForDerivAct1(values: Array<String>, i: Int): Array<String> {
    val ans=values[0]
    val b= MutableList<String>(4){""}
    b[0]=ans
    when(i){
        1->{b[1]=values[1];b[2]=values[2];b[3]="∞"}
        2->{}
        4->{}
        5->{b[1]="1; ∞"; b[2]="0; 1"; b[3]="0; e"}
    }
    shuffle(b)
    val p="${b.indexOf(ans)}"
    return arrayOf(b[0],b[1],b[2],b[3],p)
}


