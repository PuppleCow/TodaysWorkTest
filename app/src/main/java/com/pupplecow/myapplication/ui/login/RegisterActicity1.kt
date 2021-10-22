package com.pupplecow.myapplication.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.pupplecow.myapplication.MainNavActivity
import com.pupplecow.myapplication.R
import com.pupplecow.myapplication.data.UserData

import com.pupplecow.myapplication.databinding.ActivityRegister1Binding
import com.pupplecow.myapplication.ManagerNavActivity
import kotlinx.android.synthetic.main.activity_register1.*
import java.util.concurrent.TimeUnit

class RegisterActicity1 : AppCompatActivity() {

    private lateinit var binding:ActivityRegister1Binding

    //2분 기다릴 수 있음
    companion object{
        private const val AUTH_TIMELIMIT=120L
    }

    private var mUserType: Int=-1
    private var resendToken:PhoneAuthProvider.ForceResendingToken?=null
    private var storedVerificationId: String = ""
    private var isPhoneNumberVrify=false
    private var myUid=""

    val mAuth=Firebase.auth  //어스는 앱 전체에서 지원되고 있음 ( 이미 구글 로그인 객체가 생성되어있음)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register1)

        binding= ActivityRegister1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        myUid=mAuth.uid.toString()
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

//        //123456-7890123
//        register_id_input.addTextChangedListener {
//            it.toString().replace("-","")
//            if(it.toString().length>6){
//                register_id_input.setText(
//                it.toString().substring(0,6)+"-"+it.toString().substring(6)
//                )
//            }
//        }


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
            val phoneCredention= PhoneAuthProvider.getCredential(
                storedVerificationId,
                binding.registerNumberInput.text.toString()
            )
            verifyPhoneNumberWithCode(phoneCredention)
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
                updateUserData()

                //인증번호 확인


            }

        }

    }
    //폰넘버 앞에 +82 붙여야함
    private fun startPhoneNumberVerification() {

        var phoneNumber="+82"+register_cellPhoneNumber_input.text.toString().replace(".","").replace("-","").substring(1)

        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(AUTH_TIMELIMIT, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            verifyPhoneNumberWithCode(credential)

            //즉시인증,자동인등
            //여기서는 이거 필요 없음
            //이콜백은 두가지 상황일떄
            //1. 실기기로 테스트일때 내폰이면 인증번호 입력할 필요 없음
            //2. 구글 플레이 서비스가 자동적으로 체크해서 유저 액션 없이
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            //Log.d(TAG, "onVerificationCompleted:$credential")
            //폰번호로 가입을 시도
            //파이어 베이스에 어센틱 올라갈 필요 없음
            //signInWithPhoneAuthCredential(credential)

        }

        override fun onVerificationFailed(e: FirebaseException) {
            //오류났을때
            //폰넘버 포맷이 잘못 된경우
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            //Log.w(TAG, "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            //문자 발송 되었을때
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            //Log.d(TAG, "onCodeSent:$verificationId")

            // Save verification ID and resending token so we can use them later
            //인증 코드
            storedVerificationId = verificationId
            //재전송
            resendToken = token
        }




    }

    //
//    private fun verifyPhoneNumberWithCode(phoneAuthCredential: PhoneAuthCredential){
//
//
//
//
//    }

    private fun verifyPhoneNumberWithCode(credential: PhoneAuthCredential) {

        mAuth.currentUser!!.linkWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //인증성공
                    isPhoneNumberVrify=true
                    binding.registerNumberAuth.isVisible=true
                    updateUserData()
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                } else {
                    //실패
                    Toast.makeText(this,"인증번호를 다시 확인해주세요",Toast.LENGTH_SHORT).show()

                    // Sign in failed, display a message and update the UI
                    //Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Log.e("Exciprtoni",task.exception?.message.toString())
                    }
                    // Update UI
                }
            }
    }

    private fun updateUserData() {

        //인증번호 확인되면
        //이메일도 저장
        //이름, 주민등록번호, 전화번호 저장
        val userCellPhoneNumber=binding.registerCellPhoneNumberInput.text.toString()
        val userId=binding.registerIdInput.text.toString()
        val userName=binding.registerNameInput.text.toString()

        //프로바이더 : 인증제공 업체
        //appli

        //아래와 완전히 똑같은 코드이지만 사이에 코드 들어갈수도 있고 복잡해짐
        //val user1=UserData()
        //user1.uid=mAuth.uid

        val user= UserData().apply{
            uid=myUid
            email=mAuth.currentUser?.email
            name=userName
            photoUrl=mAuth.currentUser?.photoUrl.toString()
            providerData= try {
                mAuth.currentUser?.providerData?.get(1)?.providerId //0:파이어베이스, 1: 구글닷컴
            }catch (e:Exception){
                mAuth.currentUser?.providerData?.get(0)?.providerId
            }


            //로그인 방식
            //fcmToken=  //푸시 알림
            phoneNumber=userCellPhoneNumber //01012341234

//                    val birthPrefix=userIDNumber.split("-")[1].substring(0,1)  //-> 주민등록번호 뒷자리 한자리 뽑아옴
//                    var birthPostfix=userIDNumber.split("-")[0]  // 990727
            //userIDNumber.split("-")[0] //19990727-1234567 -> [990727,1234567]

            //9001011234567 ->6번쨰 글짜 뽑아내기
            val birthPrefix=userId.substring(6,7)  //1,2,3,4
            var birthPostfix=userId.substring(0,6) // 990727
            Log.e("birth","$userId$birthPrefix$birthPostfix")


            birthDate= (if(birthPrefix=="1"||birthPrefix=="2")"19" else "20")+birthPostfix
            userType= mUserType //0: 관리자, 1:일용직 , 2:상용직
            ssid=userId  //990727-1234567
        }

        //유저객체 입력


        //파이어베이스 데이터 베이스 루트
        Firebase.database.reference.child("users").child(myUid!!).setValue(user)
            .addOnSuccessListener {

                //mAuth.signOut()
                //Toast.makeText(this,"")

                //finish()

                if(mUserType==0){
                    val intent=Intent(this, ManagerNavActivity::class.java)
                    startActivity(intent)
                }else{
                    val intent=Intent(this,MainNavActivity::class.java)
                    startActivity(intent)
                }


            }


    }


}