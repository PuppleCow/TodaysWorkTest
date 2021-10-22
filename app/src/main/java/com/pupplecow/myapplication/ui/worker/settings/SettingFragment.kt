package com.pupplecow.myapplication.ui.worker.settings

import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.pupplecow.myapplication.data.UserData
import com.pupplecow.myapplication.databinding.FragmentSettingBinding

class SettingFragment:Fragment() {


    private var fbFirestore: FirebaseFirestore?=null
    var auth = Firebase.auth

    private var _binding:FragmentSettingBinding?=null
    private val binding get() = _binding!!
    companion object {
        fun newInstance(): SettingFragment {
            return SettingFragment()
        }

//        private lateinit var settingMyInformationFragment: SettingMyInformationFragment
//        private lateinit var settingOpenSourceLicenseFragment: SettingOpenSourceLicenseFragment
//        private lateinit var settingTermsAndPolicyFragment: SettingTermsAndPolicyFragment
//        private lateinit var settingCheckSafetyManualFragment: SettingCheckSafetyManualFragment
    }


    // 사진을 가져오기 위한 권한을 확인하는 코드
    val permission_list = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_MEDIA_LOCATION
    )


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
       _binding= FragmentSettingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //이름,사진 가져오기
        // 파이어스토어에서 데이터 가져오기
        val docRef = fbFirestore?.collection("USER")?.document(auth.uid.toString())
        docRef?.get()
            ?.addOnSuccessListener { document ->
                if (document != null) {
                    //val userData=document.data
                    Log.e("개인정보 가져오기 성공", "DocumentSnapshot data: ${document.data}")

                    // 개인정보 가져오기 성공했을 때
                    docRef?.get().addOnSuccessListener { documentSnapshot ->
                        val information = documentSnapshot.toObject<UserData>()

                        // 이름 가져오기
                        binding.fragmentSetting1TextView2.text=information?.name+"님"
                    }

                } else {
                    Log.d("개인정보 등록 안 되어 있음", "No such document")
                }
            }
            ?.addOnFailureListener { exception ->
                Log.e("개인정보 가져오기 실패", "get failed with ", exception)
            }



        @Suppress("DEPRECATION")
        requestPermissions(permission_list, 0)


        // 사진 저장
        // 처음엔 기본 이미지, 등록 후엔 변경된 이미지 보여주기
        binding.fragmentSetting1MyImage.setOnClickListener {
            // 앨범에서 사진을 선택할 수 있는 액티비티를 실행한다.
            val albumInternet=Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            // 실행할 액티비티의 타입을 설정(이미지를 선택할 수 있는 것)
            albumInternet.type="image/*"
            // 선택할 파일의 타입을 지정(안드로이드 OS가 사전작업을 할 수 있도록)
            val mimeType= arrayOf("image/*")
            albumInternet.putExtra(Intent.EXTRA_MIME_TYPES,mimeType)
            @Suppress("DEPRECATION")
            startActivityForResult(albumInternet,0)
        }


        // 내 정보 설정
        binding.fragmentSetting1TextView4.setOnClickListener {

             //SettingMyInformationSettingActivity로 넘어가기
            val myInformationSetting_intent= Intent(requireContext(),SettingMyInformationSettingActivity::class.java)
            startActivity(myInformationSetting_intent)

//            settingMyInformationFragment=SettingMyInformationFragment.newInstance()
//            val tran=activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_nav_frame, settingMyInformationFragment)?.commit()
        }

        // 오픈소스 라이센스
        binding.fragmentSetting1TextView5.setOnClickListener {

            val openSourceLicense_intent= Intent(requireContext(),SettingOpenSourceLicense::class.java)
            startActivity(openSourceLicense_intent)

//            settingOpenSourceLicenseFragment=SettingOpenSourceLicenseFragment.newInstance()
//            val tran=activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_nav_frame, settingOpenSourceLicenseFragment)?.commit()
        }

        // 약관 및 정책
        binding.fragmentSetting1TextView6.setOnClickListener {

            val termsAndPolicy_intent= Intent(requireContext(),SettingTermsAndPolicy::class.java)
            startActivity(termsAndPolicy_intent)

//            settingTermsAndPolicyFragment=SettingTermsAndPolicyFragment.newInstance()
//            val tran=activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_nav_frame, settingTermsAndPolicyFragment)?.commit()
        }

        // 안전 메뉴얼 확인
        binding.fragmentSetting1TextView7.setOnClickListener {

            val checkSafetyManual_intent= Intent(activity,SettingCheckSafetyManual::class.java)
            startActivity(checkSafetyManual_intent)

//            settingCheckSafetyManualFragment=SettingCheckSafetyManualFragment.newInstance()
//            val tran=activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_nav_frame, settingCheckSafetyManualFragment)?.commit()
        }

        // 위급상황시 의료정보
        binding.fragmentSetting1TextView8.setOnClickListener {
            val emergencyInformaion_intent= Intent(requireContext(),SettingEmergencyInformationActivity::class.java)
            startActivity(emergencyInformaion_intent)
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
                    val source = ImageDecoder.createSource(this.requireActivity().contentResolver, uri)
                    val bitmap = ImageDecoder.decodeBitmap(source)
                    binding.fragmentSetting1MyImage.setImageBitmap(bitmap)
                } else {
                    // 안드로이드 9버전 까지
                    val cursor = this.requireActivity().contentResolver.query(uri, null, null, null, null)
                    if(cursor != null){
                        cursor.moveToNext()
                        // 이미지 경로를 가져온다.
                        @Suppress("DEPRECATION")
                        val index = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                        val source = cursor.getString(index)
                        // 이미지를 생성한다.
                        val bitmap = BitmapFactory.decodeFile(source)
                        binding.fragmentSetting1MyImage.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }
}