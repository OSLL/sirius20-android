package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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
        if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusIntegerAct1",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusIntegerAct2",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusIntegerAct3",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusIntegerAct4",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusIntegerBoss",0)==100)
            PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusInteger",1).apply()
        if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusModuloAct1",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusModuloAct2",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusModuloAct3",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusModuloAct4",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusModuloBoss",0)==100)
            PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusModulo",1).apply()
        if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusCombineAct1",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusCombineAct2",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusCombineAct3",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusCombineAct4",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusCombineBoss",0)==100)
            PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusCombine",1).apply()
        //Do the exact same thing when new zones are added
        if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusDerivAct1",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusDerivAct2",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusDerivAct3",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusDerivAct4",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusDerivAct5",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusDerivBoss",0)==100)
            PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusDeriv",1).apply()
        if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusComplexAct1",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusComplexAct2",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusComplexAct3",0)==100 &&
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusComplexBoss",0)==100)
            PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt("statusComplex",1).apply()
        val mask=arrayOf(
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusInteger",0),
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusModulo",-1),
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusCombine",-1),
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusStats",-2),
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusFloat",-2),
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusFunction",-2),
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusTriangle",-2),
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusPlain",-2),
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusStereo",-2),
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusDeriv",-1),
            PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusComplex",-1)
        )
        val zoneNameArray= Array<String>(11){ ""}
        for (i in (0..10)){
            zoneNameArray[i]=(getString(resources.getIdentifier(("zone_${zoneArray[i]}_name"), "string", packageName)))
        }
        val maskFiltered =mask.filter { it>-2 }.toTypedArray()
        val ZoneNameArrayFiltered =zoneNameArray.filterIndexed { index, s ->  mask[index]>-2}.toTypedArray()
        val ZoneArrayFiltered=zoneArray.filterIndexed { index, s ->  mask[index]>-2}.toTypedArray()
        val adapt= CustomArrayAdapter(this,ZoneNameArrayFiltered,maskFiltered)
        list1.adapter=adapt
        list1.setOnItemClickListener { _, _, position, _ ->
            if ((mask[position]>-1) || PreferenceManager.getDefaultSharedPreferences(applicationContext).getBoolean("allowWandering",false))
            {val intent= Intent(this,MenuActSelect::class.java)
            intent.putExtra("zone",ZoneArrayFiltered[position])
            startActivity(intent)}
        }
        toMainButtonZoneSelect.setOnClickListener { startActivity(Intent(this,MainActivity::class.java));finish() }
    }
}