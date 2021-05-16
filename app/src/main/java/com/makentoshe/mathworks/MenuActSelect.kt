package com.makentoshe.mathworks

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
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
        backButtonActSelect.setOnClickListener { startActivity(Intent(this,MenuZoneSelect::class.java));finish()}
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
        val linksActMask=mapOf(
                "integer" to arrayOf(
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusIntegerAct1",0),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusIntegerAct2",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusIntegerAct3",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusIntegerAct4",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusIntegerBoss",-1)
                ),
                "modulo" to arrayOf(
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusModuloAct1",0),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusModuloAct2",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusModuloAct3",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusModuloAct4",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusModuloBoss",-1)
                ),
                "combine" to arrayOf(
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusCombineAct1",0),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusCombineAct2",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusCombineAct3",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusCombineAct4",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusCombineBoss",-1)
                ),
                "stats" to arrayOf(
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusStatsAct1",0),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusStatsAct2",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusStatsAct3",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusStatsAct4",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusStatsAct5",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusStatsBoss",-1)
                ),
                "float" to arrayOf(
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusFloatAct1",0),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusFloatAct2",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusFloatAct3",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusFloatAct4",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusFloatAct5",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusFloatAct6",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusFloatAct7",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusFloatBoss",-1)
                ),
                "function" to arrayOf(
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusFunctionAct1",0),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusFunctionAct2",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusFunctionAct3",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusFunctionAct4",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusFunctionAct5",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusFunctionAct6",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusFunctionAct7",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusFunctionBoss",-1)
                ),
                "triangle" to arrayOf(
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusTriangleAct1",0),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusTriangleAct2",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusTriangleAct3",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusTriangleAct4",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusTriangleAct5",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusTriangleBoss",-1)
                ),
                "plain" to arrayOf(
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusPlainAct1",0),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusPlainAct2",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusPlainAct3",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusPlainAct4",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusPlainAct5",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusPlainAct6",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusPlainAct7",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusPlainBoss",-1)
                ),
                "stereo" to arrayOf(
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusStereoAct1",0),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusStereoAct2",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusStereoAct3",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusStereoAct4",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusStereoBoss",-1)
                ),
                "deriv" to arrayOf(
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusDerivAct1",0),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusDerivAct2",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusDerivAct3",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusDerivAct4",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusDerivAct5",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusDerivBoss",-1)
                ),
                "complex" to arrayOf(
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusComplexAct1",0),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusComplexAct2",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusComplexAct3",-1),
                        PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("statusComplexBoss",-1)
                )
        )
        val actArray= Array<String>(acts){""}
        for (i in (0..acts-2)){
            actArray[i]=(getString(resources.getIdentifier(("act_${zone}_${i+1}_name"), "string", packageName)))
        }
        actArray[acts-1]=(getString(resources.getIdentifier(("boss_name"), "string", packageName)))
        val adapt= linksActMask[zone]?.let { CustomArrayAdapterAct(this,actArray, it, getString(R.string.act_unavailable)) }
        list.adapter=adapt
        list.setOnItemClickListener { _, _, position, _ ->
            val pendActivity= linksAct[zone!!]?.get(position)
            val lives=getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE).getInt("lives",-1)
            val pose= linksActMask[zone]!!.toIntArray()[position]
            if ((position!= acts-1 || lives!=0)){
                if (pose!=-1)
                {val intent= Intent(this,pendActivity)
                intent.putExtra("zone",getString(resources.getIdentifier(("zone_${zone}_name"), "string", packageName)))
                intent.putExtra("act",actArray[position])
                startActivity(intent)
                this.finish()}
            }
            else {
                Toast.makeText(applicationContext,getString(R.string.boss_unavailable),Toast.LENGTH_LONG).show()
            }
        }
        zoneTitle.text=getString(resources.getIdentifier("zone_${zone}_name", "string", packageName))
    }
}