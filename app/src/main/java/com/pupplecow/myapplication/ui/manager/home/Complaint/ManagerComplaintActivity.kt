package com.pupplecow.myapplication.ui.manager.home.Complaint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pupplecow.myapplication.R
import com.pupplecow.myapplication.databinding.ActivityManagerComplaintBinding
import com.pupplecow.myapplication.databinding.ActivityRegister2Binding
import kotlinx.android.synthetic.main.activity_manager_complaint.*


class ManagerComplaintActivity : AppCompatActivity() {
    private lateinit var binding:ActivityManagerComplaintBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_complaint)
        binding= ActivityManagerComplaintBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //서버에서 제목,내용,날짜받아오기
        binding.managerMyComplaintTextTitle.text="민원제목입니다."
        binding.managerMyComplaintTextContent.text="민원 내용입니다."



//        //산업안전 뉴스 제목,링크 불러오기
//        manager_MyComplaint_text_news.text="뉴스 제목입니다."
//
//        manager_MyComplaint_text_news.setOnClickListener {
//            var intent =
//                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.news1.kr/articles/?4386702"))
//            startActivity(intent)
//        }

        //목록버튼

        binding.managerMyComplaintButtonList.setOnClickListener {
            finish()
        }

    }
}