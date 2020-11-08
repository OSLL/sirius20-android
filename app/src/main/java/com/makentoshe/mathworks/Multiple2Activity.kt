package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.multiple_1.*
import kotlinx.android.synthetic.main.multiple_2.*
import kotlin.random.Random

var answers: Int = 0

class Multiple2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.multiple_2)
        var a: Int = Random.nextInt(2, 10)
        var b: Int = Random.nextInt(2, 10)
        val task: String = this.getString(R.string.MultipleTask)
        val taskS: String = String.format(task, a, b)
        textMultipleTask.setText(taskS)
        if (intent.getIntExtra("progress", 0) == 5) {
            progressBarZone1Act2_2.progress = intent.getIntExtra("progress", 0)
            val task: String = this.getString(R.string.DivTask)
            val taskS: String = String.format(task, a * b, b)
            textMultipleTask.setText(taskS)
        }
        checkMultiple.setOnClickListener {
            if (checkMultiple.text.toString() == "Проверить") {
                if (progressBarZone1Act2_2.progress <= 2) {
                    if (editTextMultiple1.getText().toString() == (a * b).toString()) {
                        textRes.setText("Верно")
                        answers += 1
                    } else {
                        textRes.setText("Неверно")
                    }
                } else if (progressBarZone1Act2_2.progress == 3) {
                        if (editTextMultiple1.getText().toString() == (a * (b + 1)).toString()) {
                            textRes.setText("Верно")
                            answers += 1
                        } else {
                            textRes.setText("Неверно")
                        }
                    } else if (progressBarZone1Act2_2.progress == 5 || progressBarZone1Act2_2.progress == 6) {
                        if (editTextMultiple1.getText().toString() == a.toString()) {
                            textRes.setText("Верно")
                            answers += 1
                        } else {
                            textRes.setText("Неверно")
                        }
                    } else if (progressBarZone1Act2_2.progress == 7) {
                        if (editTextMultiple1.getText().toString() == (a * b + a).toString()) {
                            textRes.setText("Верно")
                            answers += 1
                        } else {
                            textRes.setText("Неверно")
                        }
                    }
            } else {
                    checkMultiple.setText("Проверить")
                    textRes.setText("")
                    editTextMultiple1.setText("")
                    if (progressBarZone1Act2_2.progress == 2) {
                        a = Random.nextInt(2, 10)
                        b = Random.nextInt(2, 10)
                        val task: String = this.getString(R.string.MultipleTask3)
                        val taskS: String = String.format(task, a, b)
                        textMultipleTask.setText(taskS)
                    } else if (progressBarZone1Act2_2.progress == 3) {
                        a = Random.nextInt(20, 70)
                        b = Random.nextInt(2, 10)
                        val task: String = this.getString(R.string.MultipleTask4)
                        val taskS: String = String.format(task, a, b)
                        textMultipleTask.setText(taskS)
                    } else if (progressBarZone1Act2_2.progress == 4) {
                        val multIntent = Intent(this, MultipleActivity::class.java)
                        multIntent.putExtra("progress_2", progressBarZone1Act2_2.progress)
                        startActivity(multIntent)
                    } else if (progressBarZone1Act2_2.progress == 6) {
                        a = Random.nextInt(2, 10)
                        b = Random.nextInt(2, 10)
                        val task: String = this.getString(R.string.DivTask2)
                        val taskS: String = String.format(task, b, a*b)
                        textMultipleTask.setText(taskS)
                    } else if (progressBarZone1Act2_2.progress == 7) {
                        a = Random.nextInt(20, 70)
                        b = Random.nextInt(2, 10)
                        val task: String = this.getString(R.string.DivTask3)
                        val taskS: String = String.format(task, a * b, b)
                        textMultipleTask.setText(taskS)
                    }

                }
                if (textRes.text != "") {
                    checkMultiple.setText("Продолжить")
                    progressBarZone1Act2_2.progress += 1
                    if (progressBarZone1Act2_2.progress == 8) {
                        val multIntent = Intent(this, MultipleResult::class.java)
                        multIntent.putExtra("answers", answers)
                        startActivity(multIntent)
                    }
                }

        }
    }

}