package com.makentoshe.mathworks.ZoneInteger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.makentoshe.mathworks.R
import com.makentoshe.mathworks.Result
import kotlinx.android.synthetic.main.example_task.*
import kotlin.random.Random

var answersComposite: Int = 0
var aComposite: Int = 0
var bComposite: Int = 0

fun decomposition (x: Int): String{
    var str = ""
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

class IntegerAct2Activity : AppCompatActivity() {
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
        progressBarTask.max = 5
        progressBarTaskTrue.max = 5
        when (progressBarTask.progress) {
            0 -> {
                val h: String = this.getString(R.string.Head1)
                val subh: String = this.getString(R.string.Subhead1_4)
                headTask.text = h
                subheadTask.text = subh
                val task: String = this.getString(R.string.textComposite)
                taskText.text = task
                editTextTask.visibility = View.GONE
            }

        }

        continueButtonTask.setOnClickListener {
            when (progressBarTask.progress) {
                0 -> {
                    progressBarTaskTrue.progress += 1
                }
                1 -> {
                    if (editTextTask.text.toString() == decomposition(aComposite)) {
                        answersComposite += 1
                        progressBarTaskTrue.progress += 1
                    }
                }
                2 -> {
                    if (editTextTask.text.toString() == "1") {
                        answersComposite += 1
                        progressBarTaskTrue.progress += 1
                    }
                }
                3 -> {
                    if (editTextTask.text.toString() == 2.toString()) {
                        answersComposite += 1
                        progressBarTaskTrue.progress += 1
                    }
                }
                4 -> {
                    if (editTextTask.text.toString() == 2.toString()) {
                        answersComposite += 1
                        progressBarTaskTrue.progress += 1
                    }
                }
            }
            editTextTask.setText("")

            progressBarTask.progress += 1
            when (progressBarTask.progress) {
                1 -> {
                    aComposite = Random.nextInt(10, 20)
                    val task: String = this.getString(R.string.CompositeTask)
                    val taskS: String = String.format(task, aComposite)
                    taskText.text = taskS
                    editTextTask.visibility = View.VISIBLE
                }
                2 -> {
                    aComposite = Random.nextInt(500, 1000)
                    bComposite = Random.nextInt(400, 500)
                    val task: String = this.getString(R.string.CompositeTask2)
                    val taskS: String = String.format(task, aComposite, bComposite)
                    taskText.text = taskS
                }
                3 -> {
                    aComposite = Random.nextInt(20, 70)
                    bComposite = Random.nextInt(2, 10)
                    val task: String = this.getString(R.string.CompositeTask3)
                    val taskS: String = String.format(task)
                    taskText.text = taskS
                }
                4 -> {
                    aComposite = Random.nextInt(2, 10)
                    bComposite = Random.nextInt(2, 10)
                    val task: String = this.getString(R.string.CompositeTask4)
                    val taskS: String = String.format(task)
                    taskText.text = taskS
                }


            }
            if (progressBarTask.progress == 5) {
                val multIntent = Intent(this, Result::class.java)
                multIntent.putExtra("answers", answersComposite)
                multIntent.putExtra("tasks", 4)
                multIntent.putExtra("h", this.getString(R.string.Head1_4))
                multIntent.putExtra("subh", this.getString(R.string.Subhead1_4))
                startActivity(multIntent)
            }


        }

        }
    }
