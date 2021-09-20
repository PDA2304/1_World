package com.example.trenirovka_worldskills_1.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.trenirovka_worldskills_1.SignIn
import com.example.trenirovka_worldskills_1.databinding.FragmentAcountBinding

class AcountFragment : Fragment() {

    private var binding: FragmentAcountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAcountBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        binding!!.ButtonExit.setOnClickListener {
            var intent = Intent(root.context, SignIn::class.java)
            it.context.getSharedPreferences("settings", Context.MODE_PRIVATE).edit().also {
                it.clear()
                it.commit()
            }
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        return root
    }


}