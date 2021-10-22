package com.pupplecow.myapplication.ui.manager.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.pupplecow.myapplication.R
import kotlinx.android.synthetic.main.fragment_manager_setting_resetting_password2.*

class ManagerSettingResettingPassword2Fragement:Fragment() {

    companion object{
        fun newInstance(): ManagerSettingResettingPassword2Fragement{
            return ManagerSettingResettingPassword2Fragement()
        }
        private lateinit var managerSettingMyInformationFragment: ManagerSettingMyInformationFragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_manager_setting_resetting_password2,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 새로운 비밀번호 입력받은 후, 기존 비밀번호에서 변경


        // 다음버튼 눌렀을때
        manager_resetting_password2_button1.setOnClickListener {
            //비밀번호 서로 같은지 확인하기
            val password = manager_resetting_password2_password.text.toString()
            val passwordCheck = manager_resetting_password2_password2.text.toString()



            // 영어 , 숫자 ,특수문자 포함 8~15 글자 중 하나라도 안지키면

            // 숫자, 문자, 특수문자 모두 포함 (8~15자)
            // ^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,15}.$

//            String pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,10}$";
//
//            영어 대소문자가 한개이상 들어가 있는가 ?  숫자가 한개이상 들어가 있는가 ?? 특수문자가 한개이상 들어가 있는가 ?
//
//            영어부터 숫자 특수문자를 입력 받을것이고,  8개 이상 10개 이하 의숫자를 받아야 한다.
            //
//            val s:String="^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,}$";
//            val s="^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{8,15}.\$"
//            val b_password:Boolean= Pattern.matches(s,password)
//            val b_passwordCheck:Boolean=Pattern.matches(s,passwordCheck)



            // ====================> 비밀번호 조건: 6자리 & 숫자로만 <=====================

            if (manager_resetting_password2_password.length() != 6 || manager_resetting_password2_password2.length() != 6) {
                manager_resetting_password2_textView4.text = "글자 수가 맞지 않습니다. 다시 입력해주세요."
                manager_resetting_password2_textView5.text = ""
            }else if(isNumOnly(password)==false || isNumOnly(passwordCheck)==false){
                // 숫자로만 이루어져있는 지 확인
                manager_resetting_password2_textView4.text = "숫자로만 입력해주세요."
                manager_resetting_password2_textView5.text = ""
            }
            else if (password != passwordCheck) {
                // 비밀번호 다를 때

                manager_resetting_password2_textView4.text = "비밀번호가 맞지 않습니다. 다시 입력해주세요."
                manager_resetting_password2_textView5.text = ""
            } else {

                //비밀번호 같을때
                //"비밀번호 변경이 정상적으로 완료되었습니다." 메세지

                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("")
                builder.setMessage("비밀번호 변경이 정상적으로 완료되었습니다")
                builder.setPositiveButton("확인", null)
                builder.show()

                // '관리자 탭'으로 이동
                // ManageActivity로 넘어가기---(x)

                // 메인 -> 메인 설정 ( 내 정보 설정 ) , 관리자 -> 관리자 설정 ( 내 정보 설정 )
//                val intent = Intent(requireContext(),ManagerSettingMyInformationFragment::class.java)
//                startActivity(intent)
                managerSettingMyInformationFragment= ManagerSettingMyInformationFragment.newInstance()
                val transaction=activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.manager_nav_frame,
                    managerSettingMyInformationFragment
                )?.addToBackStack(null)?.commit()
            }
        }

    }

    // 숫자로만 되어있는 지 판별
    fun isNumOnly(s:String):Boolean{
        for(item in s){
            if(item=='0'|| item=='1'|| item=='2'|| item=='3'|| item=='4'||
                item=='5'|| item=='6'|| item=='7'|| item=='8'|| item=='9'){
                return true
            }
        }
        return false
    }
}


