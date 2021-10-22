package com.pupplecow.myapplication.ui.worker.home.complaint

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.pupplecow.myapplication.R
import com.pupplecow.myapplication.databinding.ActivityComplaintListBinding
import com.pupplecow.myapplication.Adapter.ComplaintListAdapter
import com.pupplecow.myapplication.data.Complaint
import com.pupplecow.myapplication.view.LinearLayoutManagerWrapper


class ComplaintListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityComplaintListBinding

    private val complaintList: ArrayList<Complaint> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComplaintListBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val mAdapter = ManagerComplaintListAdapter(this, complaintList){
        val mAdapter = ComplaintListAdapter(this) { complaint ->


            //해당 민원 내용프래그먼트로 넘어가기
            val intent = Intent(this, ComplaintActivity::class.java)
            startActivity(intent)
        }
        binding.complaintListRecyclerview.adapter = mAdapter


        val lm = LinearLayoutManagerWrapper(this, LinearLayoutManager.VERTICAL, false)
        binding.complaintListRecyclerview.layoutManager = lm
        binding.complaintListRecyclerview.setHasFixedSize(true)

        binding.searchEt.addTextChangedListener {
            //글자 바뀔떄마다
            binding.searchClearIv.isVisible = it.toString().isNotEmpty()

            if (it.toString().isEmpty()) {
                //loadData()
            }
            if (it.toString().length >= 2) {
                binding.searchBtn.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(this, R.color.main_color))
                //실시간검색
                //search()
            } else {
                binding.searchBtn.backgroundTintList =
                    ColorStateList.valueOf(Color.parseColor("#bbbbbb"))
            }
        }


    }

    override fun onResume() {
        super.onResume()
        //loadData()
    }

//    private fun loadData() {
//        db.collection("COMPLAINT")
//            //.whereEqualTo("writerName", "최지혜")  //조건 추가 가능
//            .get()
//            .addOnSuccessListener { documents ->
//                complaintList.clear()
//                for (document in documents) {
//                    Log.e("COMPLAINT", "${document.id} => ${document.data}")
//                    val data=document.toObject(Complaint::class.java)
//                    data.key=document.id
//                    complaintList.add(data)
//                }
//                (binding.complaintListRecyclerview.adapter as ComplaintListAdapter).setDatas(complaintList)
//            }
//            .addOnFailureListener { exception ->
//                Log.w("", "Error getting documents: ", exception)
//            }
//    }

    fun onClick(view: View) {
        when (view) {
            binding.searchBtn -> {
                search()
            }
            binding.searchClearIv -> {
                binding.searchEt.setText("")
                //loadData()
            }
        }
    }

    private fun search() {
        if (binding.searchEt.text.toString().length < 2) {
            Toast.makeText(this, "검색어는 최소 2자 이상입니다", Toast.LENGTH_SHORT).show()
        }
//        db.collection("COMPLAINT")
//            //.whereEqualTo("writerName", "최지혜")  //조건 추가 가능
//            .get()
//            .addOnSuccessListener { documents ->
//                complaintList.clear()
//                for (document in documents) {
//                    Log.e("COMPLAINT", "${document.id} => ${document.data}")
//                    val data=document.toObject(Complaint::class.java)
//                    data.key=document.id
//
//                    //검색어 포함하고 있으면
//                    if(data.title.trim().contains(binding.searchEt.text.toString().trim())
//                        ||data.body.trim().contains(binding.searchEt.toString().trim())
//
//                    ){
//                        complaintList.add(data)
//
//                    }
//
//
//
//                }
//                (binding.complaintListRecyclerview.adapter as ComplaintListAdapter).setDatas(complaintList)
//            }
//            .addOnFailureListener { exception ->
//                Log.w("", "Error getting documents: ", exception)
//            }
//    }

    }
}
