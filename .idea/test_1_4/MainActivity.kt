package com.quickmaths.quickmaths

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("TAG","Hello World")
        button.setOnClickListener {  but -> Log.d("CLICK_LISTEN", "Henlo")
        Toast.makeText(applicationContext,button.text ,Toast.LENGTH_SHORT).show()}
        button.setOnClickListener {
            button.text=textField.text
        }
        /*textField.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, end: Int) {
                TODO("Not yet implemented")
            }
        })
        textField.onFocusChangeListener= View.OnFocusChangeListener{_, hasFocus -> {
            if(hasFocus){button2.text="0"}
            else {button2.text="GetStarted"}
        }
    }*/
    button2.setOnClickListener {
        var intt: Intent
        if (textField.text.contains("leti") && textField.text.length<=6) intt=Intent(this,SecretLevelSelectActivity::class.java)
        else intt=Intent(this, ActionActivity::class.java)
        startActivity(intt)
    }

}}