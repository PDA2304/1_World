package com.example.trenirovka_worldskills_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.trenirovka_worldskills_1.`interface`.Api
import com.example.trenirovka_worldskills_1.databinding.ActivityPasswordRecavariBinding
import com.example.trenirovka_worldskills_1.databinding.ActivitySingUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class PasswordRecavariActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasswordRecavariBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPasswordRecavariBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textInputLayout.editText!!.doAfterTextChanged {
            binding.textInputLayout.isErrorEnabled = false
        }

        binding.btnResetPassword.setOnClickListener {

            var retrofit = Retrofit.Builder().baseUrl("https://eda.ucmpt.ru/api/")
                .addConverterFactory(GsonConverterFactory.create()).build()

            retrofit.create(Api::class.java)
                .resetPassword(binding.emailTextinputedit.text.toString())
                .enqueue(object : Callback<Object> {
                    override fun onResponse(call: Call<Object>, response: Response<Object>) {
                        if(binding.emailTextinputedit.text!!.isEmpty())
                            binding.textInputLayout.also {
                                it.isErrorEnabled = true
                                it.error = "Поле пустое"
                            }
                        else{
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<Object>, t: Throwable) {
                    }
                })

        }

    }


}