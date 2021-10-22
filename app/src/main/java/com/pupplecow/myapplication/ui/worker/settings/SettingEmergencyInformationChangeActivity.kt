package com.pupplecow.myapplication.ui.worker.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pupplecow.myapplication.R
import com.pupplecow.myapplication.databinding.ActivitySettingEmergencyInformationChangeBinding

class SettingEmergencyInformationChangeActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySettingEmergencyInformationChangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySettingEmergencyInformationChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 보호자 연락처
        binding.setting6ChangeEmergencyPhone

        // 본인 혈액형
        binding.setting6ChangeBloodType

        // 특이사항
        // 당뇨
        binding.setting6Diabetes

        // 고혈압
        binding.setting6HighBloodPressure

        // 고지혈증
        binding.setting6Hyperlipidemia

        // 기타
        binding.setting6ChangeOtherDisease

        // 완료 버튼
        binding.setting6ChangeComplete
    }
}