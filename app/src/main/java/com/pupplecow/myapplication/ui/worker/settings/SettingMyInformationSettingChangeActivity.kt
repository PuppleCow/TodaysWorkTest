package com.pupplecow.myapplication.ui.worker.settings

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AlertDialog

import com.pupplecow.myapplication.data.UserData
import com.pupplecow.myapplication.databinding.ActivitySettingMyInformationSettingChangeBinding

import java.util.*

class SettingMyInformationSettingChangeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingMyInformationSettingChangeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingMyInformationSettingChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)





        //사진첨부버튼
        binding.setting2ChangeImage.setOnClickListener {
            // 앨범에서 사진을 선택할 수 있는 액티비티를 실행한다.
            val albumIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            // 실행할 액티비티의 타입을 설정(이미지를 선택할 수 있는 것)
            albumIntent.type = "image/*"
            // 선택할 파일의 타입을 지정(안드로이드 OS가 사전작업을 할 수 있도록)
            val mimeType = arrayOf("image/*")
            albumIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
            @Suppress("DEPRECATION")
            startActivityForResult(albumIntent, 0)
            //complaint_button_image_delete.visibility=View.VISIBLE
        }


//        // 이름
//        binding.setting2ChangeName
//
//        // 생년월일
//        binding.setting2ChangeBirth

        // 인증번호 받기 ( 전화번호 입력 )
        binding.setting2ChangeWritePhone

        // 인증번호 받기 버튼
        binding.setting2ChangeGetNumber.setOnClickListener {

        }

        // 인증번호 입력
        binding.setting2ChangeComparePhone

        // 완료 버튼
        binding.setting2ChangeComplete.setOnClickListener {


            // 전화번호가 비어있는지 확인
            if (binding.setting2ChangeWritePhone.text.toString() == "") {

                //비어있으면 작성해주세요 다이얼로그
                val builder = AlertDialog.Builder(this)
                builder.setTitle("내 정보 변경")
                builder.setMessage("전화번호를 입력해주세요")
                builder.setPositiveButton("확인", null)
                builder.show()
            } else {
                // 정보를 저장하시겠습니까? 다이얼로그
                val builder = AlertDialog.Builder(this)
                builder.setTitle("내 정보 변경")
                builder.setMessage("변경된 정보를 저장장하시겠습니까?")
                var listener = object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        when (p1) {
                            //"네" 눌렀을때
                            // 정보 업데이트

                            DialogInterface.BUTTON_POSITIVE -> {


                                setResult(RESULT_OK)
                                finish()
                            }
                        }
                    }
                }
                builder.setNegativeButton("아니오", null)
                builder.setPositiveButton("네", listener)
                builder.show()
            }


        }
    }

}
