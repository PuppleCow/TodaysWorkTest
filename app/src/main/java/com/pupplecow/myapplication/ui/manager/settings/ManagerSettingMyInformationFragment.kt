package com.pupplecow.myapplication.ui.manager.settings

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pupplecow.myapplication.R
import com.pupplecow.myapplication.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_manager_setting_my_information.*

class ManagerSettingMyInformationFragment: Fragment() {

    companion object{
        fun newInstance(): ManagerSettingMyInformationFragment{
            return ManagerSettingMyInformationFragment()
        }
        //private lateinit var managerSettingResettingPassword1Fragment: ManagerSettingResettingPassword1Fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_manager_setting_my_information,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 내 정보 저장
        fragment_manager_setting2_button1.setOnClickListener {

            // 입력하지 않은 것이 있다면
            if(fragment_manager_setting2_editTextTextPhoneNumber.text.toString()==" " ||
                fragment_manager_setting2_editTextTextBirth.text.toString()=="" ||
                fragment_manager_setting2_editTextTextOtherNumber.text.toString()==" " ||
                fragment_manager_setting2_editTextTextBloodType.text.toString() ==" " ) {
                val t1=Toast.makeText(requireContext(),"입력하지 않은 정보가 있습니다.",Toast.LENGTH_SHORT)
                t1.show()
            }
                else {
                val t1 = Toast.makeText(requireContext(), "정보가 저장되었습니다", Toast.LENGTH_SHORT)
                t1.show()
            }
        }

        // 비밀번호 변경으로 이동
        // ManagerResettingPassword1 액티비티로 이동
        fragment_manager_setting2_button4.setOnClickListener{
            val intent=Intent(requireContext(),ManagerResettingPassword1::class.java)
            startActivity(intent)
//            managerSettingResettingPassword1Fragment= ManagerSettingResettingPassword1Fragment.newInstance()
//            val transaction=activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.manager_nav_frame,
//                managerSettingResettingPassword1Fragment
//            )?.addToBackStack(null)?.commit()

        }

        // 회원탈퇴
        fragment_manager_setting2_button2.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("회원탈퇴 하시겠습니까?")
            builder.setMessage("※ 탈퇴하시면 기존 정보는 초기화됩니다. ※")

            val listener = object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when (which) {

                        // '예' 버튼 눌렀을 때
                        DialogInterface.BUTTON_NEGATIVE -> {
                            val builder =
                                AlertDialog.Builder(requireContext())
                            builder.setTitle("정상적으로 회원탈퇴 되었습니다.")
                            builder.setMessage("지금까지 이용해주셔서 감사합니다")
                            builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->

                                // '확인' 버튼 누르면 '로그인페이지'으로 이동
                                val home_intent= Intent(activity, LoginActivity::class.java)
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
        fragment_manager_setting2_button3.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("")
            builder.setMessage("로그아웃 하시겠습니까?")

            val listener = object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when (which) {

                        // '예' 버튼 눌렀을 때
                        DialogInterface.BUTTON_NEGATIVE -> {
                            val builder1 =
                                AlertDialog.Builder(requireContext(),)
                            builder1.setTitle("정상적으로 로그아웃 되었습니다.")
                            builder1.setMessage("메인화면으로 돌아갑니다")
                            builder1.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->

                                // '확인' 버튼 누르면 '로그인페이지'으로 이동
                                val home_intent= Intent(activity, LoginActivity::class.java)
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

//                //산업안전 뉴스 제목,링크 불러오기
//        fragment_manager_setting2_news.text="뉴스 제목입니다."
//
//        fragment_manager_setting2_news.setOnClickListener {
//            var intent =
//                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.news1.kr/articles/?4386702"))
//            startActivity(intent)
//        }
    }

}



