package com.pupplecow.myapplication.ui.worker.home.complaint
import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.pupplecow.myapplication.R
import com.pupplecow.myapplication.api.FirebaseApi
import com.pupplecow.myapplication.data.Complaint
import com.pupplecow.myapplication.data.UserData
import com.pupplecow.myapplication.databinding.ActivityComplaintBinding
import kotlinx.android.synthetic.main.activity_complaint.*
import kotlinx.android.synthetic.main.activity_home1.*
import kotlinx.android.synthetic.main.fragment_manager_create_announecement.*
import java.util.*


class WriteComplaintActivity : AppCompatActivity() {
    private var fbFirestore: FirebaseFirestore?=null
    private lateinit var binding: ActivityComplaintBinding
    private var photoUri: Uri?=null


    //private var mUid:String=""

    var userData: UserData?=null
    var auth = Firebase.auth


    val permission_list = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_MEDIA_LOCATION
    )
    //민원항목
    val complaintCategoryData= arrayOf("불편사항 접수","불법행위 신고","시설물 파손 신고/수리요청","환경오염 행위 신고","기타")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaint)

        fbFirestore= FirebaseFirestore.getInstance()

        //수정할때 필요한코드
        val intent = intent
        var docID:String?="null"
        docID=intent.extras!!.getString("DocumentID")
        if(docID!="null"){
            val docRef = fbFirestore!!.collection("COMPLAINT").document(docID.toString())
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        //var complaint = document.data
                        docRef.get().addOnSuccessListener { documentSnapshot ->
                            val complaint = documentSnapshot.toObject<Complaint>()
                            //binding.MyComplaintTextTitle.text= complaint?.title
                                binding.complaintEditTextTextMultiLine.setText(complaint?.body)

                            binding.complaintSpinnerCategory.prompt=complaint?.category

                        }

                    } else {
                        Log.d("문서 데이터 없음", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("문서 데이터 실패", "get failed with ", exception)
                }
            //수정하는 페이지


        }

        //바인딩을 위한 코드
        binding= ActivityComplaintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //파이어스토어 파이어베이스
        auth= FirebaseAuth.getInstance()



        //유저 데이터 가져오기
        auth.uid?.let {
            FirebaseApi().getUserData(it){isSuccess,message,data ->
                Log.e("UserData","$isSuccess $message $data")
                if(isSuccess){
                    userData=data
                }
            }
        }
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
            complaint_imageView.setImageResource(0)
            complaint_button_image_delete.visibility=View.INVISIBLE
        }



        //등록하기버튼
        binding.complaintButtonEnroll.setOnClickListener {
            //textarea비어있는지 확인
            if(complaint_editTextTextMultiLine.text.toString()=="") {
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
                                    complaintCategoryData[complaint_spinner_category.selectedItemPosition]


//                                var dataInput= Complaint(
//                                    mUid,
//                                    mMont
//                                    month,date,complaintCategory,
//                                    //complaint_editText_title.text.toString(),
//                                    complaint_editTextTextMultiLine.text.toString()
//                                )

                                Log.e("COMPLAINT","현장 민원 접수 $title 민원글 올라가는지 확인하는 로그")
                                var dataInput=Complaint().apply{
                                    this.uid=auth.uid!!
                                    this.category=complaintCategory
                                    this.body=complaint_editTextTextMultiLine.text.toString()
                                    this.month=mMonth
                                    this.date=mDate
                                    this.imageUri=photoUri.toString()
                                    this.title=this.body.substring(0,min(10,body.length))
                                    this.writerName=userData?.name?:""

                                }
                                FirebaseApi().writeComplaint(docID?:"null",dataInput){_,_->}
                                //fbFirestore?.collection("complaint")?.document(fbAuth?.uid.toString())?.set(dataInput)
                                //fbFirestore?.collection("complaint")?.add(dataInput)

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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        @Suppress("DEPRECATION")
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == AppCompatActivity.RESULT_OK){
            // 선택한 이미지의 경로 데이터를 관리하는 Uri 객체를 추출한다.
            photoUri = data?.data

            if(photoUri != null){
                Glide.with(this).load(photoUri).into(complaint_imageView)

            }
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



