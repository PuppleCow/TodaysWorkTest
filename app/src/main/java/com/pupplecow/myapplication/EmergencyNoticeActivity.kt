package com.pupplecow.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_emergency_notice.*

class EmergencyNoticeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency_notice)

        //알림 내용에따른 긴급메시지 설정
        emergency_notice_text_message.text="사고 발생"

        //확인버튼 누르면 알람,현재화면 종료
        emergency_notice_button_confirm.setOnClickListener {


            finish()

        }


    }
}