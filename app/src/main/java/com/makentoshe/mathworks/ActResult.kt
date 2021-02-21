package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class ActResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_result
        )
        var score=intent.getIntExtra("score",2)
        var max=intent.getIntExtra("max",5)
        var rate=(score.toDouble()/max.toDouble()*100.0).toInt()
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
        var zoneIndex ="integer"
        zoneIndex =intent.getStringExtra("zone")
        var actIndex = intent.getIntExtra("act",0)
        var acts= linksAct[zoneIndex]!!.size
        headResult.text=getString(resources.getIdentifier("zone_${zoneIndex}_name", "string", packageName))
        if (actIndex<acts-1){subheadResult.text=getString(resources.getIdentifier(("act_${zoneIndex}_${actIndex+1}_name"), "string", packageName))}
        else {subheadResult.text=getString(resources.getIdentifier(("boss_name"), "string", packageName))}
        textResultPC.text= "$rate%"
        resultProgressBar.progress=score
        resultProgressBar.max=max
        if (rate==100) {textCongratulations.text=getString(resources.getIdentifier("result_good", "string", packageName))}
        var lives=intent.getIntExtra("lives",3)
        continueButtonResult.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            intent.putExtra("lives",lives)
            startActivity(intent)
        }
    }
}