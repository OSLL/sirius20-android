package com.makentoshe.mathworks.ZoneInteger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.preference.PreferenceManager
import com.makentoshe.mathworks.ActResult
import com.makentoshe.mathworks.R
import com.makentoshe.mathworks.hideKeyboard
import kotlinx.android.synthetic.main.layout_act_tasks.*

//Импорт библиотек и разметок

class IntegerAct2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headSetup.text=intent.getStringExtra("zone")
        subheadTask.text=intent.getStringExtra("act")
        //Акт получает имя зоны и имя акта, которые передаются через интент
        var score=0 //Очки прогресса (за правильные ответы)
        val taskNames=arrayOf("act_integer_2_descr_1","act_integer_2_task_1","act_integer_2_task_2","act_integer_2_task_3","act_integer_2_descr_2","act_integer_2_task_4","act_integer_2_task_5","act_integer_2_task_6")
        //Все подгружаемые строки акта
        val taskTypes=arrayOf(0,1,2,2,0,1,2,2)
        //Тип задачи: 0 теория, 1 выбор ответа, 2 ввод ответа
        val taskQuantity=arrayOf(0,2,2,2,0,2,2,2)
        //Количество генерируемых чисел или строчек в задаче
        Log.d("Act2","Imported")
        radioGroupTask.visibility = View.GONE; editTextTask.visibility= View.GONE
        //Отключаем видимость элементов
        var i=0; var step=0
        //i счётчик всех шагов, step счётчик сделанных или пропущенных задач
        descrText.text=getText(resources.getIdentifier(taskNames[0], "string", packageName))
        //Подгрузка текста в элемент теории descrText
        taskText.visibility = View.GONE; descrText.visibility= View.VISIBLE
        //Скрываем элемент задачи taskText и показываем элемент теории descrText
        val max=6 //Число задач
        var variants=IntArray(5) //Массив для временного хранения вариантов; нужен только актам, где есть задачи с выбором ответа. Содержит 4 варианта и номер правильного из них
        var nums=IntArray(4) //Массив для временного хранения генериремых чисел из условий задач; содержит ответ и 3 вставляемых числа
        var choice: Int //Номер выбора (для задач с выбором ответа)
        var a: String //Строка ввода
        var aint=0 //Строка ввода, преобразованная в Int
        taskImage.visibility=View.VISIBLE
        taskImage.setImageResource(R.drawable.table_multiple)
        editTextTask.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                a=editTextTask.text.toString().trim()
                if (a.toIntOrNull()!=null) aint=a.toInt()
                Log.d("Act2","Input was modified: $a $aint")
            }
        })
        continueButtonTask.setOnClickListener{
            taskImage.visibility=View.GONE
            Log.d("Act2","Button pressed")
            if (taskTypes[i]!=0) {
                if (taskTypes[i]==1){
                    if (radioButtonTask1.isChecked) choice=0
                    else if (radioButtonTask2.isChecked) choice=1
                    else if (radioButtonTask3.isChecked) choice=2
                    else if (radioButtonTask4.isChecked) choice=3
                    else choice=editTextTask.toString().toIntOrNull()?:-1
                    if (choice==variants[4]) score++
                    Log.d("Act1","Score is now $score")
                }
                if (taskTypes[i]==2){
                    Log.d("Act1","Text task, answer is ${nums[0]}")
                    if (aint==nums[0]) score++
                    Log.d("Act1","Score is now $score")
                }
                step++
                Log.d("Act1","Proceeding to next task, step=${step}, score=${score}")
            }
            i++
            if(i<taskTypes.size) if(taskTypes[i]!=2) hideKeyboard(this)
            if (i==taskTypes.size) {
                Log.d("Act1","The act is over, launching to the start menu")
                val intt= Intent(this, ActResult::class.java)
                intt.putExtra("score",score)
                intt.putExtra("max",max)
                intt.putExtra("zone", "integer")
                intt.putExtra("act", 1)
                if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusIntegerAct2",0)<(score.toDouble()/max.toDouble()*100.0).toInt()) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusIntegerAct2",(score.toDouble()/max.toDouble()*100.0).toInt()).apply()
                if ((score.toDouble()/max.toDouble()*100.0).toInt()>=50 && PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusIntegerAct3",-1)<0) PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusIntegerAct3",0).apply()
                startActivity(intt)
            }

            Log.d("Act1","Strings updated")
            Log.d("Act1","Now showing frame $i")
            radioGroupTask.clearCheck()
            editTextTask.setText("")
            Log.d("Act1","Variants unchecked, input cleaned")
            if (i<taskTypes.size) {
                if (taskTypes[i] != 0) nums = correctNumberMakerForIntegerAct2(i) //Генерируем числа для условия задач и ответы
                variants = answerMakerForIntegerAct2(nums) //Генерируем варианты ответов для задач
                Log.d("Act1", "Answer: ${nums[0]}")
                Log.d("Act1", "Variants deployed")
                when (taskTypes[i]) {
                    0 -> {
                        radioGroupTask.visibility = View.GONE; editTextTask.visibility = View.GONE; descrText.visibility = View.VISIBLE; taskText.visibility = View.GONE //Отобразить текст теории и скрыть текст задачи
                    }
                    2 -> {
                        radioGroupTask.visibility = View.GONE; editTextTask.visibility = View.VISIBLE; descrText.visibility = View.GONE; taskText.visibility = View.VISIBLE //Отобразить текст задачи и скрыть текст теории
                    }
                    1 -> {
                        radioGroupTask.visibility = View.VISIBLE; editTextTask.visibility = View.GONE; descrText.visibility = View.GONE; taskText.visibility = View.VISIBLE //Отобразить текст задачи и скрыть текст теории
                        radioButtonTask1.text = variants[0].toString()
                        radioButtonTask2.text = variants[1].toString()
                        radioButtonTask3.text = variants[2].toString()
                        radioButtonTask4.text = variants[3].toString()//Подписать названия вариантов ответа
                    }
                }
                Log.d("Act1", "Variants deployed")
                if (step <= max) progressBarTaskTrue.progress = ((score.toDouble() / max.toDouble()) * 100.0).toInt()
                if (step <= max) progressBarTask.progress = ((step.toDouble() / max.toDouble()) * 100.0).toInt() //Обновляем счетчики прогресса
                val name: String
                descrText.text=getText(resources.getIdentifier(taskNames[i], "string", packageName)) //Присваиваем текст теории текстовому блоку (через getText, чтобы сохранить форматирование)
                when {
                    taskQuantity[i] == 2 -> {
                        name = getString(resources.getIdentifier(taskNames[i], "string", packageName), nums[1], nums[2])
                    }
                    taskQuantity[i] == 3 -> {
                        name = getString(resources.getIdentifier(taskNames[i], "string", packageName), nums[1], nums[2], nums[3])
                    }
                    else -> {
                        name = getString(resources.getIdentifier(taskNames[i], "string", packageName))
                    }
                }
                taskText.text = name //Присваиваем строку задачи текстовому блоку (через getString, чтобы подставить числа)
            }
        }
        }
    }
