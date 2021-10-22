
package com.pupplecow.myapplication.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class CheckInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //뷰바인딩으로 바꿔주세요~!
//
//        //완료 버튼 클릭 시 정보 전송
//        //어디 페이지로 이동? -> 안전매뉴얼퀴즈로 이동(QuizActivity2)
//        check_in_button2.setOnClickListener {
//            //완료 버튼 클릭 시 정보 전송
//            //어디 페이지로 이동? -> 안전매뉴얼퀴즈로 이동(QuizActivity2)
//            if ((check_in_radioButton3.isChecked || check_in_radioButton4.isChecked) && (check_in_radioButton.isChecked || check_in_radioButton2.isChecked) && (check_in_radioButton5.isChecked || check_in_radioButton6.isChecked)) {
//
//                if ((check_in_radioButton4.isChecked == true) && (check_in_radioButton.isChecked == true) && (check_in_radioButton6.isChecked == true)){
//                val intent =
//                    Intent(this@CheckInActivity, SafetyManualQuizActivity1::class.java)
//                startActivity(intent)
//                finish()
//                }
//                else{
//
//                    val builder = AlertDialog.Builder(this)
//                    builder.setMessage("작업장 입장 조건에 맞지 않는 답변이 있습니다.")
//                    builder.setPositiveButton("확인", null)
//                    builder.show()
//                }
//
//            }
//
//            else {
//                        val builder = AlertDialog.Builder(this)
//                        builder.setMessage("퀴즈를 완료하지 않았습니다.")
//                        builder.setPositiveButton("확인", null)
//                        builder.show()
//                    }
//        }
//
        }
    }


