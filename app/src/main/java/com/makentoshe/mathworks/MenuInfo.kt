package com.makentoshe.mathworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.layout_act_tasks.*

class MenuInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_act_tasks)
        headTask.text=getText(R.string.app_name)
        subheadTask.text=getText(R.string.title_info)
        continueButtonTask.text=getText(R.string.to_main)
        descrText.visibility=View.VISIBLE
        descrText.text=getText(R.string.info_text)
        taskText.text=getString(resources.getIdentifier("info_version", "string", packageName),getText(R.string.app_name),applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0).versionName)
        editTextTask.visibility=View.GONE
        progressBarTask.visibility=View.GONE
        progressBarTaskTrue.visibility= View.GONE
        continueButtonTask.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}