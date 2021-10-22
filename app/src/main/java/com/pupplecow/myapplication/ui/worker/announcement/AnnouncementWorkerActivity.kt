
package com.pupplecow.myapplication.ui.worker.announcement

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.pupplecow.myapplication.R
import kotlinx.android.synthetic.main.activity_announcement.*


class AnnouncementWorkerActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_announcement)


        announcement_button_edit.visibility = View.GONE
        announcement_button_delete.visibility = View.GONE


        //산업안전 뉴스 제목,링크 불러오기
        announcement_text_news.text="최신 뉴스입니다."

        announcement_text_news.setOnClickListener {
            var intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.news1.kr/articles/?4386702"))
            startActivity(intent)
        }


        if(true) {
            //manager_announcement_imageView.setImageResource(0)
        }
        else{
            announcement_imageView.isInvisible=true
        }

        //제목,공지날짜 서버에서 가져오기
        announcement_text_title.text="5/10 공지입니다."


        //공지내용 서버에서 가져오기
        announcement_text_content.text="공지\n공지입니당\n공쥐\n팥쥐"




        //목록버튼
        announcement_button_list.setOnClickListener {
            //공지사항 목록 페이지로 넘어가기

            finish()
            //AnnouncmentListFragment로 넘어가기
            //announcementListFragment= AnnouncementListFragment.newInstance()
            //val transaction=activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_nav_frame,announcementListFragment)?.addToBackStack(null)?.commit()

            //val transaction= activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
            //activity?.supportFragmentManager?.popBackStack()


        }

    }



}



