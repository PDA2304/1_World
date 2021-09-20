package com.example.trenirovka_worldskills_1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trenirovka_worldskills_1.R
import com.example.trenirovka_worldskills_1.model.ModelProducts
import com.squareup.picasso.Picasso

class Adapter_ItemFood(
    var context: Context,
    var arrayList: ArrayList<ModelProducts>,
   var  listener: () -> Unit
) : RecyclerView.Adapter<Adapter_ItemFood.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val ImageView_Icon = view.findViewById<ImageView>(R.id.ImageView_Icon)
        val TextView_Name_Food = view.findViewById<TextView>(R.id.TextView_Name_Food)
        val TextView_Cost = view.findViewById<TextView>(R.id.TextView_Cost)


        fun onCreate(modelProducts: ModelProducts, listener: () -> Unit) {
            Picasso.get().load("https://eda.ucmpt.ru${modelProducts.photo}").fit().into(ImageView_Icon)

            TextView_Cost.text =  modelProducts.price.toString()
            TextView_Name_Food.text =  modelProducts.name_product

            itemView.setOnClickListener {
                listener()
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter_ItemFood.ViewHolder =
        ViewHolder(
          LayoutInflater.from(context).inflate(R.layout.custom_recycler_item_menu, parent,false)
        )

    override fun onBindViewHolder(holder: Adapter_ItemFood.ViewHolder, position: Int) {
        holder.onCreate(arrayList[position],listener)
    }

    override fun getItemCount(): Int = arrayList.size
}