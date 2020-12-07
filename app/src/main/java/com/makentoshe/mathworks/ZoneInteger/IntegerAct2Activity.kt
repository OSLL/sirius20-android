package com.makentoshe.mathworks.ZoneInteger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.example_task.*
import kotlinx.android.synthetic.main.example_theory.*
import kotlin.random.Random

var a: Int = Random.nextInt(2, 10)
var b: Int = Random.nextInt(2, 10)
var answers: Int = 0

class IntegerAct2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.example_theory)
        when (progressBarTheory.progress) {
            0 -> {
                val H: String = this.getString(R.string.textMultiple1)
                val H1: String = String.format(H, 1, 2)
                val subH: String = this.getString(R.string.Subhead1_1)
                head.text = H1
                subhead.text = subH
                val task: String = this.getString(R.string.textMultiple1)
                theoryText.text = task
            }
            4 -> {
                val H: String = this.getString(R.string.textMultiple1)
                val H1: String = String.format(H, 1, 2)
                val subH: String = this.getString(R.string.Subhead1_1)
                head.text = H1
                subhead.text = subH
                val task: String = this.getString(R.string.textMultiple2)
                theoryText.text = task
            }
        }
        when (progressBarTask.progress) {

            1 -> {
                a = Random.nextInt(2, 10)
                b = Random.nextInt(2, 10)
                val H: String = this.getString(R.string.textMultiple1)
                val H1: String = String.format(H, 1, 2)
                val subH: String = this.getString(R.string.Subhead1_1)
                headTask.text = H1
                subheadTask.text = subH
                val task: String = this.getString(R.string.MultipleTask)
                val taskS: String = String.format(task, a, b)
                taskText.text = taskS
            }
            2 -> {
                a = Random.nextInt(2, 10)
                b = Random.nextInt(2, 10)
                val task: String = this.getString(R.string.MultipleTask3)
                val taskS: String = String.format(task, a, b)
                taskText.text = taskS
            }
            3 -> {
                a = Random.nextInt(20, 70)
                b = Random.nextInt(2, 10)
                val task: String = this.getString(R.string.MultipleTask4)
                val taskS: String = String.format(task, a, b)
                taskText.text = taskS
            }

            5 -> {
                a = Random.nextInt(2, 10)
                b = Random.nextInt(2, 10)
                val task: String = this.getString(R.string.DivTask)
                val taskS: String = String.format(task, a, b)
                taskText.text = taskS
            }
            6 -> {
                a = Random.nextInt(2, 10)
                b = Random.nextInt(2, 10)
                val task: String = this.getString(R.string.DivTask2)
                val taskS: String = String.format(task, b, a*b)
                taskText.text = taskS
            }
            7 -> {
                a = Random.nextInt(20, 70)
                b = Random.nextInt(2, 10)
                val task: String = this.getString(R.string.DivTask3)
                val taskS: String = String.format(task, a * b, b)
                taskText.text = taskS
            }
        }
        continueButtonTheory.setOnClickListener {
            when (progressBarTheory.progress) {
                0 -> {
                    progressBarTask.progress += 1
                }
                4 -> {
                    progressBarTask.progress += 1
                }
            }
        }
        continueButtonTask.setOnClickListener {
            if (continueButtonTask.text.toString() == "Проверить") {
                when (progressBarTask.progress) {
                    1, 2 -> {
                        if (editTextTask.text.toString() == (a * b).toString()) {
                            textResultTask.text = "Верно"
                            answers += 1
                        } else {
                            textResultTask.text = "Неверно"
                        }}
                    3 -> {
                        if (editTextTask.text.toString() == (a * (b + 1)).toString()) {
                            textResultTask.text = "Верно"
                            answers += 1
                        } else {
                            textResultTask.text = "Неверно"
                        }}
                    5, 6 -> {
                        if (editTextTask.text.toString() == a.toString()) {
                            textResultTask.text = "Верно"
                            answers += 1
                        } else {
                            textResultTask.text = "Неверно"
                        }}
                    7 -> {
                        if (editTextTask.text.toString() == (a * b + a).toString()) {
                            textResultTask.text = "Верно"
                            answers += 1
                        } else {
                            textResultTask.text = "Неверно"
                        }}
                }
            } else {
                continueButtonTask.text = "Проверить"
                textResultTask.text = ""
                editTextTask.setText("")
                if (progressBarTask.progress == 4) {
                    setContentView(R.layout.example_theory)
                }
            }
            if (textResultTask.text != "") {
                continueButtonTask.text = "Продолжить"
                progressBarTask.progress += 1
                progressBarTheory.progress = progressBarTask.progress
            }

        }

    }
}