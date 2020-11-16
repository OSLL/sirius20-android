package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.composite_2.*
import kotlinx.android.synthetic.main.composite_2.textRes
import kotlinx.android.synthetic.main.multiple_2.*
import kotlin.random.Random

var answersComposite: Int = 0

fun Decomposition (x: Int): String{
    var str: String = ""
    var x1: Int = x
    for (i in 2..x) {
        if (x1 == 1) {
            break
        }
        while (x1 % i == 0) {
            x1 /= i
            str += i.toString()
        }

    }
    return str
}

class CompositeActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.composite_2)
        if (intent.getIntExtra("progress", 0) == 1) {
            progressBarZone1Act2_4.progress = intent.getIntExtra("progress", 0)
        }
        var a: Int = Random.nextInt(10, 50)
        var b: Int = Random.nextInt(2, 10)
        val task: String = this.getString(R.string.CompositeTask)
        val taskS: String = String.format(task, a)
        textCompositeTask.setText(taskS)
        checkComposite.setOnClickListener {
            if (checkComposite.text.toString() == "Проверить") {
                if (progressBarZone1Act2_4.progress == 1) {
                    if (editTextComposite1.getText().toString() == Decomposition(a)) {
                        textRes.setText("Верно")
                        answersComposite += 1
                    } else {
                        textRes.setText("Неверно")
                    }
                } else if (progressBarZone1Act2_4.progress == 2) {
                    if (editTextComposite1.getText().toString() == "01") {
                        textRes.setText("Верно")
                        answersComposite += 1
                    } else {
                        textRes.setText("Неверно")
                    }
                } else if (progressBarZone1Act2_4.progress == 3) {
                    if (editTextComposite1.getText().toString() == 2.toString()) {
                        textRes.setText("Верно")
                        answersComposite += 1
                    } else {
                        textRes.setText("Неверно")
                    }
                } else if (progressBarZone1Act2_4.progress == 4) {
                    if (editTextComposite1.getText().toString() == 2.toString()) {
                        textRes.setText("Верно")
                        answersComposite += 1
                    } else {
                        textRes.setText("Неверно")
                    }
                }
            } else {
                checkComposite.setText("Проверить")
                textRes.setText("")
                editTextComposite1.setText("")
                if (progressBarZone1Act2_4.progress == 2) {
                    a = Random.nextInt(500, 1000)
                    b = Random.nextInt(400, 500)
                    val task: String = this.getString(R.string.CompositeTask2)
                    val taskS: String = String.format(task, a, b)
                    textCompositeTask.setText(taskS)
                } else if (progressBarZone1Act2_4.progress == 3) {
                    a = Random.nextInt(20, 70)
                    b = Random.nextInt(2, 10)
                    val task: String = this.getString(R.string.CompositeTask3)
                    val taskS: String = String.format(task)
                    textCompositeTask.setText(taskS)
                } else if (progressBarZone1Act2_4.progress == 4) {
                    a = Random.nextInt(2, 10)
                    b = Random.nextInt(2, 10)
                    val task: String = this.getString(R.string.CompositeTask4)
                    val taskS: String = String.format(task)
                    textCompositeTask.setText(taskS)
                }

            }
            if (textRes.text != "") {
                checkComposite.setText("Продолжить")
                progressBarZone1Act2_4.progress += 1
                if (progressBarZone1Act2_4.progress == 5) {
                    val multIntent = Intent(this, CompositeResult::class.java)
                    multIntent.putExtra("answers", answersComposite)
                    startActivity(multIntent)
                }
            }

        }
    }

}