package com.pupplecow.myapplication.ui.safetyManual

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pupplecow.myapplication.R

class SafetyManualFragment:Fragment() {

    companion object {
        fun newInstance(): SafetyManualFragment {
            return SafetyManualFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    override fun onCreateView(inflater: LayoutInflater,container:ViewGroup?,savedInstanceState:Bundle?): View?{
        val view=inflater.inflate(R.layout.fragment_safety_manual,container,false)
        return view
    }



}