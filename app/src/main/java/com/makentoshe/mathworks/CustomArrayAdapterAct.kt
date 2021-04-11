package com.makentoshe.mathworks

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.TextView

class CustomArrayAdapterAct(context: Context, values: Array<String>, private val mask: Array<Int>, private val replacer: String) : ArrayAdapter<String>(
        context,
        R.layout.list_simple_element_act,
        R.id.list_content,
        values
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val text1 = view.findViewById(R.id.list_content) as TextView
        val text2 = view.findViewById(R.id.list_content_inactive) as TextView
        val text3 = view.findViewById(R.id.list_content_completed) as TextView
        val progress = view.findViewById(R.id.list_act_progress) as ProgressBar
        val percentage = view.findViewById(R.id.list_act_percentage) as TextView
        text2.text=text1.text; text3.text=text1.text
        if (mask[position]!=-1)percentage.text="${mask[position]}%" else percentage.text=replacer
        when(mask[position]){
            -1->{text1.visibility=View.GONE;text2.visibility=View.VISIBLE;text3.visibility=View.GONE}
            0->{text1.visibility=View.VISIBLE;text2.visibility=View.GONE;text3.visibility=View.GONE}
            100->{text1.visibility=View.GONE;text2.visibility=View.GONE;text3.visibility=View.VISIBLE}
        }
        progress.progress=mask[position]
        return view
    }
}