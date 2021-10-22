package com.pupplecow.myapplication.ui.worker.settings

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pupplecow.myapplication.databinding.ActivitySettingEmergencyInformaionBinding

class SettingEmergencyInformationActivity: AppCompatActivity() {

    private lateinit var binding:ActivitySettingEmergencyInformaionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySettingEmergencyInformaionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 수정 버튼 누르면 이동하기
        binding.setting6Change.setOnClickListener {
            val intent=Intent(this,SettingEmergencyInformationChangeActivity::class.java)
            startActivity(intent)
        }

        // 보호자 연락처 가져오기
        binding.setting6EmergencyPhone

        // 혈액형
        binding.setting6BloodType

        // 특이사항
        // 당뇨
        binding.setting6Diabetes

        // 고혈압
        binding.setting6HighBloodPressure

        // 고지혈증
        binding.setting6Hyperlipidemia

        // 기타
        binding.setting6OtherDisease

    }
}