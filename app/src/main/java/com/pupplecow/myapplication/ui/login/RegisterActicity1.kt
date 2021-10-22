package com.pupplecow.myapplication.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isGone
import androidx.core.widget.addTextChangedListener
import com.pupplecow.myapplication.databinding.ActivityRegister1Binding


class RegisterActicity1 : AppCompatActivity() {

    private lateinit var binding:ActivityRegister1Binding

    //2분 기다릴 수 있음
    companion object{
        private const val AUTH_TIMELIMIT=120L
    }

    private var mUserType: Int=-1

    private var storedVerificationId: String = ""
    private var isPhoneNumberVrify=false
    private var myUid=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityRegister1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerMessage.isGone

        //010.1234.5678
        //010-1234-5678
        val phonenumberPattern = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$".toPattern()


        binding.registerCellPhoneNumberInput.addTextChangedListener {
            //휴대폰 번호인지 아닌지 실시간 체크
            //정규표현식

            if(!phonenumberPattern.matcher(it.toString()).matches()){
                binding.registerCellPhoneNumberMessage.text="휴대전화번호를 정확하게 입력해주세요"
                //인증번호 받기 버튼 눌릴지 아닐지 결정
                binding.registerIdButton.isEnabled=false

            }else{
                binding.registerCellPhoneNumberMessage.text=""
                binding.registerIdButton.isEnabled=true
            }

        }


        binding.type1Btn.setOnClickListener {
            mUserType=1
            binding.registerTextType.text="근로 형태-일용직"
        }

        binding.type2Btn.setOnClickListener {
            mUserType=2
            binding.registerTextType.text="근로 형태-상용직"
        }
        //인증번호 받기 버튼 클릭
        binding.registerIdButton.setOnClickListener {
            //핸드폰번호 저장

            //핸드폰번호 입력했는지 확인 (빈칸이면 메시지)
            val userCellPhoneNumber=binding.registerCellPhoneNumberInput.text.toString()
            val userIDNumber=binding.registerIdInput.text.toString()
            val userName=binding.registerNameInput.text.toString()
            if(userCellPhoneNumber==""||userCellPhoneNumber.length!=11){
                val builder= AlertDialog.Builder(this)
                builder.setTitle("")
                builder.setMessage("휴대폰 번호를 입력해주세요")
                builder.setPositiveButton("네",null)
                builder.show()
            }
            else if(userIDNumber==""||userIDNumber.length!=13){
                val builder= AlertDialog.Builder(this)
                builder.setTitle("")
                builder.setMessage("주민등록번호를 입력해주세요")
                builder.setPositiveButton("네",null)
                builder.show()
            }
            else if(mUserType!=1&&mUserType!=2) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("")
                builder.setMessage("근로형태를 선택해주세요")
                builder.setPositiveButton("네", null)
                builder.show()
            }
            else if(userName==""){
                val builder= AlertDialog.Builder(this)
                builder.setTitle("")
                builder.setMessage("이름를 입력해주세요")
                builder.setPositiveButton("네",null)
                builder.show()
            }

            else{
                //val inputCellPhoneNumber
                // val userCellPhoneNumber=register_cellPhoneNumber_input.text.toString()
                val userCellPhoneNumberFront=userCellPhoneNumber.substring(0,3)
                val userCellPhoneNumberBack=userCellPhoneNumber.substring(7,11)


                //핸드폰 번호로 인증번호 전송 메시지
                //register_message.text=register_cellPhoneNumber_input.text
                binding.registerMessage.text=userCellPhoneNumberFront+" **** "+userCellPhoneNumberBack+"로 인증번호가 전송되었습니다.\n회원가입을 계속하시려면 인증번호를 입력하세요."
                binding.registerMessage.visibility= View.VISIBLE

                //인증번호 전송하기
                startPhoneNumberVerification()

            }
        }

        binding.registerNumberButton.setOnClickListener{
//            val phoneCredention= PhoneAuthProvider.getCredential(
//                storedVerificationId,
//                binding.registerNumberInput.text.toString()
//            )

        }

        //다음버튼 클릭
        binding.registerButton1.setOnClickListener {

            //핸드폰번호 입력했는지 확인 (빈칸이면 메시지)
            val userCellPhoneNumber=binding.registerCellPhoneNumberInput.text.toString()
            val userIDNumber=binding.registerIdInput.text.toString()
            val userName=binding.registerNameInput.text.toString()
            if(userCellPhoneNumber==""||userCellPhoneNumber.length!=11){
                val builder= AlertDialog.Builder(this)
                builder.setTitle("")
                builder.setMessage("휴대폰 번호를 입력해주세요")
                builder.setPositiveButton("네",null)
                builder.show()
            }
            else if(userIDNumber==""||userIDNumber.length!=13){
                val builder= AlertDialog.Builder(this)
                builder.setTitle("")
                builder.setMessage("주민등록번호를 입력해주세요")
                builder.setPositiveButton("네",null)
                builder.show()
            }
            else if(mUserType!=1&&mUserType!=2){
                val builder= AlertDialog.Builder(this)
                builder.setTitle("")
                builder.setMessage("근로형태를 선택해주세요")
                builder.setPositiveButton("네",null)
                builder.show()
            }
            else if(userName==""){
                val builder= AlertDialog.Builder(this)
                builder.setTitle("")
                builder.setMessage("이름를 입력해주세요")
                builder.setPositiveButton("네",null)
                builder.show()
            }else if(!isPhoneNumberVrify) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("")
                builder.setMessage("휴대전화번호 인증을 완료해주세요")
                builder.setPositiveButton("네", null)
                builder.show()
            }
            else{


                //인증번호 확인


            }

        }

    }
    //폰넘버 앞에 +82 붙여야함
    private fun startPhoneNumberVerification() {

       // var phoneNumber="+82"+register_cellPhoneNumber_input.text.toString().replace(".","").replace("-","").substring(1)


    }




}