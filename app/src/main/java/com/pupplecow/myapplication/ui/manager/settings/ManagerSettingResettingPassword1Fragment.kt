package com.pupplecow.myapplication.ui.manager.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.pupplecow.myapplication.R
import kotlinx.android.synthetic.main.fragment_manager_setting_resetting_password1.*

class ManagerSettingResettingPassword1Fragment:Fragment() {

    companion object{
        fun newInstance():ManagerSettingResettingPassword1Fragment{
            return ManagerSettingResettingPassword1Fragment()
        }
        private lateinit var managerSettingResettingPassword2Fragement: ManagerSettingResettingPassword2Fragement
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_manager_setting_resetting_password1,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //인증번호 받기 버튼 클릭
        manager_resetting_password1_button1.setOnClickListener {
            //핸드폰번호 저장

            //핸드폰번호 입력했는지 확인 (빈칸이면 메시지)
            val name=manager_resetting_password1_name.text.toString()
            val phoneNumber=manager_resetting_password1_phone.text.toString()

            if(phoneNumber==""||phoneNumber.length!=11){
                val builder= AlertDialog.Builder(requireContext())
                builder.setTitle("")
                builder.setMessage("휴대폰 번호를 입력해주세요")
                builder.setPositiveButton("네",null)
                builder.show()
            }
            else if(name==""){
                val builder= AlertDialog.Builder(requireContext())
                builder.setTitle("")
                builder.setMessage("이름를 입력해주세요")
                builder.setPositiveButton("네",null)
                builder.show()
            }
            // 이름과 전화번호 모두 입력했다면
            else{
                val phoneNumberFront=manager_resetting_password1_phone.text.substring(0,3)
                val phoneNumberBehind=manager_resetting_password1_phone.text.substring(7,11)


                //핸드폰 번호로 인증번호 전송 메시지
                manager_resetting_password1_textView4.text=phoneNumberFront+" **** "+phoneNumberBehind+"로 발송하였습니다.\n비밀번호 변경을 계속 하시려면 인증번호를 입력하세요."

                //인증번호 전송하기

            }
        }

        //다음버튼 클릭
        manager_resetting_password1_button2.setOnClickListener {

            //인증번호 확인
            if(manager_resetting_password1_number.text.toString()=="1234") {

                //인증번호 확인되면
                //ManagerSettingResettingPassword2Fragement로 넘어가기
//                val intent = Intent(requireContext(),ManagerSettingResettingPassword2Fragement::class.java)
//                startActivity(intent)
                managerSettingResettingPassword2Fragement=
                    ManagerSettingResettingPassword2Fragement.newInstance()
                val transaction=activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.manager_nav_frame,
                    managerSettingResettingPassword2Fragement
                )?.addToBackStack(null)?.commit()
            }

            else{
                //인증번호 틀리면
                manager_resetting_password1_textView5.text="인증번호가 틀렸습니다."
            }
        }
    }
}