package com.example.trenirovka_worldskills_1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trenirovka_worldskills_1.R
import com.example.trenirovka_worldskills_1.databinding.FragmentBottomShitBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomShitFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = FragmentBottomShitBinding.inflate(inflater,container,false)

        return binding.root
    }

}