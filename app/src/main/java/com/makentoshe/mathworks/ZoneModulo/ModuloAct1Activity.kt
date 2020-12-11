package com.makentoshe.mathworks.ZoneModulo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.example_task.*
import kotlin.random.Random.Default.nextInt as nextInt

var answersCompositeNum: Int = 0

fun rep_letters(number: Int = 0): String{
    var newNumber: String = ""
    newNumber = when (number) {
        10 -> "A"
        11 -> "B"
        12 -> "C"
        13 -> "D"
        14 -> "E"
        15 -> "F"
        else -> number.toString()
    }
    return newNumber
}
fun number(degree: Int = 2, num: Int = 2): String{
    var x: String = ""
    for (i in 0..num)
        x += rep_letters(nextInt(1, degree - 1))
    return x
}

fun sum(a: String = "", b: String = "", degree: Int = 2): String{
    var res: String = ""
    val a_list= mutableListOf<Int>()
    val b_list= mutableListOf<Int>()
    val c_list = mutableListOf<String>()
    var one: Int = 0
    var new_str: Int
    var end_str: String
    for (i in a) {
        new_str = when (i.toString()) {
            "A" -> 10
            "B" -> 11
            "C" -> 12
            "D" -> 13
            "E" -> 14
            "F" -> 15
            else -> i.toString().toInt()
        }
        a_list.add(new_str)
    }
    a_list.reverse()
    for (i in b) {
        new_str = when (i.toString()) {
            "A" -> 10
            "B" -> 11
            "C" -> 12
            "D" -> 13
            "E" -> 14
            "F" -> 15
            else -> i.toString().toInt()
        }
        b_list.add(new_str)
    }
    b_list.reverse()
    if (a_list.size >= b_list.size) {
        for (i in 0 until a_list.size) {
            if (i>b_list.size) {
                b_list.add(0)
            }
            if ((a_list[i] +b_list[i]+one) < degree) {
                end_str = when (a_list[i] +b_list[i]+one) {
                    10 -> "A"
                    11 -> "B"
                    12 -> "C"
                    13 -> "D"
                    14 -> "E"
                    15 -> "F"
                    else -> (a_list[i] +b_list[i]+one).toString()
                }
                c_list.add(end_str)
                one = 0
            } else {
                end_str = when (a_list[i] +b_list[i]+one-degree) {
                    10 -> "A"
                    11 -> "B"
                    12 -> "C"
                    13 -> "D"
                    14 -> "E"
                    15 -> "F"
                    else -> (a_list[i] +b_list[i]+one-degree).toString()
                }
                c_list.add(end_str)
                one = 1
            }
        }
    } else {
        for (i in 0 until b_list.size) {
            if (i>b_list.size) {
                a_list.add(0)
            }
            if ((a_list[i]+b_list[i]+one) < degree) {
                end_str = when (a_list[i] +b_list[i]+one) {
                    10 -> "A"
                    11 -> "B"
                    12 -> "C"
                    13 -> "D"
                    14 -> "E"
                    15 -> "F"
                    else -> (a_list[i] +b_list[i]+one).toString()
                }
                c_list.add(end_str)
                one = 0
            } else {
                end_str = when (a_list[i] +b_list[i]+one-degree) {
                    10 -> "A"
                    11 -> "B"
                    12 -> "C"
                    13 -> "D"
                    14 -> "E"
                    15 -> "F"
                    else -> (a_list[i] +b_list[i]+one-degree).toString()
                }
                c_list.add(end_str)
                one = 1
            }
        }
    }
    if (one == 1) {
        c_list.add(1.toString())
    }
    c_list.reverse()
    for (i in c_list){
        res += i
    }
    return res
}

class NumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.example_task)
        val task: String = this.getString(R.string.NumberTask)
        var degree: Int = nextInt(2, 16)
        var x: String = number(degree, nextInt(3, 5))
        var y: String = number(degree, nextInt(3, 5))
        val taskS: String = String.format(task, x, y, degree)
        taskText.text = taskS
        continueButtonTask.setOnClickListener {
            when (progressBarTask.progress) {
                1 -> {
                    if (editTextTask.text.toString() == sum(x, y, degree)) {
                        answersCompositeNum += 1
                    }
                }
                2 -> {
                    if (editTextTask.text.toString() == y) {
                        answersCompositeNum += 1
                    }
                }
            }
            editTextTask.setText("")

            progressBarTask.progress += 1
            when (progressBarTask.progress) {
                2 -> {
                    degree = nextInt(2, 16)
                    x = number(degree, nextInt(3, 5))
                    y = number(degree, nextInt(3, 5))
                    val task: String = this.getString(R.string.Number2Task)
                    val taskS: String = String.format(task, degree, sum(x, y, degree), x)
                    taskText.text = taskS
                }
            }
        }
    }
}