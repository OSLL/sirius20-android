package com.makentoshe.mathworks

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_arithmetic_act1.*
import java.util.*


class ArithmeticAct1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arithmetic_act1)
        var score=0
        var taskNames=arrayOf("act_1_1_descr_1","act_1_1_task_1","act_1_1_task_2","act_1_1_task_3","act_1_1_descr_2","act_1_1_task_4","act_1_1_task_5","act_1_1_task_6")
        var taskTypes=arrayOf(0,1,1,2,0,1,1,2)
        var taskQuantity=arrayOf(0,2,2,2,0,3,2,3)
        answerVariantGroup.visibility = View.GONE; answerNumberInput.visibility= View.GONE
        var i=0; var step=0
        taskText.text=getString(resources.getIdentifier(taskNames[0], "string", packageName))
        var max=6
        var variants=arrayOf(0,0,0,0)
        var nums=arrayOf(0,0,0,0)
        var choice=0
        nextButton.setOnClickListener{
            if (i<taskTypes.size){if (taskTypes[i]!=0) {
                if (taskTypes[i]==1){
                    choice = when {
                        answerVariant1.isChecked -> variants[0]
                        answerVariant2.isChecked -> variants[1]
                        answerVariant3.isChecked -> variants[2]
                        answerVariant4.isChecked -> variants[3]
                        else -> 0
                    }
                }
                if (taskTypes[i]==2){
                    var a = answerNumberInput.toString().toIntOrNull()
                    if (a!=null) choice = a else choice =0
                }
                if (choice==nums[0]) score++
                step++
            }}
            i++
            if (i==taskNames.size) {
            val intt= Intent(this, ActCompletedActivity::class.java)
                intt.putExtra("score",score)
                intt.putExtra("max",max)
            startActivity(intt)
            finish()
            }
            else {if (taskTypes[i]!=0) nums = correctNumberMakerForArithmAct1(i)
            when (taskTypes[i]) {
                0 -> {answerVariantGroup.visibility = View.GONE; answerNumberInput.visibility= View.GONE}
                2 -> {answerVariantGroup.visibility = View.GONE; answerNumberInput.visibility= View.VISIBLE;}
                1 -> {answerVariantGroup.visibility = View.VISIBLE; answerNumberInput.visibility= View.GONE;variants = answerVariantMakerForArithmAct1(nums);
                answerVariant1.text=variants[0].toString()
                answerVariant2.text=variants[1].toString()
                answerVariant3.text=variants[2].toString()
                answerVariant4.text=variants[3].toString()}
            }
            if(step<=max) progressBar.progress= ((step.toDouble()/max.toDouble())*100.0).toInt()
            if (i<taskNames.size) {
                var name=""
                if (taskQuantity[i]==2)
                {name=getString(resources.getIdentifier(taskNames[i], "string", packageName),nums[1],nums[2])}
                else if (taskQuantity[i]==3)
                {name=getString(resources.getIdentifier(taskNames[i], "string", packageName),nums[1],nums[2],nums[3])}
                else {name=getString(resources.getIdentifier(taskNames[i], "string", packageName))}
                taskText.text=name}
        }}
    }
}
fun correctNumberMakerForArithmAct1 (i: Int): Array<Int> {
    var num1:Int; var num2:Int; var num3=0; var ans:Int;
    when (i) {
        1->{
            num1=99; num2=99; while (num1%10+num2%10>9) {num1= (10..49).random(); num2=(10..49).random()}; ans=num1+num2
        }
        2->{
            num1=100; num2=99; while (num2%10-num1%10<0||num2-num1<=0) {num1= (10..99).random(); num2=(10..99).random()}; ans=num2-num1
        }
        3->{
            num1=99; num2=99; while (2*(num1%10)+num2%10>9){num1=(20..39).random(); num2=(5..15).random()}; ans=num2+2*num1
        }
        5->{
            num1=90; num2=90; num3=90; while (num1%10+num2%10+num3%10<10){num1=(20..49).random();num2=(20..49).random();num3=(20..49).random()};ans=num1+num2+num3
        }
        6->{
            num1=(20..99).random(); num2=(10..num1-1).random(); ans=(-num2+2*num1)
        }
        7->{
            num1=0; num2=0; ans=(200..899).random(); while(2*(num1%10)+ans%10<10||ans%10-(ans+2*num1)%10>-1||ans+2*num1<num2){num1=(10..199).random(); num2=(10..199).random()}; num3=ans+2*num1
        }
        else -> {num1=0; num2=0; ans=0}
    }
    return arrayOf(ans,num1,num2,num3)
}
fun answerVariantMakerForArithmAct1 (a: Array<Int>): Array<Int> {

    var ans=a[0]
    var b =arrayOf(0,0,0,0)
    b[(0..3).random()]=ans
    for (i in (0..3)) {
        var c=0
        while (c==0) c=(-20..20).random()
        if (b[i]==0) {if (ans+c<=0) b[i]=ans-c else b[i]=ans+c}
        if (i>0) while(b[i]==b[i-1]){b[i]+=1}
    }
    return b
}