package com.pupplecow.myapplication.ui.worker.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pupplecow.myapplication.databinding.ActivitySettingTermsAndPolicyBinding

class SettingTermsAndPolicy : AppCompatActivity() {

    private lateinit var binding:ActivitySettingTermsAndPolicyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySettingTermsAndPolicyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}