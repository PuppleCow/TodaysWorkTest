package com.pupplecow.myapplication.ui.manager.announcement

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pupplecow.myapplication.R


class ManagerAnnouncementListFragment:Fragment() {


    private lateinit var managerCreateAnnouncementActivity: ManagerCreateAnnouncementActivity
    //private lateinit var auth : FirebaseAuth


    var AnnouncementList= arrayListOf<AnnouncementData>(


    )
    companion object {
        fun newInstance(): ManagerAnnouncementListFragment {
            return ManagerAnnouncementListFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//==============뷰바인딩으로 바꿔주세요=================================
//
//        val listAdapter = ManagerAnnouncementListAdapter(requireContext()){
//                Announcement->
//            val intent = Intent(requireContext(), ManagerAnnouncementActivity::class.java)
//
//            startActivity(intent)
//
//        }
//        announcementlist_recyclerview.adapter = listAdapter
//
//
//        val lm = LinearLayoutManager(requireContext())
//        announcementlist_recyclerview.layoutManager = lm
//        announcementlist_recyclerview.setHasFixedSize(true)
//
//
///*
//
//        database.addValueEventListener(object : ValueEventListener {
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for(data in snapshot.children){
//                    val modelResult=data.getValue(AnnouncementData::class.java)
//                    AnnouncementList.apply {
//                        add(
//                            AnnouncementData(
//                            month = modelResult?.month.toString(),
//                            date = modelResult?.date.toString(),
//                            title = modelResult?.title.toString(),
//                            category = modelResult?.category.toString(),
//                            uid = modelResult?.uid.toString(),
//                            content = modelResult?.content.toString()
//                        )
//                        )
//                        //ManagerComplaintList.complaintList=complaintList
//                    }
//                }
//                listAdapter.notifyDataSetChanged()
//
//            }
//        })
//        FirebaseDatabase.getInstance()
//
// */
//
//
//
//        fragment_announcement_news.text = "뉴스 제목입니다"
//
//        fragment_announcement_news.setOnClickListener {
//            var intent =
//                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.news1.kr/articles/?4386702"))
//            startActivity(intent)
//
//        }
//
//        floatingActionButton.setOnClickListener {
//            val intent = Intent(requireContext(), ManagerCreateAnnouncementActivity::class.java)
//            startActivity(intent)
//        }
//
//        /*  //공지사항 목록 페이지로 넘어가기
//                                    //AnnouncmentListFragment로 넘어가기
//                                    managerAnnouncementListFragment= ManagerAnnouncementListFragment.newInstance()
//                                    val transaction=activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.manager_nav_frame,managerAnnouncementListFragment)?.addToBackStack(null)?.commit()
//
//
//        * */
//



    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:Bundle?): View?{
        val view=inflater.inflate(R.layout.fragment_manager_announcement_list,container,false)
        return view
    }
}