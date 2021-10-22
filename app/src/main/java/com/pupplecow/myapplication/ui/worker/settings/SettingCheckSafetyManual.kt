package com.pupplecow.myapplication.ui.worker.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pupplecow.myapplication.databinding.ActivitySettingCheckSafetyManualBinding

class SettingCheckSafetyManual : AppCompatActivity() {

    private lateinit var binding:ActivitySettingCheckSafetyManualBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySettingCheckSafetyManualBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}