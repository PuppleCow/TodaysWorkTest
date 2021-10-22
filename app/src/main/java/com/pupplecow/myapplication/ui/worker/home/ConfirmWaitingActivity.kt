package com.pupplecow.myapplication.ui.worker.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pupplecow.myapplication.databinding.ActivityConfirmWaitingBinding


class ConfirmWaitingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmWaitingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityConfirmWaitingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //승인요청

        //승인확인후 다음페이지로 이동
        //임시(화면 누르면 넘어가게
        binding.confrimWaiting.setOnClickListener{
            finish()
        }
    }
}