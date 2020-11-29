package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_lobby__main.*
import kotlinx.android.synthetic.main.activity_menu_act_select.*

class MenuZoneSelect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lobby__main)
        var lives:Int
        lives=intent.getIntExtra("lives",3)
        val intt=Intent(this,MenuActSelect::class.java)
        intt.putExtra("lives",lives)
        val zoneArray=arrayOf("integer","modulo","combine","stats","float","function","triangle","plain","stereo","deriv","complex")
        var zoneNameArray= Array<String>(11){ ""}
        for (i in (0..10)){
            zoneNameArray[i]=(getString(resources.getIdentifier(("zone_${zoneArray[i]}_name"), "string", packageName)))
        }
        val adapt= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,zoneNameArray)
        list1.adapter=adapt
        list1.setOnItemClickListener { parent, view, position, id ->
            val intent= Intent(this,MenuActSelect::class.java)
            intent.putExtra("zone",zoneArray[position])
            startActivity(intent)
        }
    }
}