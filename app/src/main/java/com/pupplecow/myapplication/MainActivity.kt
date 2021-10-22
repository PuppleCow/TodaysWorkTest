package com.pupplecow.myapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pupplecow.myapplication.databinding.ActivityMainBinding
import com.pupplecow.myapplication.ui.login.LoginActivity



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //로그인
        binding.button.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        //메인 네비바
        binding.button7.setOnClickListener {
            val intent = Intent(this, MainNavActivity::class.java)
            startActivity(intent)
        }


        //관리자 네비바
        binding.button8.setOnClickListener{
            val intent=Intent(this, ManagerNavActivity::class.java)
            startActivity(intent)
        }



    }




}