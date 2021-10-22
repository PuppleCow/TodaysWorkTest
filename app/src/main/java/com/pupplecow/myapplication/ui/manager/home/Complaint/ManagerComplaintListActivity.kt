package com.pupplecow.myapplication.ui.manager.home.Complaint

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pupplecow.myapplication.Adapter.ComplaintListAdapter
import com.pupplecow.myapplication.databinding.ActivityManagerComplaintListBinding


class ManagerComplaintListActivity : AppCompatActivity() {
    private lateinit var binding:ActivityManagerComplaintListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityManagerComplaintListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mAdapter = ComplaintListAdapter(this){
                complaint->
            //해당 민원 내용프래그먼트로 넘어가기
            //넘어갈때 해당 내용 서버에서 불러오기
            //MyConplaintActivity로 넘어가기
            val intent = Intent(this, ManagerComplaintActivity::class.java)
            //intent.putExtra("uid",auth.currentUser?.uid)
            startActivity(intent)
        }


        binding.managerComplaintRecyclerview.adapter = mAdapter


        val lm = LinearLayoutManager(this)
        binding.managerComplaintRecyclerview.layoutManager = lm
        binding.managerComplaintRecyclerview.setHasFixedSize(true)

}}