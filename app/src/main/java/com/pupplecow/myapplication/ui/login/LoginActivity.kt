package com.pupplecow.myapplication.ui.login

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.pupplecow.myapplication.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding

    val login_data = arrayOf( "선택","상용직", "일용직","관리자","임시")
    //val spinner=findViewById<Spinner>(R.id.login_workpart)
    //val choice=spinner.toString()

    //선택은 클릭해도 안되는 걸로 설정하기
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val workpartAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, login_data)
        workpartAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //login_workpart.adapter = workpartAdapter
        binding.loginWorkpart.adapter = workpartAdapter


//       binding.loginShowButton.setOnClickListener{
//           //비밀번호 숨기기 해제
//           if(binding.loginShowButton.isChecked) {
//               binding.loginEditTextPassword.inputType=InputType.TYPE_CLASS_TEXT
//               binding.loginShowButton.setBackgroundDrawable(
//                   getDrawable(R.drawable.ic_baseline_visibility_24)
//               )
//
//
//           }
//           else{
//               binding.loginEditTextPassword.inputType=InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
//
//               binding.loginShowButton.setBackgroundDrawable(
//                   getDrawable(R.drawable.ic_baseline_visibility_off_24)
//               )
//
//           }
//
//        }
//
//
//
//        login_register_button.setOnClickListener {
//            val builder = AlertDialog.Builder(this)
//            builder.setTitle("아직 회원이 아니세요?")
//            builder.setMessage("회원가입하시겠습니까?")
//            var listener = object : DialogInterface.OnClickListener {
//                override fun onClick(p0: DialogInterface?, p1: Int) {
//                    when (p1) {
//                        DialogInterface.BUTTON_POSITIVE -> {
//                            val intent =
//                                Intent(this@LoginActivity, RegisterActicity1::class.java)
//                            startActivity(intent)
//                        }
//                    }
//                }
//            }
//            builder.setNegativeButton("아니오", listener)
//            builder.setPositiveButton("네", listener)
//            builder.show()
//
//
//        }
//        login_button10.setOnClickListener {
//            val intent = Intent(this@LoginActivity, ResettingPassword1::class.java)
//            startActivity(intent)
//        }
//        login_button.setOnClickListener {
//
//
//            //spinner그룹
//            val workpart = login_data[login_workpart.selectedItemPosition]
//
//            //로그인 성공했을 경우(id와 password와 일용직/상용직 매치되는 정보 있으면)
//            //휴대폰 번호가 입력되지 않았을 경우
//            if (login_editTextPhone.text.toString() == "") {
//            val builder = AlertDialog.Builder(this)
//            builder.setMessage("휴대폰 번호를 입력해주세요")
//            builder.setPositiveButton("확인", null)
//            builder.show()
//
//        }
//        //비밀번호가 입력되지 않았을 경우
//
//        else if (login_editTextPassword.text.toString() == "") {
//            val builder = AlertDialog.Builder(this)
//            builder.setMessage("비밀번호를 입력해주세요")
//            builder.setPositiveButton("확인", null)
//            builder.show()
//        }
//            else if(workpart=="상용직"||workpart=="일용직"){
//            if (login_editTextPhone.text.toString() == "01087347954" && login_editTextPassword.text.toString() == "991109") {
//                Toast.makeText(this@LoginActivity, "정상적으로 로그인되었습니다.", Toast.LENGTH_SHORT).show()
//
//                val intent = Intent(this@LoginActivity, MainNavActivity::class.java)
//                startActivity(intent)
//                finish()
//
//            }
//            else {
//                val builder = AlertDialog.Builder(this)
//                builder.setTitle("틀렸습니다")
//                builder.setMessage("휴대폰 번호와 비밀번호를 확인해주세요")
//                builder.setPositiveButton("확인", null)
//                builder.show()
//            }
//            }
//            else if(workpart=="관리자") {
//                //관리자로 로그인할 경우
//                if (login_editTextPhone.text.toString() == "01087347954" && login_editTextPassword.text.toString() == "991109") {
//                    Toast.makeText(this@LoginActivity, "정상적으로 로그인되었습니다.", Toast.LENGTH_SHORT).show()
//
//                    val intent = Intent(this@LoginActivity, ManagerNavActivity::class.java)
//                    startActivity(intent)
//                    finish()
//
//                }
//                else {
//                    val builder = AlertDialog.Builder(this)
//                    builder.setTitle("틀렸습니다")
//                    builder.setMessage("휴대폰 번호와 비밀번호를 확인해주세요")
//                    builder.setPositiveButton("확인", null)
//                    builder.show()
//                }
//            }
//            else if(workpart=="선택"){
//                val builder = AlertDialog.Builder(this)
//                builder.setTitle("")
//                builder.setMessage("상용직/일용직/관리자를 선택해주세요")
//                builder.setPositiveButton("확인", null)
//                builder.show()
//            }
//
//            //<로그인 실패했을 경우>
//
//
//            //휴대폰 번호가 입력되지 않았을 경우
//            else if (login_editTextPhone.text.toString() == "") {
//                val builder = AlertDialog.Builder(this)
//                builder.setMessage("휴대폰 번호를 입력해주세요")
//                builder.setPositiveButton("확인", null)
//                builder.show()
//
//            }
//            //비밀번호가 입력되지 않았을 경우
//
//            else if (login_editTextPassword.text.toString() == "") {
//                val builder = AlertDialog.Builder(this)
//                builder.setMessage("비밀번호를 입력해주세요")
//                builder.setPositiveButton("확인", null)
//                builder.show()
//            }
//
//
//            //휴대폰 or 비밀번호 틀렸을 경우
//            else {
//                val builder = AlertDialog.Builder(this)
//                builder.setTitle("틀렸습니다")
//                builder.setMessage("휴대폰 번호와 비밀번호를 확인해주세요")
//                builder.setPositiveButton("확인", null)
//                builder.show()
//            }
//
//
//        }
    }




}






