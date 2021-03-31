package com.makentoshe.mathworks

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomArrayAdapter(context: Context, values: Array<String>, private val mask: Array<Int>) : ArrayAdapter<String>(
        context,
        R.layout.list_simple_element,
        R.id.list_content,
        values
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val text1 = view.findViewById(R.id.list_content) as TextView
        val text2 = view.findViewById(R.id.list_content_inactive) as TextView
        val text3 = view.findViewById(R.id.list_content_completed) as TextView
        text2.text=text1.text; text3.text=text1.text
        when(mask[position]){
            -1->{text1.visibility=View.GONE;text2.visibility=View.VISIBLE;text3.visibility=View.GONE}
            0->{text1.visibility=View.VISIBLE;text2.visibility=View.GONE;text3.visibility=View.GONE}
            1->{text1.visibility=View.GONE;text2.visibility=View.GONE;text3.visibility=View.VISIBLE}
        }
        return view
    }
}