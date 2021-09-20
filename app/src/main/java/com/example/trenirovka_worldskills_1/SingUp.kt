package com.example.trenirovka_worldskills_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.doAfterTextChanged
import com.example.trenirovka_worldskills_1.`interface`.Api
import com.example.trenirovka_worldskills_1.databinding.ActivitySingUpBinding
import com.example.trenirovka_worldskills_1.model.RegistrationModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SingUp : AppCompatActivity() {

    private lateinit var binding: ActivitySingUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistration.setOnClickListener {

            binding.firstnameLayout.isErrorEnabled = false
            binding.passwordLayout.isErrorEnabled = false
            binding.emailLayout.isErrorEnabled = false
            binding.password2Layout.isErrorEnabled = false

            val retrofit = Retrofit.Builder().baseUrl("https://eda.ucmpt.ru/api/")
                .addConverterFactory(GsonConverterFactory.create()).build()

            binding.firstnameLayout.editText!!.doAfterTextChanged {
                binding.firstnameLayout.isErrorEnabled = false
            }
            binding.emailLayout.editText!!.doAfterTextChanged {
                binding.emailLayout.isErrorEnabled = false
            }
            binding.passwordLayout.editText!!.doAfterTextChanged {
                binding.passwordLayout.isErrorEnabled = false
            }
            binding.password2Layout.editText!!.doAfterTextChanged {
                binding.password2Layout.isErrorEnabled = false
            }

            retrofit.create(Api::class.java).registration(
                binding.firstnameLayout.editText!!.text.toString(),
                binding.emailLayout.editText!!.text.toString(),
                binding.passwordLayout.editText!!.text.toString()
            )
                .enqueue(object : Callback<RegistrationModel> {
                    override fun onResponse(
                        call: Call<RegistrationModel>,
                        response: Response<RegistrationModel>
                    ) {

                        if (binding.textInputpassword.text.toString() != binding.password2Layout.editText!!.text.toString()) {
                            binding.password2Layout.error = "Пароли не совпадают"
                            binding.password2Layout.isErrorEnabled = true
                        }

                        if (response.body()!!.data.isEmpty() || binding.password2Layout.isErrorEnabled) {
                            binding.firstnameLayout.isErrorEnabled = true
                            binding.passwordLayout.isErrorEnabled = true
                            binding.emailLayout.isErrorEnabled = true


                            binding.firstnameLayout.error =
                                response.body()!!.errors["firstname"].toString()

                            binding.emailLayout.error = response.body()!!.errors["email"].toString()

                            binding.passwordLayout.error =
                                response.body()!!.errors["password"].toString()


                        } else {
                            finish()
                        }

                    }

                    override fun onFailure(call: Call<RegistrationModel>, t: Throwable) {
                        Log.i("onFailure", t.message.toString())
                    }
                })
        }


    }
}