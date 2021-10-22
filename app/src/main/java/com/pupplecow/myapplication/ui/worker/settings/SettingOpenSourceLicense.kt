package com.pupplecow.myapplication.ui.worker.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pupplecow.myapplication.databinding.ActivitySettingOpenSourceLicenseBinding

class SettingOpenSourceLicense : AppCompatActivity() {

    private lateinit var binding:ActivitySettingOpenSourceLicenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySettingOpenSourceLicenseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}