package com.example.trenirovka_worldskills_1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.trenirovka_worldskills_1.R
import com.google.android.material.chip.Chip
import java.util.zip.Inflater

class Adapter_Categories(
    var context: Context,
    var array: ArrayList<HashMap<String, String>>,
    var listener: (Int?) -> Unit
) :
    RecyclerView.Adapter<Adapter_Categories.ViewHolder>() {

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        val RadioButton = view.findViewById<CheckBox>(R.id.toggleButtonCategories)

        fun onCreate(hashMap: HashMap<String, String>, listener: (Int) -> Unit) {

            RadioButton.text = hashMap["category_name"]

            RadioButton.setOnClickListener {
                listener(hashMap["id"]!!.toInt())

                CompoundButton?.isChecked = false
                CompoundButton = it as CompoundButton
                if (!it.isChecked) {
                    CompoundButton = null
                }
            }

        }

    }

    private var CompoundButton: CompoundButton? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter_Categories.ViewHolder = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.custom_categories_item_menu, parent, false)
    )

    override fun onBindViewHolder(holder: Adapter_Categories.ViewHolder, position: Int) {
        holder.onCreate(array[position], listener)
    }

    override fun getItemCount(): Int = array.size
}

