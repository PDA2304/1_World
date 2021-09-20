package com.example.trenirovka_worldskills_1.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trenirovka_worldskills_1.`interface`.Api
import com.example.trenirovka_worldskills_1.adapter.Adapter_Categories
import com.example.trenirovka_worldskills_1.adapter.Adapter_ItemFood
import com.example.trenirovka_worldskills_1.databinding.FragmentRestoranBinding
import com.example.trenirovka_worldskills_1.model.ModelProducts
import com.example.trenirovka_worldskills_1.model.PlacesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RestoranFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentRestoranBinding.inflate(inflater, container, false)

        var model = arguments?.getSerializable("test") as PlacesModel
        binding.TextViewName.text = model.place_name


        var retrofit =
            Retrofit.Builder().baseUrl("https://eda.ucmpt.ru/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()


        retrofit.create(Api::class.java).categorys_placeId(model.id.toString())
            .enqueue(object : Callback<ArrayList<HashMap<String, String>>> {
                override fun onResponse(
                    call: Call<ArrayList<HashMap<String, String>>>,
                    response: Response<ArrayList<HashMap<String, String>>>
                ) {

                    var adapter = Adapter_Categories(binding.root.context, response.body()!!) {

                        retrofit.create(Api::class.java).products_placeId_categoryId(model.id, it!!)
                            .enqueue(object : Callback<ArrayList<ModelProducts>> {
                                override fun onResponse(
                                    call: Call<ArrayList<ModelProducts>>,
                                    response: Response<ArrayList<ModelProducts>>
                                ) {

                                    var adapter =
                                        Adapter_ItemFood(binding.root.context, response.body()!!) {
                                            val test = BottomShitFragment()
                                            test.show(parentFragmentManager,"")
                                        }
                                    var recycler = binding.RecyclerViewFood
                                    recycler.adapter = adapter
                                    recycler.layoutManager =
                                        GridLayoutManager(binding.root.context, 2).apply {
                                            orientation = LinearLayoutManager.VERTICAL
                                        }
                                    recycler.setHasFixedSize(true)

                                }

                                override fun onFailure(
                                    call: Call<ArrayList<ModelProducts>>,
                                    t: Throwable
                                ) {
                                    Log.e("error", t.message.toString())

                                }
                            })

                    }


                    binding.categoriesRecyclerView.also {
                        it.adapter = adapter
                    }
                }

                override fun onFailure(
                    call: Call<ArrayList<HashMap<String, String>>>,
                    t: Throwable
                ) {
                }
            })




        retrofit.create(Api::class.java).products_placeId(model.id)
            .enqueue(object : Callback<ArrayList<ModelProducts>> {
                override fun onResponse(
                    call: Call<ArrayList<ModelProducts>>,
                    response: Response<ArrayList<ModelProducts>>
                ) {
                    Toast.makeText(binding.root.context, "", Toast.LENGTH_SHORT).show()
                    var adapter = Adapter_ItemFood(binding.root.context, response.body()!!) {
                        val test = BottomShitFragment()
                        test.show(parentFragmentManager,"")
                    }
                    activity!!.runOnUiThread {
                        var recycler = binding.RecyclerViewFood
                        recycler.adapter = adapter
                        recycler.layoutManager = GridLayoutManager(binding.root.context, 2).apply {
                            orientation = LinearLayoutManager.VERTICAL
                        }
                        recycler.setHasFixedSize(true)
                    }

                }

                override fun onFailure(call: Call<ArrayList<ModelProducts>>, t: Throwable) {
                    Log.e("error", t.message.toString())

                }
            })

        return binding.root
    }

}