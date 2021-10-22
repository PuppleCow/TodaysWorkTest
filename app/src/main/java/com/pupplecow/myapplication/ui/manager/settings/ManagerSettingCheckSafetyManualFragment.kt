package com.pupplecow.myapplication.ui.manager.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pupplecow.myapplication.R
import kotlinx.android.synthetic.main.fragment_manager_setting_check_safety_manual.*

class ManagerSettingCheckSafetyManualFragment :Fragment(){

   // private var binding:FragmentManagerSettingCheckSafetyManualBinding?=null

    companion object{
        fun newInstance(): ManagerSettingCheckSafetyManualFragment{
            return ManagerSettingCheckSafetyManualFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val binding=FragmentManagerSettingCheckSafetyManualBinding.inflate(inflater,container,false)
//        return binding.root
        val view=inflater.inflate(R.layout.fragment_manager_setting_check_safety_manual,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 안전 메뉴얼 확인 구현 ~~!!


// ====================== 약관 내용 보이기 ========================

        // 개인정보 처리 동의 버튼
//        binding?.fragmentManagerSetting5TextView2?.setOnClickListener {
//            binding?.fragmentManagerSetting5Scrollview?.visibility=View.VISIBLE
//
//            binding?.fragmentManagerSetting5TextView6?.text="약관1 \n" +
//                    "개인정보\n처리\n동의\n"
//        }
        // 개인정보 처리 동의 버튼
                fragment_manager_setting5_textView2.setOnClickListener {
                    fragment_manager_setting5_scrollview.visibility=View.VISIBLE

                    fragment_manager_setting5_textView6.text="약관1 \n" +
                    "개인정보\n처리\n동의\n"
        }



//        // 개인정보 지속 처리 동의 버튼
//        binding?.fragmentManagerSetting5TextView3?.setOnClickListener {
//            binding?.fragmentManagerSetting5Scrollview?.visibility=View.VISIBLE
//
//            binding?.fragmentManagerSetting5TextView6?.text="약관2 \n" +
//                    "개인정보\n지속\n처리\n동의\n"
//        }
         //개인정보 지속 처리 동의 버튼
        fragment_manager_setting5_textView3.setOnClickListener {
            fragment_manager_setting5_scrollview.visibility=View.VISIBLE

            fragment_manager_setting5_textView6.text="약관2 \n" +
                    "개인정보\n지속\n처리\n동의\n"
        }


//        // 주민등록번호 지속 처리 동의 버튼
//        binding?.fragmentManagerSetting5TextView4?.setOnClickListener {
//            binding?.fragmentManagerSetting5Scrollview?.visibility=View.VISIBLE
//
//            binding?.fragmentManagerSetting5TextView6?.text="약관3 \n" +
//                    "주민등록번호\n지속\n처리\n동의\n"
//        }
        // 주민등록번호 지속 처리 동의 버튼
        fragment_manager_setting5_textView4.setOnClickListener {
            fragment_manager_setting5_scrollview.visibility=View.VISIBLE

            fragment_manager_setting5_textView6.text="약관3 \n" +
                    "주민등록번호\n지속\n처리\n동의\n"
        }


//        // 출근 안내 등 정보이용 동의 버튼
//        binding?.fragmentManagerSetting5TextView5?.setOnClickListener {
//            binding?.fragmentManagerSetting5Scrollview?.visibility=View.VISIBLE
//
//            binding?.fragmentManagerSetting5TextView6?.text="약관4 \n" +
//                    "출근 안내 등\n정보이용\n동의\n"
//        }
        //        // 출근 안내 등 정보이용 동의 버튼
        fragment_manager_setting5_textView5.setOnClickListener {
            fragment_manager_setting5_scrollview.visibility=View.VISIBLE

            fragment_manager_setting5_textView6.text="약관4 \n" +
                    "출근 안내 등\n정보이용\n동의\n"
        }

    }
}

