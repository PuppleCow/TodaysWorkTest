package com.pupplecow.myapplication.ui.worker.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.pupplecow.myapplication.R
import com.pupplecow.myapplication.databinding.ActivitySafetyManualQuiz1Binding
import android.os.Build
import android.webkit.WebResourceRequest

class SafetyManualQuizActivity1 : AppCompatActivity() {
    private lateinit var binding: ActivitySafetyManualQuiz1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySafetyManualQuiz1Binding.inflate(layoutInflater)
        setContentView(binding.root)

//        // webView 실행
//        binding.button2.setOnClickListener {
//            val intent=Intent(this,SafetyManualQuizWebViewActivity1::class.java)
//            startActivity(intent)
//        }

        // 체크 박스들
        binding.quiz1CheckBox1.setOnClickListener{ onCheckChanged(binding.quiz1CheckBox1)}
        binding.quiz1CheckBox2.setOnClickListener{ onCheckChanged(binding.quiz1CheckBox2)}
        binding.quiz1CheckBox3.setOnClickListener{ onCheckChanged(binding.quiz1CheckBox3)}
        binding.quiz1CheckBox4.setOnClickListener{ onCheckChanged(binding.quiz1CheckBox4)}
        binding.quiz1CheckBox5.setOnClickListener{ onCheckChanged(binding.quiz1CheckBox5)}

        // ====================== 약관 내용 보이기 ========================
        // 항목 터치 시 -> 다이얼 로그 띄우고 그 안에 웹뷰

        // 개인정보 처리 동의 버튼
        binding.quiz1TextView2.setOnClickListener {
//            binding.quiz1Scrollview.visibility= View.VISIBLE
//
//            binding.quiz1TextView6.text="약관1 \n" +
//                    "개인정보\n처리\n동의\n"


            //============================================================//
            // 다이얼로그 안에 웹뷰로 항목 보여주고
            // 확인 버튼 누르면 체크박스 체크됨

          val webView=WebView(this).apply {
              loadUrl("file:///android_asset/index1.html")
              webViewClient=object :WebViewClient(){
                  override fun shouldOverrideUrlLoading(
                      view: WebView?,
                      request: WebResourceRequest?
                  ): Boolean {
                      view!!.loadUrl("file:///android_asset/index1.html")
                      return true
                  }
              }
          }

            // 웹뷰 깜빡임 때문에 추가한 코드
            if(Build.VERSION_CODES.HONEYCOMB<=Build.VERSION.SDK_INT){
                webView.setLayerType(WebView.LAYER_TYPE_HARDWARE,null)
            }


            AlertDialog.Builder(this).setView(webView).setNegativeButton("확인"){
                dialog, _ ->dialog.dismiss()
                binding.quiz1CheckBox2.isChecked=true
            }.show()
//            val listener=object :DialogInterface.OnClickListener{
//                override fun onClick(dialog: DialogInterface?, which: Int) {
//                    when(which){
//                        // 확인 버튼 누르면 -> 웹뷰 끄고 체크박스 체크 표시됨
//                        DialogInterface.BUTTON_NEGATIVE->{
//                            webView.destroy()
//                            binding.quiz1CheckBox2.isChecked=true;
//                        }
//                    }
//                }
//            }
//            builder.setNegativeButton("확인",listener)
//            builder.show()
        }


        // 개인정보 지속 처리 동의 버튼
        binding.quiz1TextView3.setOnClickListener {
//            binding.quiz1Scrollview.visibility= View.VISIBLE
//
//            binding.quiz1TextView6.text="약관2 \n" +
//                    "개인정보\n지속\n처리\n동의\n"

            val webView=WebView(this).apply {
                loadUrl("file:///android_asset/index2.html")
                webViewClient=object :WebViewClient(){
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        view!!.loadUrl("file:///android_asset/index2.html")
                        return true
                    }
                }
            }

