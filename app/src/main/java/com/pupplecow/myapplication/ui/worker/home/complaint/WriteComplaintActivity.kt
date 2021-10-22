package com.pupplecow.myapplication.ui.worker.home.complaint
import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.pupplecow.myapplication.databinding.ActivityComplaintBinding
import java.util.*


class WriteComplaintActivity : AppCompatActivity() {
    private lateinit var binding: ActivityComplaintBinding

    val permission_list = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_MEDIA_LOCATION
    )
    //민원항목
    val complaintCategoryData= arrayOf("불편사항 접수","불법행위 신고","시설물 파손 신고/수리요청","환경오염 행위 신고","기타")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //수정할때 필요한코드


        //바인딩을 위한 코드
        binding= ActivityComplaintBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //유저 데이터 가져오기

//        //산업안전 뉴스 제목,링크 불러오기
//        complaint_text_news.text="뉴스 제목입니다."
//        complaint_text_news.setOnClickListener {
//            var intent =
//                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.news1.kr/articles/?4386702"))
//            startActivity(intent)
//        }

        binding.complaintButtonImageDelete.isVisible=false
        //민원항목 선택 스피너

        val complaintAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,complaintCategoryData)
        complaintAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.complaintSpinnerCategory.adapter= complaintAdapter


        @Suppress("DEPRECATION")
        requestPermissions(permission_list, 0)

        //사진첨부버튼
        binding.complaintImageView.setOnClickListener {
            // 앨범에서 사진을 선택할 수 있는 액티비티를 실행한다.
            val albumIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            // 실행할 액티비티의 타입을 설정(이미지를 선택할 수 있는 것)
            albumIntent.type = "image/*"
            // 선택할 파일의 타입을 지정(안드로이드 OS가 사전작업을 할 수 있도록)
            val mimeType = arrayOf("image/*")
            albumIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
            @Suppress("DEPRECATION")
            startActivityForResult(albumIntent, 0)
            //complaint_button_image_delete.visibility=View.VISIBLE
        }

        //사진삭제버튼

        binding.complaintButtonImageDelete.setOnClickListener {
            binding.complaintImageView.setImageResource(0)
            binding.complaintButtonImageDelete.visibility=View.INVISIBLE
        }



        //등록하기버튼
        binding.complaintButtonEnroll.setOnClickListener {
            //textarea비어있는지 확인
            if(binding.complaintEditTextTextMultiLine.text.toString()=="") {
                //비어있으면 작성해주세요 다이얼로그
                val builder= AlertDialog.Builder(this)
                builder.setTitle("민원접수")
                builder.setMessage("민원내용을 작성해주세요")
                builder.setPositiveButton("네",null)
                builder.show()
            }
            else {
                //민원접수 다이얼로그
                val builder= AlertDialog.Builder(this)
                builder.setTitle("민원접수")
                builder.setMessage("민원내용을 접수하시겠습니까?")
                var listener = object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        when (p1) {
                            //"네" 눌렀을때
                            DialogInterface.BUTTON_POSITIVE -> {

                                //민원시간 표시
                                val homeNow = Calendar.getInstance()
                                val year = homeNow.get(Calendar.YEAR).toString()
                                val mMonth = (homeNow.get(Calendar.MONTH)+1).toString()
                                val mDate = homeNow.get(Calendar.DATE).toString()
                                val hour = homeNow.get(Calendar.HOUR).toString()
                                val minute = homeNow.get(Calendar.MINUTE).toString()

                                //민원항목
                                val complaintCategory =
                                    complaintCategoryData[binding.complaintSpinnerCategory.selectedItemPosition]


                                //다음페이지로 넘어가기
                                //MyConplaintActivity로 넘어가기
//                                val intent = Intent(this@ComplaintActivity, MyComplaintActivity::class.java)
//                                startActivity(intent)
                                setResult(RESULT_OK)
                                finish()
                            }

                        }
                    }
                }
                builder.setNegativeButton("아니오",null)
                builder.setPositiveButton("네",listener)
                builder.show()
            }
        }



//        //전화걸기 버튼
//        complaint_button_phonecall.setOnClickListener {
//            var intent = Intent(Intent.ACTION_DIAL)
//            intent.data = Uri.parse("tel:01012345678")
//            startActivity(intent)
//        }
//
//
        //나의 민원 보기 버튼
        binding.complaintButtonMycomplaint.setOnClickListener {
            //MyConplaintActivity로 넘어가기
            val intent = Intent(this@WriteComplaintActivity, ComplaintListActivity::class.java)
            startActivity(intent)
        }


    }


    fun min(a:Int,b:Int):Int{
        return if(a<b){
            a
        }else{
            b
        }
    }

}



