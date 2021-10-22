package com.pupplecow.myapplication.ui.manager.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import com.pupplecow.myapplication.databinding.FragmentManagerHomeBinding
import com.pupplecow.myapplication.ui.manager.announcement.ManagerAnnouncementListFragment
import com.pupplecow.myapplication.ui.manager.home.Complaint.ManagerComplaintListActivity

import kotlinx.android.synthetic.main.fragment_manager_home.*


class ManagerHomeFragment:Fragment() {


    private var _binding:FragmentManagerHomeBinding?=null
    private val binding get() = _binding!!

    private lateinit var managerAnnouncementListFragment : ManagerAnnouncementListFragment
    val Manage_SelectGroup = arrayOf("긴급알림 그룹 선택","모든 그룹","인천항만 하역","인천항만 하역","인천항만 하역")
    //직접 쓰기-> 따로 페이지 이동해야 함.
    val Manage_SelectNotif = arrayOf("긴급알림 종류 선택","모든 작업 일시 중단","공지사항 필독","직접 쓰기")

    companion object {
        fun newInstance(): ManagerHomeFragment {
            return ManagerHomeFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //산업안전 뉴스 제목,링크 불러오기
        binding.managerHomeTextNews.text="뉴스 제목입니다."

        binding.managerHomeTextNews.setOnClickListener {
            var intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.news1.kr/articles/?4386702"))
            startActivity(intent)
        }

        //긴급알림 그룹 선택 스피너
        val SelectGroupAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,Manage_SelectGroup)
        SelectGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.managerHomeSelectGroupSp.adapter= SelectGroupAdapter

        //긴급알림 종류 선택 스피너
        val SelectNotifAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,Manage_SelectNotif)
        SelectGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.managerHomeElectNotifSp.adapter= SelectNotifAdapter

        //직접입력칸 안보이게 하기
        binding.managerHomeEditText.isInvisible=true
        //알림종류 선택 - 직접입력 선택 리스너
        binding.managerHomeElectNotifSp.onItemSelectedListener= object :AdapterView.OnItemSelectedListener {
            override fun onItemSelected( parent: AdapterView<*>?,view: View?,position: Int,id: Long ) {
                Manage_SelectNotif.get(position)?.let {
                    if (position==3)
                        binding.managerHomeEditText.visibility= View.VISIBLE
                    else
                        binding.managerHomeEditText.visibility= GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }

        //긴급 알림 버튼
        binding.managerHomeEmergencyButton.setOnClickListener {
            //근무자 그룹
            val Manage_Group=Manage_SelectGroup[binding.managerHomeSelectGroupSp.selectedItemPosition]
            //알림 종류 선택
            var Manage_Notif=Manage_SelectNotif[binding.managerHomeElectNotifSp.selectedItemPosition]
            //직접쓰기면 Manage_Notif가 editText에서 내용 받아옴
            if(Manage_Notif=="직접 쓰기"){
                 Manage_Notif=binding.managerHomeEditText.text.toString()
            }

            if (Manage_Group == "긴급알림 그룹 선택") {
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("근무자 그룹을 선택해주세요")
                builder.setPositiveButton("확인", null)
                builder.show()
            } else if (Manage_Notif == "긴급알림 종류 선택") {
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("긴급 알림 메시지를 선택해주세요")
                builder.setPositiveButton("확인", null)
                builder.show()
            }

            else {
                Log.e("NOTIF","긴급 알림 로그 : $Manage_Group 에 $Manage_Notif 알림 보내기")
                //긴급 알림 보내기!!









            }



        }
        //근무자 현황 버튼
        binding.managerHomeWorkerButton.setOnClickListener {
            //다음페이지로 넘어가기
            //WorkersStatusFragment로 넘어가기
            val intent = Intent(requireContext(), ManagerWorkersStatusActivity::class.java)
            startActivity(intent)
        }

        binding.managerHomeComplaintList.setOnClickListener {
            //다음페이지로 넘어가기
            //ManagerComplaintListFragment로 넘어가기
            val intent = Intent(requireContext(), ManagerComplaintListActivity::class.java)
            startActivity(intent)
        }

        //inner class SpListner: AdapterView.OnItemSele

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:Bundle?): View?{
        _binding= FragmentManagerHomeBinding.inflate(inflater,container,false)
        return binding.root
    }




}


