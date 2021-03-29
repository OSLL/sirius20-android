package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.layout_menu_zone_select.*

class MenuZoneSelect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_menu_zone_select)
        val lives= intent.getIntExtra("lives",3)
        val intt=Intent(this,MenuActSelect::class.java)
        intt.putExtra("lives",lives)
        val zoneArray=arrayOf("integer","modulo","combine","stats","float","function","triangle","plain","stereo","deriv","complex")
        val mask=arrayOf(true,true,true,false,false,false,false,false,false,true,false)
        val zoneNameArray= Array<String>(11){ ""}
        for (i in (0..10)){
            zoneNameArray[i]=(getString(resources.getIdentifier(("zone_${zoneArray[i]}_name"), "string", packageName)))
        }
        val adapt= CustomArrayAdapter(this,zoneNameArray,mask)
        list1.adapter=adapt
        list1.setOnItemClickListener { _, _, position, _ ->
            if (mask[position])
            {val intent= Intent(this,MenuActSelect::class.java)
            intent.putExtra("zone",zoneArray[position])
            startActivity(intent)}
        }
    }
}