package com.pupplecow.myapplication.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pupplecow.myapplication.R
import com.pupplecow.myapplication.data.Complaint
import kotlinx.android.synthetic.main.manager_complaint_list_item.view.*


//class ManagerComplaintListAdapter(val context: Context,val complaint:ArrayList<ManagerComplaint>, val itemClick: (ManagerComplaint) -> Unit):
class ComplaintListAdapter(val context: Context, val itemClick: (Complaint) -> Unit):
    RecyclerView.Adapter<ComplaintListAdapter.Holder>() {


    var complaintList:ArrayList<Complaint> = arrayListOf(    )

    fun setDatas(arrayList: ArrayList<Complaint>){
        complaintList.clear()
        complaintList.addAll(arrayList)
        notifyItemRangeInserted(0,complaintList.size)
    }
//
//    init{
////        db.collection("COMPLAINT")
////            .whereEqualTo("writerName", "최지혜")  //조건 추가 가능
////            .get()
////            .addOnSuccessListener { documents ->
////                complaintList.clear()
////                for (document in documents) {
////                    Log.e("COMPLAINT", "${document.id} => ${document.data}")
////                    val data=document.toObject(Complaint::class.java)
////                    data.key=document.id
////                    complaintList.add(data)
////                }
////                notifyDataSetChanged()
////            }
////            .addOnFailureListener { exception ->
////                Log.w("", "Error getting documents: ", exception)
////            }
////        firestore?.collection("COMPLAINT")?.addSnapshotListener{querySnapshot,firebaseFirestoreException ->
////        // ArrayList 비워줌
////            complaintList.clear()
////        for (snapshot in querySnapshot!!.documents) {
////            var item = snapshot.toObject(Complaint::class.java)
////            complaintList.add(item!!)
////        }
////        notifyDataSetChanged()
//
//
//    }
//
//

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.manager_complaint_list_item, parent, false)
        return Holder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return complaintList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
//        holder?.bind(complaintList[position], context)
        var viewHolder= holder.itemView

        viewHolder.manager_complaint_list_title.text="["+complaintList[position].category+"]"+complaintList[position]. title
        viewHolder.manager_complaint_list_number.text=complaintList[position].month+"/"+complaintList[position].date

        holder?.itemView.setOnClickListener {
            itemClick(complaintList[position])
        }
    }

    inner class Holder(itemView: View, itemClick: (Complaint) -> Unit) : RecyclerView.ViewHolder(itemView) {

        //val complaintPhoto = itemView?.findViewById<ImageView>(R.id.manager_complaint_list_imageview)
        val complaintNum = itemView?.findViewById<TextView>(R.id.manager_complaint_list_number)
        val complaintTitle = itemView?.findViewById<TextView>(R.id.manager_complaint_list_title)
        //val complaintCategory = itemView?.findViewById<TextView>(R.id.manager_complaint_list_category)

        fun bind(complaint: Complaint, context: Context) {

            itemView.setOnClickListener { itemClick(complaint) }
            /* (3) itemView가 클릭됐을 때 처리할 일을 itemClick으로 설정한다.
             (Dog) -> Unit 에 대한 함수는 나중에 MainActivity.kt 클래스에서 작성한다. */

        }
    }
}