package com.pupplecow.myapplication.ui.manager.announcement

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import com.pupplecow.myapplication.R
import kotlinx.android.synthetic.main.activity_announcement.*

class ManagerAnnouncementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_announcement)

        //산업안전 뉴스 제목,링크 불러오기
        announcement_text_news.text="뉴스 제목입니다."

        announcement_text_news.setOnClickListener {
            var intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.news1.kr/articles/?4386702"))
            startActivity(intent)
        }

        //제목,공지날짜 서버에서 가져오기
        announcement_text_title.text="5/10 공지입니다."

        //이미지 가져오기(있을때 없을때 구분)
        if(true) {
            //manager_announcement_imageView.setImageResource(0)
        }
        else{
            announcement_imageView.isInvisible=true
        }


        //공지내용 서버에서 가져오기
        announcement_text_content.text="공지\n공지입니당\n공쥐\n팥쥐"


        //수정버튼 누르면
        announcement_button_edit.setOnClickListener {
            //다이얼로그
            val builder= AlertDialog.Builder(this)
            builder.setTitle("")
            builder.setMessage("해당 공지를 수정하시겠습니까?")
            var listener = object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when (p1) {
                        //"네" 눌렀을때
                        DialogInterface.BUTTON_POSITIVE -> {
                            //예누르면 공지사항 작성페이지로 이동 --> 수정공지사항은 입력되어있어야함
                            //CreateAnnounecementFragment로 넘어가기
                            val intent = Intent(this@ManagerAnnouncementActivity,
                                ManagerCreateAnnouncementActivity::class.java)
                            startActivity(intent)
                        }

                    }
                }
            }
            builder.setNegativeButton("아니오",listener)
            builder.setPositiveButton("네",listener)
            builder.show()

        }




        //삭제버튼 누르면
        announcement_button_delete.setOnClickListener {
            //다이얼로그
            val builder= AlertDialog.Builder(this)
            builder.setTitle("")
            builder.setMessage("해당 공지를 삭제하시겠습니까?")
            var listener = object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when (p1) {
                        //"네" 눌렀을때
                        DialogInterface.BUTTON_POSITIVE -> {

                            //해당 공지 서버에서 삭제하기


                            //공지사항 목록 페이지로 넘어가기
                            //ManagerAnnouncementListFragmentt로 넘어가기
                            finish()
                        }

                    }
                }
            }
            builder.setNegativeButton("아니오",listener)
            builder.setPositiveButton("네",listener)
            builder.show()
        }


        announcement_button_list.setOnClickListener {
            //공지사항 목록 페이지로 넘어가기
            //AnnouncmentListFragment로 넘어가기
           finish()
        }
    }
}