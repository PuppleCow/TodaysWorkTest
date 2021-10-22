package com.pupplecow.myapplication.ui.worker.announcement

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pupplecow.myapplication.Adapter.AnnouncementListAdapterActivity
import com.pupplecow.myapplication.R
import com.pupplecow.myapplication.ui.manager.announcement.AnnouncementData


//근무자 위한 list 프래그먼트
class AnnouncementListFragment:Fragment() {

    //private lateinit var announcementFragmentWorkerVer : AnnouncementFragmentWorkerVer

    var announcementList: ArrayList<AnnouncementData> = arrayListOf()




    companion object {
        fun newInstance(): AnnouncementListFragment {
            return AnnouncementListFragment()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//=====================뷰바인딩으로 변경==============================
//        floatingActionButton.visibility=View.GONE
//
//
//        val listAdapter = AnnouncementListAdapterActivity(requireContext()){
//                Announcement->
//            val intent = Intent(requireContext(), AnnouncementWorkerActivity::class.java)
//            startActivity(intent)
//            //announcementFragmentWorkerVer= AnnouncementFragmentWorkerVer.newInstance()
//            //val transaction = activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_nav_frame,announcementFragmentWorkerVer)?.addToBackStack(null)?.commit()
//
//        }
//
//        announcementlist_recyclerview.adapter = listAdapter
//
//
//        val lm = LinearLayoutManager(requireContext())
//        announcementlist_recyclerview.layoutManager = lm
//        announcementlist_recyclerview.setHasFixedSize(true)
//

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:Bundle?): View?{
        val view=inflater.inflate(R.layout.fragment_manager_announcement_list,container,false)
        return view
    }


}