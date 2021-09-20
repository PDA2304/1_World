package com.example.trenirovka_worldskills_1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.trenirovka_worldskills_1.R
import com.example.trenirovka_worldskills_1.`interface`.Api
import com.example.trenirovka_worldskills_1.adapter.Adapter_Categories
import com.example.trenirovka_worldskills_1.adapter.Adapter_Restoran
import com.example.trenirovka_worldskills_1.databinding.FragmentMenuBinding
import com.example.trenirovka_worldskills_1.model.PlacesModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var retrofit =
            Retrofit.Builder().baseUrl("https://eda.ucmpt.ru/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()



        retrofit.create(Api::class.java).places().enqueue(object :
            retrofit2.Callback<ArrayList<PlacesModel>> {
            override fun onResponse(
                call: Call<ArrayList<PlacesModel>>,
                response: Response<ArrayList<PlacesModel>>
            ) {
                var adapter = Adapter_Restoran(
                    response.body()!!, root
                        .context
                ){
                    var bundle = Bundle()
                    bundle.putSerializable("test",it)
                    Navigation.findNavController(root).navigate(R.id.action_navigation_menu_to_restoranFragment,bundle)
                }
                activity!!.runOnUiThread {
                    _binding!!.RecyclerViewRestoran.also {
                        it.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<PlacesModel>>, t: Throwable) {
            }
        })


        return root
    }


}