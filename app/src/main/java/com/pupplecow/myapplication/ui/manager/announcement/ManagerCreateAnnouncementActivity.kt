package com.pupplecow.myapplication.ui.manager.announcement

import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pupplecow.myapplication.R
import kotlinx.android.synthetic.main.activity_complaint.*
import kotlinx.android.synthetic.main.fragment_manager_create_announecement.*
import java.util.*

class ManagerCreateAnnouncementActivity : AppCompatActivity() {
    private var uid:String =""

    var Firestore : FirebaseFirestore? = null
    var Auth : FirebaseAuth? = null


    val category = arrayOf( "카테고리 선택","모집", "A","B","C")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_create_announcement)

        Firestore = FirebaseFirestore.getInstance()

        /*
        if(intent.hasExtra("uid")){
            uid = intent.getStringExtra("uid").toString()
        }


         */

        //카테고리 스피너
        val categoryAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,category)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        manager_createAnnouncement_spinner.adapter= categoryAdapter


        //산업안전 뉴스 제목,링크 불러오기
        manager_create_announcement_text_news.text = "뉴스 제목입니다."

        manager_create_announcement_text_news.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.news1.kr/articles/?4386702"))
            startActivity(intent)
        }

        manager_create_announcement_button_image_delete.isVisible=false
        //이미지
        manager_create_announcement_imageView.setOnClickListener{
            // 앨범에서 사진을 선택할 수 있는 액티비티를 실행한다.
            val albumIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            // 실행할 액티비티의 타입을 설정(이미지를 선택할 수 있는 것)
            albumIntent.type = "image/*"
            // 선택할 파일의 타입을 지정(안드로이드 OS가 사전작업을 할 수 있도록)
            val mimeType = arrayOf("image/*")
            albumIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
            @Suppress("DEPRECATION")
            startActivityForResult(albumIntent, 0)
            manager_create_announcement_button_image_delete.isVisible=true
        }

        manager_create_announcement_button_image_delete.setOnClickListener {
            manager_create_announcement_imageView.setImageResource(0)
            manager_create_announcement_button_image_delete.isVisible=false
        }






        //등록하기버튼
        manager_create_announcement_button_enroll.setOnClickListener {
            val selectCategory = category[manager_createAnnouncement_spinner.selectedItemPosition]




            //제목란 비어있는지 확인
            if (manager_create_announcement_editText_title.text.toString() == "") {
                //비어있으면 작성해주세요 다이얼로그
                val builder = AlertDialog.Builder(this)
                builder.setTitle("공지사항")
                builder.setMessage("제목을 작성해주세요")
                builder.setPositiveButton("네", null)
                builder.show()
            } else {
                //textarea비어있는지 확인

                if (manager_create_announcement_editTextTextMultiLine.text.toString() == "") {
                    //비어있으면 작성해주세요 다이얼로그
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("공지사항")
                    builder.setMessage("공지사항을 작성해주세요")
                    builder.setPositiveButton("네", null)
                    builder.show()
                }
                else if(selectCategory=="카테고리 선택"){
                    //카테고리 선택이 안됐을 경우
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("카테고리")
                    builder.setMessage("카테고리를 선택해주세요")
                    builder.setPositiveButton("네", null)
                    builder.show()

                }
                else {
                    //공지사항작성 다이얼로그
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("공지사항")
                    builder.setMessage("공지사항을 등록하시겠습니까?")
                    var listener = object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            when (p1) {
                                //"네" 눌렀을때
                                DialogInterface.BUTTON_POSITIVE -> {

                                    //공지시간 표시
                                    val homeNow = Calendar.getInstance()
                                    val year = homeNow.get(Calendar.YEAR).toString()
                                    val month = homeNow.get(Calendar.MONTH).toString()
                                    val date = homeNow.get(Calendar.DATE).toString()
                                    val hour = homeNow.get(Calendar.HOUR).toString()
                                    val minute = homeNow.get(Calendar.MINUTE).toString()


                                    //서버에 사진,공지 제목,공지내용,공지날짜,시간,공지한 사람 정보 저장,필독정보
                                    //파이어베이스

                                    val dataInput = AnnouncementData(
                                        uid,
                                        month,date,selectCategory,
                                        manager_create_announcement_editText_title.text.toString(),
                                        manager_create_announcement_editTextTextMultiLine.text.toString()

                                    )

                                    //공지사항 목록이 쭉 나열됨
                                    Firestore?.collection("announcement")?.add(dataInput)
                                    setResult(RESULT_OK)
                                    finish()




                                    //공지사항 목록 페이지로 넘어가기
                                    //AnnouncmentListFragment로 넘어가기
                                    val intent = Intent(this@ManagerCreateAnnouncementActivity,
                                        ManagerAnnouncementActivity::class.java)
                                    startActivity(intent)

                                }

                            }
                        }
                    }
                    builder.setNegativeButton("아니오", listener)
                    builder.setPositiveButton("네", listener)
                    builder.show()
                }
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        @Suppress("DEPRECATION")
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == AppCompatActivity.RESULT_OK){
            // 선택한 이미지의 경로 데이터를 관리하는 Uri 객체를 추출한다.
            val uri = data?.data

            if(uri != null){
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                    // 안드로이드 10버전 부터
                    val source = ImageDecoder.createSource(this.contentResolver, uri)
                    val bitmap = ImageDecoder.decodeBitmap(source)
                    manager_create_announcement_imageView.setImageBitmap(bitmap)
                } else {
                    // 안드로이드 9버전 까지
                    val cursor = this.contentResolver.query(uri, null, null, null, null)
                    if(cursor != null){
                        cursor.moveToNext()
                        // 이미지 경로를 가져온다.
                        @Suppress("DEPRECATION")
                        val index = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                        val source = cursor.getString(index)
                        // 이미지를 생성한다.
                        val bitmap = BitmapFactory.decodeFile(source)
                        manager_create_announcement_imageView.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }
}