package com.pupplecow.myapplication.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.pupplecow.myapplication.databinding.ActivityResettingPassword1Binding

class ResettingPassword1 : AppCompatActivity() {
    private lateinit var binding: ActivityResettingPassword1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityResettingPassword1Binding.inflate(layoutInflater)
        setContentView(binding.root)


        // 이름, 핸드폰 번호 본인 여부 확인 필요


        //인증번호 받기 버튼 클릭
        binding.resetting1Button1.setOnClickListener {
            //핸드폰번호 저장

            //핸드폰번호 입력했는지 확인 (빈칸이면 메시지)
            val name=binding.resetting1Name.text.toString()
            val phoneNumber=binding.resetting1Phone.text.toString()

            if(phoneNumber==""||phoneNumber.length!=11){
                val builder= AlertDialog.Builder(this)
                builder.setTitle("")
                builder.setMessage("휴대폰 번호를 입력해주세요")
                builder.setPositiveButton("네",null)
                builder.show()
            }
            else if(name==""){
                val builder= AlertDialog.Builder(this)
                builder.setTitle("")
                builder.setMessage("이름를 입력해주세요")
                builder.setPositiveButton("확인",null)
                builder.show()
            }
            // 이름과 전화번호 모두 입력했다면
            else{
                val phoneNumberFront=binding.resetting1Phone.text.substring(0,3)
                val phoneNumberBehind=binding.resetting1Phone.text.substring(7,11)


                //핸드폰 번호로 인증번호 전송 메시지
                binding.resetting1TextView5.text=phoneNumberFront+" **** "+phoneNumberBehind+"로 발송하였습니다.\n비밀번호 변경을 계속 하시려면 인증번호를 입력하세요."

                //인증번호 전송하기

            }
        }

        //다음버튼 클릭
        binding.resetting1Button2.setOnClickListener {

            //인증번호 확인
            if(binding.resetting1Number.text.toString()=="1234") {

                //인증번호 확인되면
                //RegisterActivity_2로 넘어가기
                val intent = Intent(this, ResettingPassword2::class.java)
                startActivity(intent)

//                // 뒤로 가기 ( 설정 첫 화면 까지)
//                finish()
            }

            else{
                //인증번호 틀리면
                binding.resetting1TextView5.text="인증번호가 틀렸습니다."
            }
        }
    }
}