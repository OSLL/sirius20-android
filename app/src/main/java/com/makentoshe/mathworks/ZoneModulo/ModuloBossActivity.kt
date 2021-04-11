package com.makentoshe.mathworks.ZoneModulo

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.makentoshe.mathworks.*
import kotlinx.android.synthetic.main.layout_act_tasks.*
import java.util.*

class ModuloBossActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headSetup.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
        val prefs = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)
        var lives= prefs.getInt("lives", 0)
        var step =0
        var score =0
        val max =5
        hearts.visibility=View.VISIBLE
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
        progressBarTask.max=100
        progressBarTaskTrue.max=100
        var i=0
        val taskTypes=arrayOf(0,2,2,1,2,1)
        val taskNames=arrayOf("boss_descr","act_modulo_boss_task_1","act_modulo_boss_task_2","act_modulo_boss_task_3","act_modulo_boss_task_4","act_modulo_boss_task_5")
        descrText.text=getString(R.string.boss_descr,headSetup.text)
        var values=arrayOf("","","","")
        var variants=arrayOf("","","","","")
        var choice: String
        var a=""
        taskText.visibility = View.GONE; descrText.visibility= View.VISIBLE
        radioGroupTask.visibility = View.GONE; editTextTask.visibility= View.GONE
        editTextTask.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                a=editTextTask.text.toString().trim()
            }

        })
        editTextTask.inputType=InputType.TYPE_NUMBER_FLAG_DECIMAL
        hearts.setOnClickListener {
            Log.d("BOSS","Lives is now ${prefs.getInt("lives",-1)}")
        }
        continueButtonTask.setOnClickListener {
            taskImage.visibility= View.GONE
            if (taskTypes[i]!=0) {
                if (taskTypes[i]==1){
                    if (radioButtonTask1.isChecked) choice="0"
                    else if (radioButtonTask2.isChecked) choice="1"
                    else if (radioButtonTask3.isChecked) choice="2"
                    else if (radioButtonTask4.isChecked) choice="3"
                    else choice=editTextTask.toString()
                    if (choice==variants[4]) score++ else  {
                        prefs.edit().putInt("lives",lives-1).apply()
                        lives= prefs.getInt("lives", 0)
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
                        }}

                }
                if (taskTypes[i]==2){
                    if (a.toLowerCase(Locale.ROOT) == values[0]) score++ else  {
                        prefs.edit().putInt("lives",lives-1).apply()
                        lives= prefs.getInt("lives", 0)
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
                        }}

                }

                step++
                if (prefs.getInt("lives",-1)==0){finish();val intt_=(Intent(this, ActFailure::class.java)); intt_.putExtra("zone", "modulo");startActivity(intt_)}
            }
            i++
            if (i==taskTypes.size) {
                val intt = Intent(this, ActResult::class.java)
                intt.putExtra("score", score)
                intt.putExtra("max", max)
                intt.putExtra("zone", "modulo")
                intt.putExtra("act", 4)
                if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusModuloBoss",0)<(score.toDouble()/max.toDouble()*100.0).toInt()) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusModuloBoss",(score.toDouble()/max.toDouble()*100.0).toInt()).apply()
                startActivity(intt)
            }
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) values = valueMakerForModuloBoss(i)
                variants = answerMakerForModuloBoss(values,i)
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
                val name = getString(resources.getIdentifier(taskNames[i], "string", packageName), values[1], values[2], values[3])
                taskText.text=name
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
fun valueMakerForModuloBoss(i: Int): Array<String>{
    var ans="";var n1=""; var n2=""; var n3=""; val num1: Int; var num2: Int; var num3: Int; val temp: Int
    when(i){
        1->{temp=(22..51).random(); num1=temp*(3..10).random(); do{num2=temp*(2..9).random()}while(num2==num1); do{num3=temp*(2..5).random()} while(num3==num2||num3==num1); n1="$num1"; n2="$num2"; n3="$num3"; ans="${GCD(GCD(num1,num2),num3)}";}
        2->{num3=((8..9)+(11..16)).random();num1=(21..55).random();do{num2=((20 until num1)+(num1+1..56)).random()}while((num1+num2)%num3==0);ans=(num1+num2).toString(radix=num3);n3="$num3";n1="$num1";n2="$num2"}
        3->{
            num1=(11..20).random()
            do{num2=(11..20).random()} while (num2==num1)
            num3=(5..15).random()
            n1="$num1"
            n2="$num2"
            n3="$num3"
            ans="${LCM(num1,num2)*num3}"
        }
        4->{num2=(0..1).random(); num3=(0..1).random(); n2="$num2"; n3="$num3"
            when((0..3).random()){
            0 -> {n1="¬N ∨ (¬M ⋀ (N∨(N ⋀ M)))"; ans="${(1-num3)or((1-num2)and num3)}"}
            1 -> {n1="¬(M ⊕ N) ∨ (M ⋀ N)";ans="${num2==num3 or (num2 and num3)}"}
            2 -> {n1="¬N ∨ (M ⋀ (M ∨ N))";ans="${(1-num3) or num2}"}
            3 -> {n1="(M ⊕ M) ∨ (M ⊕ N)";ans="${num3 or num2}"}
        }}
        5->{num1=((8..9)+(11..16)).random(); n1="$num1"; num2=(num1..2*num1-1).random(); num3=(3..num1/2).random(); n2=num2.toString(num1); n3=num3.toString(num1); ans=(num2*num3).toString(num1)
        }
    }
    return arrayOf(ans,n1,n2,n3)
}
fun answerMakerForModuloBoss(values: Array<String>, i: Int): Array<String> {
    val ans=values[0]
    val b= mutableListOf<String>("","","","")
    b[0]=ans
    when(i){
        3->{
            b[1]= "${LCM(values[1].toInt(),values[2].toInt())}"
            b[2]= values[3]
            b[3]= "${LCM(values[1].toInt(),values[2].toInt())/values[3].toInt()}"
        }
        5->{
            val v=values[1].toInt()
            b[1]= (values[2].toInt(radix=v)*values[2].toInt(radix=v)).toString(radix=v)
            b[2]= (values[3].toInt(radix=v)*values[3].toInt(radix=v)).toString(radix=v)
            b[3]= (values[2].toInt(radix=v)*(values[3].toInt(radix=v)+1)).toString(radix=v)
        }
    }
    b.shuffle()
    return arrayOf(b[0],b[1],b[2],b[3],"${b.indexOf(ans)}")
}