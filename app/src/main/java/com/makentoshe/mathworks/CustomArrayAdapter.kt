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
        if (!mask[position]) text1.setTextColor(Color.GRAY)
        return view
    }
}