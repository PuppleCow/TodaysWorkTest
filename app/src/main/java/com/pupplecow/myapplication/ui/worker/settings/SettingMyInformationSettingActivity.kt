package com.pupplecow.myapplication.ui.worker.settings

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.pupplecow.myapplication.databinding.ActivitySettingMyInformationSettingBinding
import com.pupplecow.myapplication.ui.login.ResettingPassword1
import androidx.core.view.isVisible
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.pupplecow.myapplication.data.Complaint
import com.pupplecow.myapplication.data.UserData
import com.pupplecow.myapplication.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_manager_create_announecement.*


// 메인 네비바의 SettingFragment에서 '내 정보 설정' 클릭 시 이동하게 된 액티비티


class SettingMyInformationSettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingMyInformationSettingBinding
    private var fbFirestore: FirebaseFirestore?=null
    var auth = Firebase.auth

    // 사진을 가져오기 위한 권한을 확인하는 코드
    val permission_list = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_MEDIA_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingMyInformationSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fbFirestore= FirebaseFirestore.getInstance()

        // 수정 버튼 누르면 이동하기
        binding.setting2Change.setOnClickListener {
            val intent=Intent(this,SettingMyInformationSettingChangeActivity::class.java)
            startActivity(intent)
        }


        // 파이어스토어에서 데이터 가져오기
        val docRef = fbFirestore?.collection("USER")?.document(auth.uid.toString())
        docRef?.get()
            ?.addOnSuccessListener { document ->
                if (document != null) {
                    //val userData=document.data
                    Log.e("개인정보 가져오기 성공", "DocumentSnapshot data: ${document.data}")

                    // 개인정보 가져오기 성공했을 때
                    docRef?.get().addOnSuccessListener { documentSnapshot ->
                        val information = documentSnapshot.toObject<UserData>()

                        // 이름 가져오기
                        binding.setting2MyName.text=information?.name
                        //binding.setting2MyName.text = "aaa"

                         // 전화번호 가져오기 010-1234-5678
                        var tempPhoneNum=information?.phoneNumber
                        binding.setting2MyPhone.text=tempPhoneNum


                        // 생년월일 가져오기 예) 1968년 1월 12일
                        var tempBirth=information?.birthDate
                        binding.setting2MyBirth.text=tempBirth
                    }

                } else {
                    Log.d("개인정보 등록 안 되어 있음", "No such document")
                }
            }
            ?.addOnFailureListener { exception ->
                Log.e("개인정보 가져오기 실패", "get failed with ", exception)
            }


//        // 이름 가져오기
//        binding.setting2MyName
//
//        // 전화번호 가져오기
//        binding.setting2MyPhone
//
//        // 생년월일 가져오기
//        binding.setting2MyBirth

//        // 사진 삭제 버튼 안 보이게 하기
//        binding.setting2ImageDelete2.isVisible=false
//
//        @Suppress("DEPRECATION")
//        requestPermissions(permission_list, 0)
//
//         //사진 빈칸 클릭
//        binding.setting2ImageView.setOnClickListener{
//
//            // 앨범에서 사진을 선택할 수 있는 액티비티를 실행한다.
//            val albumInternet=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            // 실행할 액티비티의 타입을 설정(이미지를 선택할 수 있는 것)
//            albumInternet.type="image/*"
//            // 선택할 파일의 타입을 지정(안드로이드 OS가 사전작업을 할 수 있도록)
//            val mimeType= arrayOf("image/*")
//            albumInternet.putExtra(Intent.EXTRA_MIME_TYPES,mimeType)
//            @Suppress("DEPRECATION")
//            startActivityForResult(albumInternet,0)
//            binding.setting2ImageDelete2.isVisible=true
//        }
//
//
//
//        binding.setting2ImageDelete2.setOnClickListener {
//            binding.setting2ImageView.setImageResource(0)
//            binding.setting2ImageDelete2.isVisible=false
//        }
//
//        // 내 정보 저장
//        binding.setting2Button1.setOnClickListener {
//
//            // 입력하지 않은 정보가 있다면
//            if(binding.setting2EditTextTextPhoneNumber.text.toString()==""||
//                binding.setting2EditTextTextBirth.text.toString()==""||
//                binding.setting2EditTextTextOtherNumber.text.toString()=="" ||
//                binding.setting2EditTextTextBloodType.text.toString()==""){
//
//                val t1 = Toast.makeText(this, "입력하지 않은 정보가 있습니다", Toast.LENGTH_SHORT)
//                t1.show()
//            }
//
//            // 사진 등록이 안 되어있다면
//            else if(binding.setting2ImageView.getDrawable()==null){
//                val t1 = Toast.makeText(this, "사진 부분을 클릭하여 사진을 등록해주세요", Toast.LENGTH_SHORT)
//                t1.show()
//            }
//            else{
//            val t1 = Toast.makeText(this, "정보가 저장되었습니다", Toast.LENGTH_SHORT)
//            t1.show()
//        }
//        }

