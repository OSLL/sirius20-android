package com.makentoshe.mathworks.ZoneInteger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.makentoshe.mathworks.Result
import com.makentoshe.mathworks.R
import kotlinx.android.synthetic.main.example_task.*
import kotlin.random.Random

var answers: Int = 0
var a: Int = Random.nextInt(2, 10)
var b: Int = Random.nextInt(2, 10)

class IntegerAct4Activity : AppCompatActivity() {
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
        when (progressBarTask.progress) {
            0 -> {
                val H: String = this.getString(R.string.Head1)
                val subH: String = this.getString(R.string.Subhead1_1)
                headTask.text = H
                subheadTask.text = subH
                val task: String = this.getString(R.string.textMultiple1)
                taskText.text = task
                taskImage.visibility = View.VISIBLE
                editTextTask.visibility = View.GONE
                taskImage.setImageResource(R.drawable.table_multiple)
            }


        }

        continueButtonTask.setOnClickListener {
            when (progressBarTask.progress) {
                0 -> {
                    progressBarTaskTrue.progress += 1
                }
                1, 2 -> {
                    if (editTextTask.text.toString() == (a*b).toString()) {
                        answers += 1
                        progressBarTaskTrue.progress += 1
                    }
                }
                3 -> {
                    if (editTextTask.text.toString() == (a * (b + 1)).toString()) {
                        answers += 1
                        progressBarTaskTrue.progress += 1
                    }
                }
                4 -> {
                    progressBarTaskTrue.progress += 1
                }
                5, 6 -> {
                    if (editTextTask.text.toString() == a.toString()) {
                        answers += 1
                        progressBarTaskTrue.progress += 1
                    }
                }
                7 -> {
                    if (editTextTask.text.toString() == (a * b + a).toString()) {
                        answers += 1
                        progressBarTaskTrue.progress += 1
                    }
                }
            }
            editTextTask.setText("")

            progressBarTask.progress += 1
            when (progressBarTask.progress) {
                1 -> {
                    taskImage.visibility = View.GONE
                    editTextTask.visibility = View.VISIBLE
                    a = Random.nextInt(2, 10)
                    b = Random.nextInt(2, 10)
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
                4 -> {
                    val H: String = this.getString(R.string.Head1)
                    val subH: String = this.getString(R.string.Subhead1_1)
                    headTask.text = H
                    subheadTask.text = subH
                    val task: String = this.getString(R.string.textMultiple2)
                    taskText.text = task
                    editTextTask.visibility = View.GONE
                }
                5 -> {
                    editTextTask.visibility = View.VISIBLE
                    a = Random.nextInt(2, 10)
                    b = Random.nextInt(2, 10)
                    val task: String = this.getString(R.string.DivTask)
                    val taskS: String = String.format(task, a * b, b)
                    taskText.text = taskS
                }
                6 -> {
                    a = Random.nextInt(2, 10)
                    b = Random.nextInt(2, 10)
                    val task: String = this.getString(R.string.DivTask2)
                    val taskS: String = String.format(task, b, a * b)
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
            if (progressBarTask.progress == 8) {
                val multIntent = Intent(this, Result::class.java)
                multIntent.putExtra("answers", answers)
                multIntent.putExtra("tasks", 6)
                multIntent.putExtra("h", this.getString(R.string.Head1))
                multIntent.putExtra("subh", this.getString(R.string.Subhead1_1))
                startActivity(multIntent)
            }


        }
    }
}