fun correctNumberMakerForIntegerAct2 (i: Int): IntArray {
    val num1:Int; val num2:Int; val num3=0; val ans:Int
    when (i) {
        1->{
            num1=(2..10).random(); num2=(2..10).random(); ans=num1*num2
        }
        2->{
            num1=(2..10).random(); num2=(2..20).random(); ans=num1*num2
        }
        3->{
            num1=(3..15).random(); num2=(3..15).random(); ans=num1*(1+num2)
        }
        5->{
            num2=(3..15).random(); ans=(3..15).random(); num1=ans*num2
        }
        6->{
            num1=(3..15).random(); ans=(3..15).random(); num2=ans*num1
        }
        7->{
            num2=(3..7).random(); num1=num2*(5..15).random(); ans=num1+num1/num2
        }
        else -> {num1=0; num2=0; ans=0}
    }
    return intArrayOf(ans,num1,num2,num3)
}
fun answerMakerForIntegerAct2 (a: IntArray): IntArray {
    val ans=a[0]
    val b = mutableListOf<Int>(ans,0,0,0)
    do{b[1]=b[0]+(-25..25).random()}while(b[1]==b[0]||b[1]<=0);do{b[2]=b[0]+(-25..25).random()}while(b[2]==b[0]||b[2]==b[1]||b[2]<=0);do{b[3]=b[0]+(-25..25).random()}while(b[3]==b[0]||b[3]==b[1]||b[3]==b[2]||b[3]<=0)
    b.shuffle()
    val i_corr=b.indexOf(ans)
    val aa= intArrayOf(b[0],b[1],b[2],b[3],i_corr)
    return aa
}