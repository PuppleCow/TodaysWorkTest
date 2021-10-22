package com.pupplecow.myapplication.ui.manager.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pupplecow.myapplication.R

class ManagerSettingTermsAndPolicyFragment :Fragment(){

    companion object{
        fun newInstance():ManagerSettingTermsAndPolicyFragment{
            return ManagerSettingTermsAndPolicyFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_manager_setting_terms_and_policy,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