            // 웹뷰 깜빡임 때문에 추가한 코드
            if(Build.VERSION_CODES.HONEYCOMB<=Build.VERSION.SDK_INT){
                webView.setLayerType(WebView.LAYER_TYPE_HARDWARE,null)
            }


            AlertDialog.Builder(this).setView(webView).setNegativeButton("확인"){
                    dialog, _ ->dialog.dismiss()
                binding.quiz1CheckBox3.isChecked=true
            }.show()
        }


        // 주민등록번호 지속 처리 동의 버튼
        binding.quiz1TextView4.setOnClickListener {
//            binding.quiz1Scrollview.visibility= View.VISIBLE
//
//            binding.quiz1TextView6.text="약관3 \n" +
//                    "주민등록번호\n지속\n처리\n동의\n"

            val webView=WebView(this).apply {
                loadUrl("file:///android_asset/index3.html")
                webViewClient=object :WebViewClient(){
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        view!!.loadUrl("file:///android_asset/index3.html")
                        return true
                    }
                }
            }

            // 웹뷰 깜빡임 때문에 추가한 코드
            if(Build.VERSION_CODES.HONEYCOMB<=Build.VERSION.SDK_INT){
                webView.setLayerType(WebView.LAYER_TYPE_HARDWARE,null)
            }


            AlertDialog.Builder(this).setView(webView).setNegativeButton("확인"){
                    dialog, _ ->dialog.dismiss()
                binding.quiz1CheckBox4.isChecked=true
            }.show()
        }


        // 출근 안내 등 정보이용 동의 버튼
        binding.quiz1TextView5.setOnClickListener {
//            binding.quiz1Scrollview.visibility= View.VISIBLE
//
//            binding.quiz1TextView6.text="약관4 \n" +
//                    "출근 안내 등\n정보이용\n동의\n"

            val webView=WebView(this).apply {
                loadUrl("file:///android_asset/index4.html")
                webViewClient=object :WebViewClient(){
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        view!!.loadUrl("file:///android_asset/index4.html")
                        return true
                    }
                }
            }

            // 웹뷰 깜빡임 때문에 추가한 코드
            if(Build.VERSION_CODES.HONEYCOMB<=Build.VERSION.SDK_INT){
                webView.setLayerType(WebView.LAYER_TYPE_HARDWARE,null)
            }


            AlertDialog.Builder(this).setView(webView).setNegativeButton("확인"){
                    dialog, _ ->dialog.dismiss()
                binding.quiz1CheckBox5.isChecked=true
            }.show()
        }


        // 확인버튼
        binding.quiz1Button1.setOnClickListener {

            // 필수항목 체크됐을 때만 넘어가기
            if (binding.quiz1CheckBox2.isChecked == true) {
                val quiz2_intent = Intent(this, SafetyManualQuizActivity2::class.java)
                startActivity(quiz2_intent)
                finish()
            }

            // 필수 항목 체크돼있지 않으면
            else{
                val t1=Toast.makeText(this,"필수항목이 비어있습니다",Toast.LENGTH_SHORT)
                t1.show()
            }
        }
    }

    fun onCheckChanged(compoundButton: CompoundButton) {
        when (compoundButton.id) {
            R.id.quiz1_checkBox1 -> {
                if (binding.quiz1CheckBox1.isChecked) {
                    binding.quiz1CheckBox2.isChecked = true
                    binding.quiz1CheckBox3.isChecked = true
                    binding.quiz1CheckBox4.isChecked = true
                    binding.quiz1CheckBox5.isChecked = true
                } else {
                    binding.quiz1CheckBox2.isChecked = false
                    binding.quiz1CheckBox2.isChecked = false
                    binding.quiz1CheckBox2.isChecked = false
                    binding.quiz1CheckBox2.isChecked = false
                }
            }
            else -> {
                binding.quiz1CheckBox1.isChecked = (
                        binding.quiz1CheckBox2.isChecked
                                && binding.quiz1CheckBox3.isChecked
                                && binding.quiz1CheckBox4.isChecked
                                && binding.quiz1CheckBox5.isChecked)
            }
        }
    }
}
