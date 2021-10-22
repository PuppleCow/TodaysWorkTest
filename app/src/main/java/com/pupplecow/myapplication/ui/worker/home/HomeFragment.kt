package com.pupplecow.myapplication.ui.worker.home
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.pupplecow.myapplication.ui.login.CheckInActivity
import com.pupplecow.myapplication.R
import com.pupplecow.myapplication.ui.worker.home.complaint.WriteComplaintActivity
import kotlinx.android.synthetic.main.activity_home1.*
import java.util.*

class HomeFragment:Fragment() {



    val workspaceData =arrayOf("인천항만","인천항만","인천항만","인천항만","기타")
    val workData =arrayOf("하역","하역","하역","하역","기타")

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //화면 시작시
        home_layout_2.visibility=GONE


        //산업안전 뉴스 제목,링크 불러오기
        home_text_news.text="뉴스 제목입니다."


        //작업장선택 스피너
        val workspaceAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,workspaceData)
        workspaceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        home_spinner_workspace.adapter= workspaceAdapter

        //작업선택 스피너
        val workAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,workData)
        workAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        home_spinner_work.adapter= workAdapter


        //오늘날짜 표시
        val homeNow= Calendar.getInstance()
        val year=homeNow.get(Calendar.YEAR).toString()
        val month=(homeNow.get(Calendar.MONTH)+1).toString()
        val day=homeNow.get(Calendar.DATE).toString()
        //val dayOfWeek=homeNow.get(Calendar.DAY_OF_WEEK).toString()

        home_text_date_1.text=month+"월 "+day+"일"

//        //근무시작버튼 활성화,근무종료버튼 비활성화
//        home_button_finish.setEnabled(false)


        //근무시작버튼
        home_button_start.setOnClickListener {
            //근무시작 확인 다이얼로그
            val builder= AlertDialog.Builder(requireContext())
            builder.setTitle("근무시작")
            builder.setMessage("" +
                    "${workspaceData[home_spinner_workspace.selectedItemPosition]} 에서 " +
                    "${workData[home_spinner_work.selectedItemPosition]} 작업 시작하시겠습니까?")

            // 근무시작 다이얼로그
            var listener = object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when (p1) {
                        //"네" 눌렀을때
                        DialogInterface.BUTTON_POSITIVE -> {
                            //근무시작시간,작업장,작업 서버에 저장
                            val year = homeNow.get(Calendar.YEAR).toString()
                            val month = homeNow.get(Calendar.MONTH).toString()
                            val date = homeNow.get(Calendar.DATE).toString()
                            //근무시작시간 표시
                            val homeNow = Calendar.getInstance()
                            val hour = homeNow.get(Calendar.HOUR).toString()
                            val minute = homeNow.get(Calendar.MINUTE).toString()

                            home_button_finish.text="$hour : $minute"

//                            home_text_start.text = "시작\n ${hour} 시 ${minute} 분"
//
//                            //근무장소 표시
//                            home_text_workspace.text =
//                                workspaceData[home_spinner_workspace.selectedItemPosition]
//                            home_text_work.text = workData[home_spinner_work.selectedItemPosition]
//
//                            //근무종료버튼 활성화
//
//                            home_button_finish.setEnabled(true)
//                            home_button_start.setEnabled(false)

                            //레이아웃 변경
                            home_layout_1.visibility=GONE
                            home_layout_2.visibility= VISIBLE

                            //비대면체크인 페이지
                            val intent = Intent(requireContext(), CheckInActivity::class.java)
                            startActivity(intent)

                        }

                    }
                }
            }


            builder.setNegativeButton("아니오",listener)
            builder.setPositiveButton("네",listener)
            builder.show()


        }


        //근무종료버튼
        home_button_finish.setOnClickListener {
            //근무종료 확인 다이얼로그
            val builder= AlertDialog.Builder(requireContext())
            builder.setTitle("근무종료")
            builder.setMessage("" +
                    "${workspaceData[home_spinner_workspace.selectedItemPosition]} 에서 " +
                    "${workData[home_spinner_work.selectedItemPosition]} 작업 종료하시겠습니까?")


            // 근무시작 다이얼로그
            var listener = object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when (p1) {
                        //"네" 눌렀을때
                        DialogInterface.BUTTON_POSITIVE ->{
                            //근무시작버튼 활성화
                            home_button_start.setEnabled(true)
                            home_button_finish.setEnabled(false)
                            //근무종료시간 표시
                            val homeNow= Calendar.getInstance()
                            val hour=homeNow.get(Calendar.HOUR).toString()
                            val minute=homeNow.get(Calendar.MINUTE).toString()
                            //home_text_finish.text="종료\n ${hour} 시 ${minute} 분"

                            //근무종료시간 저장

//                            //스피너 비활성화
//                            home_spinner_workspace.setEnabled(true)
//                            home_spinner_work.setEnabled(true)

                            //레이아웃 변경
                            home_layout_1.visibility=VISIBLE
                            home_layout_2.visibility= GONE
                        }}
                }
            }
            builder.setNegativeButton("아니오",listener)
            builder.setPositiveButton("네",listener)
            builder.show()

        }

        //현장 민원접수 버튼 눌렀을때
        home_button_complaint.setOnClickListener {
            //민원접수 페이지로 가기
            val intent = Intent(requireContext(), WriteComplaintActivity::class.java)
            intent.putExtra("DocumentID","null")
            startActivity(intent)
        }

        home_text_news.setOnClickListener{

            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.news1.kr/articles/?4386702"))
            startActivity(intent)
        }



    }

    override fun onCreateView(inflater: LayoutInflater,container:ViewGroup?,savedInstanceState:Bundle?): View?{
        val view=inflater.inflate(R.layout.activity_home1,container,false)

        return view
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(resultCode != RESULT.OK) {
//            return;
//        }
//        if(requestCode == "요청했던 리퀘스트 코드") {
//            //새로 고침해서 서버에서 글 불러오기
//        }
//    }
}