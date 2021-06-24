package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.makentoshe.mathworks.ZoneCombine.*
import com.makentoshe.mathworks.ZoneComplex.ComplexAct1Activity
import com.makentoshe.mathworks.ZoneComplex.ComplexAct2Activity
import com.makentoshe.mathworks.ZoneComplex.ComplexAct3Activity
import com.makentoshe.mathworks.ZoneComplex.ComplexBossActivity
import com.makentoshe.mathworks.ZoneDeriv.*
import com.makentoshe.mathworks.ZoneFloat.*
import com.makentoshe.mathworks.ZoneFunction.*
import com.makentoshe.mathworks.ZoneInteger.*
import com.makentoshe.mathworks.ZoneModulo.*
import com.makentoshe.mathworks.ZonePlain.*
import com.makentoshe.mathworks.ZoneStats.*
import com.makentoshe.mathworks.ZoneStereo.*
import com.makentoshe.mathworks.ZoneTriangle.*
import kotlinx.android.synthetic.main.layout_act_result.*
import kotlinx.android.synthetic.main.layout_act_result.continueButtonResult
import kotlinx.android.synthetic.main.layout_act_result_test.*
import kotlinx.android.synthetic.main.layout_menu_zone_select.*
import kotlinx.android.synthetic.main.layout_menu_zone_select.list1

class ActResultTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val prefs=PreferenceManager.getDefaultSharedPreferences(applicationContext)
        setTheme(prefs.getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_result_test)
        var array= mutableListOf(getString(R.string.zone_integer_name))
        if (intent.getBooleanExtra("modulo",false)){
            if (prefs.getInt("statusModulo",-1)<0) prefs.edit().putInt("statusModulo",0).apply()
            array.add(getString(R.string.zone_modulo_name))
        }
        if (intent.getBooleanExtra("combine",false)){
            if (prefs.getInt("statusCombine",-1)<0) prefs.edit().putInt("statusCombine",0).apply()
            array.add(getString(R.string.zone_combine_name))
        }
        if (intent.getBooleanExtra("deriv",false)){
            if (prefs.getInt("statusDeriv",-1)<0) prefs.edit().putInt("statusDeriv",0).apply()
            array.add(getString(R.string.zone_deriv_name))
        }
        if (intent.getBooleanExtra("complex",false)){
            if (prefs.getInt("statusComplex",-1)<0) prefs.edit().putInt("statusComplex",0).apply()
            array.add(getString(R.string.zone_complex_name))
        }
        listView.adapter=CustomArrayAdapter(this,array.toTypedArray(),Array(array.size){0})
        continueButtonResult.setOnClickListener {
            startActivity(Intent(this,MenuZoneSelect::class.java))
            finish()
        }
    }
}