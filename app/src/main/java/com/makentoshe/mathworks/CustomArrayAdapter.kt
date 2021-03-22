package com.makentoshe.mathworks

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomArrayAdapter(context: Context, values: Array<String>, private val mask: Array<Boolean>) : ArrayAdapter<String>(
        context,
        R.layout.list_simple_element,
        R.id.list_content,
        values
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val text1 = view.findViewById(R.id.list_content) as TextView
        val text2 = view.findViewById(R.id.list_content_inactive) as TextView
        text2.text=text1.text
        if (!mask[position]) {text1.visibility=View.GONE;text2.visibility=View.VISIBLE}
        return view
    }
}