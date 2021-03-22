package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.preference.PreferenceManager
import com.makentoshe.mathworks.ZoneCombine.*
import com.makentoshe.mathworks.ZoneFloat.*
import com.makentoshe.mathworks.ZoneFunction.*
import com.makentoshe.mathworks.ZoneInteger.*
import com.makentoshe.mathworks.ZoneModulo.*
import com.makentoshe.mathworks.ZoneStats.*
import com.makentoshe.mathworks.ZoneTriangle.*
import com.makentoshe.mathworks.ZonePlain.*
import com.makentoshe.mathworks.ZoneStereo.*
import com.makentoshe.mathworks.ZoneDeriv.*
import com.makentoshe.mathworks.ZoneComplex.*
import kotlinx.android.synthetic.main.layout_menu_act_select.*

class MenuActSelect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("themeid",R.style.AppTheme))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_menu_act_select)
        val zone=intent.getStringExtra("zone")
        val acts = when (zone){
            "integer" -> 5
            "modulo" -> 5
            "combine" -> 5
            "stats" -> 6
            "float" -> 8
            "function" -> 8
            "triangle" -> 6
            "plain" -> 8
            "stereo"-> 5
            "deriv" -> 6
            "complex" -> 4
            else -> 8
        }
        val linksAct=mapOf(
            "integer" to arrayOf(
                IntegerAct1Activity::class.java,
                IntegerAct2Activity::class.java,
                IntegerAct3Activity::class.java,
                IntegerAct4Activity::class.java,
                IntegerBossActivity::class.java
            ),
            "modulo" to arrayOf(
                ModuloAct1Activity::class.java,
                ModuloAct2Activity::class.java,
                ModuloAct3Activity::class.java,
                ModuloAct4Activity::class.java,
                ModuloBossActivity::class.java
            ),
            "combine" to arrayOf(
                CombineAct1Activity::class.java,
                CombineAct2Activity::class.java,
                CombineAct3Activity::class.java,
                CombineAct4Activity::class.java,
                CombineBossActivity::class.java
            ),
            "stats" to arrayOf(
                StatsAct1Activity::class.java,
                StatsAct2Activity::class.java,
                StatsAct3Activity::class.java,
                StatsAct4Activity::class.java,
                StatsAct5Activity::class.java,
                StatsBossActivity::class.java
            ),
            "float" to arrayOf(
                FloatAct1Activity::class.java,
                FloatAct2Activity::class.java,
                FloatAct3Activity::class.java,
                FloatAct4Activity::class.java,
                FloatAct5Activity::class.java,
                FloatAct6Activity::class.java,
                FloatAct7Activity::class.java,
                FloatBossActivity::class.java
            ),
            "function" to arrayOf(
                FunctionAct1Activity::class.java,
                FunctionAct2Activity::class.java,
                FunctionAct3Activity::class.java,
                FunctionAct4Activity::class.java,
                FunctionAct5Activity::class.java,
                FunctionAct6Activity::class.java,
                FunctionAct7Activity::class.java,
                FunctionBossActivity::class.java
            ),
            "triangle" to arrayOf(
                TriangleAct1Activity::class.java,
                TriangleAct2Activity::class.java,
                TriangleAct3Activity::class.java,
                TriangleAct4Activity::class.java,
                TriangleAct5Activity::class.java,
                TriangleBossActivity::class.java
            ),
            "plain" to arrayOf(
                PlainAct1Activity::class.java,
                PlainAct2Activity::class.java,
                PlainAct3Activity::class.java,
                PlainAct4Activity::class.java,
                PlainAct5Activity::class.java,
                PlainAct6Activity::class.java,
                PlainAct7Activity::class.java,
                PlainBossActivity::class.java
            ),
            "stereo" to arrayOf(
                StereoAct1Activity::class.java,
                StereoAct2Activity::class.java,
                StereoAct3Activity::class.java,
                StereoAct4Activity::class.java,
                StereoBossActivity::class.java
            ),
            "deriv" to arrayOf(
                DerivAct1Activity::class.java,
                DerivAct2Activity::class.java,
                DerivAct3Activity::class.java,
                DerivAct4Activity::class.java,
                DerivAct5Activity::class.java,
                DerivBossActivity::class.java
            ),
            "complex" to arrayOf(
                ComplexAct1Activity::class.java,
                ComplexAct2Activity::class.java,
                ComplexAct3Activity::class.java,
                ComplexBossActivity::class.java
            )
        )
        val actArray= Array<String>(acts){""}
        for (i in (0..acts-2)){
            actArray[i]=(getString(resources.getIdentifier(("act_${zone}_${i+1}_name"), "string", packageName)))
        }
        actArray[acts-1]=(getString(resources.getIdentifier(("boss_name"), "string", packageName)))
        val adapt= ArrayAdapter<String>(this,R.layout.list_simple_element, R.id.list_content, actArray)
        list.adapter=adapt
        list.setOnItemClickListener { _, _, position, _ ->
            val pendActivity= linksAct[zone!!]?.get(position)
            val lives=PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("lives",3)
            if (position!= acts-1 || lives!=0){
                val intent= Intent(this,pendActivity)
                intent.putExtra("zone",getString(resources.getIdentifier(("zone_${zone}_name"), "string", packageName)))
                intent.putExtra("act",actArray[position])
                startActivity(intent)
                this.finish()
            }
            else {
                Toast.makeText(applicationContext,getString(R.string.boss_unavailable),Toast.LENGTH_LONG).show()
            }
        }
        zoneTitle.text=getString(resources.getIdentifier("zone_${zone}_name", "string", packageName))
    }
}