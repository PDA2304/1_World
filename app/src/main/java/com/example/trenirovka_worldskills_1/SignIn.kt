package com.example.trenirovka_worldskills_1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.trenirovka_worldskills_1.`interface`.Api
import com.example.trenirovka_worldskills_1.databinding.SignInBinding
import com.example.trenirovka_worldskills_1.model.LoginModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignIn : AppCompatActivity() {

    private lateinit var binding: SignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var sharedPreferences =
            getSharedPreferences("settings", Context.MODE_PRIVATE).getString("data","")

        if (sharedPreferences != "") {
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("data", sharedPreferences)
            startActivity(intent)
        }

        binding.btnRegistration.setOnClickListener {
            var intent = Intent(this, SingUp::class.java)
            startActivity(intent)
        }

        binding.passwrodRecavari.setOnClickListener {
            var intent = Intent(this, PasswordRecavariActivity::class.java)
            startActivity(intent)
        }

        binding.btnInput.setOnClickListener {

            binding.TextInputLayoutPasswrod.isEnabled = false
            binding.TextInputLayoutEmail.isEnabled = false

            var retrofit = Retrofit.Builder().baseUrl("https://eda.ucmpt.ru/api/")
                .addConverterFactory(GsonConverterFactory.create()).build()

            retrofit.create(Api::class.java).login(
                binding.TextInputEditTextEmail.text.toString(),
                binding.TextInputEditTextPassword.text.toString()
            ).enqueue(object : Callback<LoginModel> {
                override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                    if (response.body()!!.data.isEmpty()) {
                        binding.TextInputLayoutPasswrod.isEnabled = true
                        binding.TextInputLayoutPasswrod.error = response.body()!!.error["email"]
                        binding.TextInputLayoutEmail.isEnabled = true
                        binding.TextInputLayoutEmail.error =
                            response.body()!!.error["password"]
                    } else {
                        var intent = Intent(this@SignIn, MainActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.putExtra("data", response.body()!!.data)
                        Toast.makeText(this@SignIn, response.body()!!.data, Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                }
            })

        }
    }
}