package com.pupplecow.myapplication.ui.worker.home.complaint

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.pupplecow.myapplication.databinding.ActivityMyComplaintBinding



class ComplaintActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyComplaintBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMyComplaintBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //산업안전 뉴스 제목,링크 불러오기
        //mycomplaint_text_news.text="뉴스 제목입니다."
        //val data = intent.getSerializableExtra("uid")
        //사진 서버에서 가져오기
        //이미지 가져오기(있을때 없을때 구분)
        if(true) {
            //binding.MyComplaintImageView.setImageResource(0)
        }
        else{
            binding.MyComplaintImageView.visibility= View.VISIBLE
        }

//        mycomplaint_text_news.setOnClickListener {
//            var intent =
//                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.news1.kr/articles/?4386702"))
//            startActivity(intent)
//        }

        //서버에서 내용받아오기


        //삭제버튼
        binding.MyComplaintButtonDelete.setOnClickListener {
            //민원삭제 다이얼로그
            val builder= AlertDialog.Builder(this)
            builder.setTitle("민원삭제")
            builder.setMessage("민원내용을 삭제하시겠습니까?")
            var listener = object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when (p1) {
                        //"네" 눌렀을때
                        DialogInterface.BUTTON_POSITIVE -> {

                            //서버에서 민원삭제

                        }

                    }
                }
            }
            builder.setNegativeButton("아니오",listener)
            builder.setPositiveButton("네",listener)
            builder.show()
        }

        //수정버튼
        binding.MyComplaintButtonEdit.setOnClickListener {
            //민원삭제 다이얼로그
            val builder= AlertDialog.Builder(this)
            builder.setTitle("민원수정")
            builder.setMessage("민원내용을 수정하시겠습니까?")
            var listener = DialogInterface.OnClickListener { p0, p1 ->
                when (p1) {
                    //"네" 눌렀을때
                    DialogInterface.BUTTON_POSITIVE -> {


                        //다음페이지로 넘어가기
                        //민원작성페이지로 넘어가기
                        val intent = Intent(this@ComplaintActivity, WriteComplaintActivity::class.java)
                        startActivity(intent)
                    }

                }
            }
            builder.setNegativeButton("아니오",listener)
            builder.setPositiveButton("네",listener)
            builder.show()
        }

        //목록버튼
        binding.MyComplaintButtonList.setOnClickListener {
            //목록페이지로 넘어가기
            //myComplaintListFragment= MyComplaintListFragment.newInstance()
            finish()

        }



    }
}