//        // 비밀번호 변경으로 이동
//        // ResettingPassword1 액티비티로 이동
//        binding.setting2Button4.setOnClickListener{
////            val intent=Intent(requireContext(), SettingResettingPassword1Fragment::class.java)
////            startActivity(intent)
////            settingResettingPassword1Fragment= SettingResettingPassword1Fragment.newInstance()
////            val transaction=activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_nav_frame,
////                settingResettingPassword1Fragment)?.addToBackStack(null)?.commit()
//            val intent=Intent(this,ResettingPassword1::class.java)
//            startActivity(intent)
//        }

        // 회원탈퇴
        binding.setting2Button2.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("회원탈퇴 하시겠습니까?")
            builder.setMessage("※ 탈퇴하시면 기존 정보는 초기화됩니다. ※")

            val listener = object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when (which) {

                        // '예' 버튼 눌렀을 때
                        DialogInterface.BUTTON_NEGATIVE -> {
                            val builder =AlertDialog.Builder(this@SettingMyInformationSettingActivity)
                            builder.setTitle("정상적으로 회원탈퇴 되었습니다.")
                            builder.setMessage("지금까지 이용해주셔서 감사합니다")
                            builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->

                                // '확인' 버튼 누르면 '로그인페이지'으로 이동
                                val home_intent= Intent(this@SettingMyInformationSettingActivity, LoginActivity::class.java)
                                startActivity(home_intent)
                            }
                            builder.show()
                        }
                    }
                }
            }

            builder.setPositiveButton("아니요", listener)
            builder.setNegativeButton("예", listener)
            builder.show()

        }

        // 로그아웃
        binding.setting2Button3.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("")
            builder.setMessage("로그아웃 하시겠습니까?")

            val listener = object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when (which) {

                        // '예' 버튼 눌렀을 때
                        DialogInterface.BUTTON_NEGATIVE -> {
                            val builder1 =
                                AlertDialog.Builder(this@SettingMyInformationSettingActivity)
                            builder1.setTitle("정상적으로 로그아웃 되었습니다.")
                            builder1.setMessage("메인화면으로 돌아갑니다")
                            builder1.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->

                                // '확인' 버튼 누르면 '로그인페이지'으로 이동
                                val home_intent= Intent(this@SettingMyInformationSettingActivity, LoginActivity::class.java)
                                startActivity(home_intent)


                            }
                            builder1.show()
                        }
                    }
                }
            }

            builder.setPositiveButton("아니요", null)
            builder.setNegativeButton("예", listener)
            builder.show()
        }
    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        @Suppress("DEPRECATION")
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if(resultCode == AppCompatActivity.RESULT_OK){
//            // 선택한 이미지의 경로 데이터를 관리하는 Uri 객체를 추출한다.
//            val uri = data?.data
//
//            if(uri != null){
//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
//                    // 안드로이드 10버전 부터
//                    val source = ImageDecoder.createSource(this.contentResolver, uri)
//                    val bitmap = ImageDecoder.decodeBitmap(source)
//                    binding.setting2ImageView.setImageBitmap(bitmap)
//                } else {
//                    // 안드로이드 9버전 까지
//                    val cursor = this.contentResolver.query(uri, null, null, null, null)
//                    if(cursor != null){
//                        cursor.moveToNext()
//                        // 이미지 경로를 가져온다.
//                        @Suppress("DEPRECATION")
//                        val index = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
//                        val source = cursor.getString(index)
//                        // 이미지를 생성한다.
//                        val bitmap = BitmapFactory.decodeFile(source)
//                        binding.setting2ImageView.setImageBitmap(bitmap)
//                    }
//                }
//            }
//        }
//    }
}
