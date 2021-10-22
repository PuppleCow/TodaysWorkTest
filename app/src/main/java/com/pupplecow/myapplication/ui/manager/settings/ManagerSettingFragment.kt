package com.pupplecow.myapplication.ui.manager.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pupplecow.myapplication.R
import kotlinx.android.synthetic.main.fragment_manager_setting.*

class ManagerSettingFragment: Fragment() {

    companion object{
        fun newInstance(): ManagerSettingFragment{
            return ManagerSettingFragment()
        }

        private lateinit var managerSettingMyInformationFragment: ManagerSettingMyInformationFragment
        private lateinit var managerSettingOpenSourceLicenseFragment: ManagerSettingOpenSourceLicenseFragment
        private lateinit var managerSettingTermsAndPolicyFragment: ManagerSettingTermsAndPolicyFragment
        private lateinit var managerSettingCheckSafetyManualFragment: ManagerSettingCheckSafetyManualFragment

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      val view=inflater.inflate(R.layout.fragment_manager_setting,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 내 정보 설정
        fragment_manager_setting1_textView4.setOnClickListener {
            managerSettingMyInformationFragment= ManagerSettingMyInformationFragment.newInstance()
            val tran=activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.manager_nav_frame, managerSettingMyInformationFragment)?.addToBackStack(null)?.commit()
        }

        // 오픈소스 라이센스
        fragment_manager_setting1_textView5.setOnClickListener {
            managerSettingOpenSourceLicenseFragment=ManagerSettingOpenSourceLicenseFragment.newInstance()
            val tran=activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.manager_nav_frame, managerSettingOpenSourceLicenseFragment)?.addToBackStack(null)?.commit()
        }

        // 약관 및 정책
        fragment_manager_setting1_textView6.setOnClickListener {
            managerSettingTermsAndPolicyFragment=ManagerSettingTermsAndPolicyFragment.newInstance()
            val tran=activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.manager_nav_frame, managerSettingTermsAndPolicyFragment)?.addToBackStack(null)?.commit()
        }

        // 안전 메뉴얼 확인
        fragment_manager_setting1_textView7.setOnClickListener {
            managerSettingCheckSafetyManualFragment=ManagerSettingCheckSafetyManualFragment.newInstance()
            val tran=activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.manager_nav_frame, managerSettingCheckSafetyManualFragment)?.addToBackStack(null)?.commit()
        }
    }
}