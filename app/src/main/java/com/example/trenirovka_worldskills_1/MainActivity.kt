package com.example.trenirovka_worldskills_1

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.system.Os.remove
import android.text.method.TextKeyListener.clear
import android.util.Log
import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.trenirovka_worldskills_1.`interface`.Api
import com.example.trenirovka_worldskills_1.databinding.ActivityMain2Binding
import com.example.trenirovka_worldskills_1.model.PlacesModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var edit  =  getSharedPreferences("settings", Context.MODE_PRIVATE).edit()
        edit.putString("data",intent.getStringExtra("data"))
        edit.commit()
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity__main)

        navView.setupWithNavController(navController)


    }
}