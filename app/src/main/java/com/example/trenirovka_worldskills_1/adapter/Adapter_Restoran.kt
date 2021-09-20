package com.example.trenirovka_worldskills_1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trenirovka_worldskills_1.R
import com.example.trenirovka_worldskills_1.model.PlacesModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.util.ArrayList

class Adapter_Restoran(var array: ArrayList<PlacesModel>, var context: Context, val listener : (PlacesModel) -> Unit) :
    RecyclerView.Adapter<Adapter_Restoran.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var TextView = view.findViewById<TextView>(R.id.TextView_Name)
        var ProgressBar = view.findViewById<ProgressBar>(R.id.ProgressBar_Icon)
        var ImageView = view.findViewById<ImageView>(R.id.ImageView_Icon)

        fun onCreate(placesModel: PlacesModel,  listener : (PlacesModel) -> Unit) {
            TextView.text = placesModel.place_name
            Picasso.get().load("https://eda.ucmpt.ru"+placesModel.place_photo).fit().into(ImageView, object :Callback{
                override fun onSuccess() {
                    ProgressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {

                }
            })
            itemView.setOnClickListener {
                listener(placesModel)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter_Restoran.ViewHolder =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.custom_recycler_restoran, parent,
            false
            )
        )


    override fun onBindViewHolder(holder: Adapter_Restoran.ViewHolder, position: Int) {
       holder.onCreate(array[position], listener)
    }

    override fun getItemCount(): Int = array.size
}