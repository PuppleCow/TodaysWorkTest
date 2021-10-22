package com.pupplecow.myapplication.ui.worker.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pupplecow.myapplication.databinding.ActivitySafetyManualQuiz3Binding

class SafetyManualQuizActivity3 : AppCompatActivity() {
    private lateinit var binding:ActivitySafetyManualQuiz3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySafetyManualQuiz3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 확인 버튼 ( 작업장(홈) 으로 가기 )
        binding.quiz3Button3.setOnClickListener{
            finish()
        }
    }
}