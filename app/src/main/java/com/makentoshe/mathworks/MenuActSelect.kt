package com.makentoshe.mathworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_menu_act_select.*

class MenuActSelect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_act_select)
        var zone="integer"
        zone=intent.getStringExtra("zone")
        var acts: Int
        when (zone){
            "integer" -> acts=5
            "modulo" -> acts=5
            "combine" -> acts=5
            "stats" -> acts=6
            "float" -> acts=8
            "function" -> acts=8
            "triangle" -> acts=8
            "plain" -> acts=6
            "stereo"-> acts=5
            "deriv" -> acts=5
            "complex" -> acts=4
            else -> acts =8
        }
        val actArray= mutableListOf<String>()
        for (i in (0..acts-2)){
            actArray.add(getString(resources.getIdentifier(("act_${zone}_${i+1}_name"), "string", packageName)))
        }
        actArray.add("act_${zone}_${acts-1}_name")
        val adapt= ArrayAdapter<String>(this,R.layout.activity_menu_act_select,actArray)
        list.adapter=adapt
        Log.d("ACT_SELECT","Zone name loaded zone_${zone}_name")
        zoneTitle.text=getString(resources.getIdentifier("zone_${zone}_name", "string", packageName))
    }
}