package com.pupplecow.myapplication.ui.manager.home.Complaint

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.pupplecow.myapplication.Adapter.ComplaintListAdapter
import com.pupplecow.myapplication.R
import com.pupplecow.myapplication.databinding.ActivityManagerComplaintListBinding


class ManagerComplaintListActivity : AppCompatActivity() {
    private lateinit var binding:ActivityManagerComplaintListBinding

    private var fbFirestore: FirebaseFirestore?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_complaint_list)
        binding=ActivityManagerComplaintListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fbFirestore= FirebaseFirestore.getInstance()

        //database= FirebaseDatabase.getInstance().getReference().child("board")

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

//        database.addValueEventListener(object : ValueEventListener {
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for(data in snapshot.children){
//                    val modelResult=data.getValue(ComplaintData::class.java)
//                    managerComplaintList.apply {
//                        add(
//                            ManagerComplaint(
//                            month = modelResult?.month.toString(),
//                            date = modelResult?.date.toString(),
//                            title = modelResult?.title.toString(),
//                            category = modelResult?.category.toString(),
//                            uid = modelResult?.uid.toString(),
//                            contents = modelResult?.contents.toString()
//                        )
//                        )
//                        //ManagerComplaintList.complaintList=complaintList
//                    }
//                }
//                mAdapter.notifyDataSetChanged()
//
//            }
//        })
//        FirebaseDatabase.getInstance()
//
//    }
}}