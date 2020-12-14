package com.makentoshe.mathworks.ZoneModulo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.text.Spanned
import android.view.MenuItem
import com.makentoshe.mathworks.R
import com.makentoshe.mathworks.Result
import com.makentoshe.mathworks.ZoneInteger.answers
import kotlinx.android.synthetic.main.example_task.*
import kotlin.random.Random.Default.nextInt as nextInt
import java.math.BigInteger

var answersCompositeNum: Int = 0

class ModuloAct1Activity : AppCompatActivity() {
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        return if (id == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.example_task)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        editTextTask.inputType = InputType.TYPE_CLASS_TEXT
        val H: String = this.getString(R.string.Head2)
        val subH: String = this.getString(R.string.Subhead2_1)
        headTask.text = H
        subheadTask.text = subH
        progressBarTask.max = 4
        progressBarTaskTrue.max = 4
        var task: String = this.getString(R.string.NumberTask)
        var degree: Int = nextInt(11, 16)
        var x: Int = nextInt(10, 20)
        var y: Int = nextInt(10, 20)
        var x1: String = BigInteger(x.toString()).toString(degree)
        var y1: String = BigInteger(y.toString()).toString(degree)
        var taskS: String = String.format(task, x1, y1, degree)
        taskText.text = taskS
        
        continueButtonTask.setOnClickListener {
            when (progressBarTask.progress) {
                0 -> {
                    if (editTextTask.text.toString() == BigInteger((x+y).toString()).toString(degree)) {
                        answersCompositeNum += 1
                        progressBarTaskTrue.progress += 1
                    }
                }
                1 -> {
                    if (editTextTask.text.toString() == BigInteger((y).toString()).toString(degree)) {
                        answersCompositeNum += 1
                        progressBarTaskTrue.progress += 1
                    }
                }
                2 -> {
                    if (editTextTask.text.toString() == BigInteger((x*y).toString()).toString(degree)) {
                        answersCompositeNum += 1
                        progressBarTaskTrue.progress += 1
                    }
                }
                3 -> {
                    if (editTextTask.text.toString() == BigInteger((y).toString()).toString(degree)) {
                        answersCompositeNum += 1
                        progressBarTaskTrue.progress += 1
                    }
                }
            }
            editTextTask.setText("")

            progressBarTask.progress += 1
            when (progressBarTask.progress) {
                1 -> {
                    degree = nextInt(11, 16)
                    task= this.getString(R.string.Number2Task)
                    x = nextInt(10, 20)
                    y= nextInt(10, 20)
                    x1= BigInteger(x.toString()).toString(degree)
                    taskS = String.format(task, degree.toString(), BigInteger((x+y).toString()).toString(degree), x1)
                    taskText.text = taskS
                }
                2 -> {
                    degree = nextInt(11, 16)
                    task = this.getString(R.string.Number3Task)
                    x = nextInt(10, 20)
                    y = nextInt(10, 20)
                    x1 = BigInteger(x.toString()).toString(degree)
                    y1 = BigInteger(y.toString()).toString(degree)
                    taskS = String.format(task, degree.toString(), y1, x1)
                    taskText.text = taskS
                }
                3 -> {
                    degree = nextInt(11, 16)
                    task = this.getString(R.string.Number4Task)
                    x = nextInt(10, 20)
                    y = nextInt(10, 20)
                    x1 = BigInteger(x.toString()).toString(degree)
                    taskS = String.format(task, degree.toString(), x1, BigInteger((x*y).toString()).toString(degree))
                    taskText.text = taskS
                }
            }
            if (progressBarTask.progress == 4) {
                val multIntent = Intent(this, Result::class.java)
                multIntent.putExtra("answers", answersCompositeNum)
                multIntent.putExtra("tasks", 4)
                multIntent.putExtra("h", this.getString(R.string.Head2))
                multIntent.putExtra("subh", this.getString(R.string.Subhead2_1))
                startActivity(multIntent)
            }
        }
    }
}