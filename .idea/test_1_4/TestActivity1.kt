package com.quickmaths.quickmaths

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_test1.*
import kotlinx.android.synthetic.main.activity_test1.actProgressName as actProgressName1

class TestActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)
        var n=3
        var answer="60"
        actProgressName1.text = "Task 1 of $n"
        var i: Int=1
        nextButton.setOnClickListener{
            if (inputField.text.contains(answer)){
                if(i<n) {i++; inputField.setText("")}; if (i==n){nextButton.text="Check"}; actProgressName1.text = "Task $i of $n"
            }
            else{
                Toast.makeText(applicationContext,"Wrong Answer" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}