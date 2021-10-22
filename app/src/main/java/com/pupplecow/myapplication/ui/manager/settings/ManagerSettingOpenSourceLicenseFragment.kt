package com.pupplecow.myapplication.ui.manager.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pupplecow.myapplication.R

class ManagerSettingOpenSourceLicenseFragment: Fragment() {

    companion object{
        fun newInstance(): ManagerSettingOpenSourceLicenseFragment{
            return ManagerSettingOpenSourceLicenseFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_manager_setting_open_source_license,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}

