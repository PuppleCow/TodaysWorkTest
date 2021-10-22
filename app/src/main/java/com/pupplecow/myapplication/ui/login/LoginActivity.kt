package com.pupplecow.myapplication.ui.login

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.pupplecow.myapplication.MainNavActivity
import com.pupplecow.myapplication.R
import com.pupplecow.myapplication.databinding.ActivityLoginBinding
import com.pupplecow.myapplication.ManagerNavActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*


class LoginActivity : AppCompatActivity() {
    private val RC_SIGN_IN: Int=1000
    private var googleSignInClient: GoogleSignInClient?=null
    private var gso: GoogleSignInOptions?=null
    private lateinit var binding:ActivityLoginBinding
    var auth = Firebase.auth
    val login_data = arrayOf( "선택","상용직", "일용직","관리자")
    //val spinner=findViewById<Spinner>(R.id.login_workpart)
    //val choice=spinner.toString()

    //선택은 클릭해도 안되는 걸로 설정하기
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if(auth.currentUser!=null){
            updateUI(auth.currentUser)
        }

        googleLoginInit()
        binding.loginGoogle.setOnClickListener {
            val signInIntent = googleSignInClient?.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }


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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.e("GoogleLogin", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.e("GoogleLogin", "Google sign in failed", e)
            }
        }
    }

    // [START auth_with_google]
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.e("GoogleLogin", "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.e("GoogleLogin", "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        //데이터베이스에 있는지 없는지 체크
        if (user != null) {
            getFcmToken()
        }
//            FirebaseApi().getUserData(user.uid) { isSuccess, message, data ->
//                Log.e("getUserData","$isSuccess $message $data")
//                if(isSuccess){
//                    //
//                    if(data==null){
//                        //회원가입 페이지로 넘어가기
//                        val intent = Intent(this@LoginActivity, RegisterActicity1::class.java)
//                        startActivity(intent)
//                        finish()
//
//                    }else{
//                        //유저타입 분기
//                        when(data.userType){
//                            0->{
//                                //관리자
//                                Toast.makeText(this@LoginActivity, "정상적으로 로그인되었습니다.", Toast.LENGTH_SHORT).show()
//
//                                val intent = Intent(this@LoginActivity, ManagerNavActivity::class.java)
//                                startActivity(intent)
//                                finish()
//                            }
//                            else->{
//                                //근무자
//                                Toast.makeText(this@LoginActivity, "정상적으로 로그인되었습니다.", Toast.LENGTH_SHORT).show()
//
//                                val intent = Intent(this@LoginActivity, MainNavActivity::class.java)
//                                startActivity(intent)
//                                finish()
//                            }
//                        }
//                    }
//                    Toast.makeText(this,isSuccess.toString()+data.toString(),Toast.LENGTH_SHORT).show()
//                }else{
//                    Toast.makeText(this,isSuccess.toString()+data?.uid.toString(),Toast.LENGTH_SHORT).show()
//                    //회원가입 페이지로 넘어가기
//                    val intent = Intent(this@LoginActivity, RegisterActicity1::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//
//            }
//        }
    }


        private fun getFcmToken() {
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result

                // Log and toast
                val msg = token.toString()
                Log.d("FCM", msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()

                //토큰은 한기기에 하나(일대일 대응)
                //리얼타임데이터베이스의 루트 -> 유저s->uid -> fcm token에 msg를 setvalue
                Firebase.database.reference
                    .child("users")
                    .child(auth.uid!!)
                    .child("fcmToken")
                    .setValue(
                        msg
                    ).addOnCompleteListener {
                        val intent=Intent(this,MainNavActivity::class.java)
                        startActivity(intent)
                    }
            })
        }

    // [END auth_with_google]

    private fun googleLoginInit() {
        // Configure Google Sign In
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

}






