package com.pupplecow.myapplication.ui.manager.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.pupplecow.myapplication.R

class workersStatusListAdapter(val context: Context, val workersList: ArrayList<workersStatus>,val itemClick:(workersStatus)->Unit,val checkClick:(String,Boolean)->Unit)  :
    RecyclerView.Adapter<workersStatusListAdapter.Holder>() {

    fun changeCheck(position: Int,checked:Boolean){
        workersList[position].checked=checked
        notifyItemChanged(position)
    }

    fun changeCheckAll(checked: Boolean){
        for(worker in workersList){
            worker.checked=checked
        }
        notifyItemRangeChanged(0,workersList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.workers_status_item, parent, false)
        return Holder(view,itemClick)
    }

    override fun getItemCount(): Int {
        return workersList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(workersList[position], context)
    }

    inner class Holder(itemView: View,itemClick: (workersStatus) -> Unit) : RecyclerView.ViewHolder(itemView) {

        val workersName = itemView?.findViewById<TextView>(R.id.workers_status_text_name)
        val workersNoneConfirm = itemView?.findViewById<CheckBox>(R.id.workers_status_checkbox)
        val workersConfirm = itemView?.findViewById<TextView>(R.id.workers_status_text_confirm  )
        val workersPhoneNum = itemView?.findViewById<TextView>(R.id.workers_status_text_phNum)
        val workersStartTime = itemView?.findViewById<TextView>(R.id.workers_status_text_startT)
        val workersFinishTime= itemView?.findViewById<TextView>(R.id.workers_status_text_finishT)
        val workersmanager= itemView?.findViewById<TextView>(R.id.workers_status_text_manager)


        fun bind (worker: workersStatus, context: Context) {
            if(worker.confirm=="미승인"){
                workersNoneConfirm.isInvisible=true
                workersConfirm.isVisible=true
            }
            else if(worker.confirm=="승인"){
                workersNoneConfirm.isVisible=true
                workersConfirm.isInvisible=true
                worker

            }


            workersNoneConfirm.isChecked =worker.checked



            workersName?.text=worker.workerName
            workersPhoneNum?.text=worker.phoneNumber
            workersStartTime?.text=worker.startTime
            workersFinishTime?.text=worker.finishTime
            workersmanager?.text=worker.manager

            itemView.setOnClickListener { itemClick(worker) }
            workersNoneConfirm.setOnClickListener{checkClick(worker.key!!,workersNoneConfirm.isChecked)}
        }
    }
}




//리스트뷰
//class workersStatusListAdapter(val context:Context,val workersList:ArrayList<workersStatus>):BaseAdapter() {
//    override fun getCount(): Int {
//        return workersList.size
//    }
//
//    override fun getItem(position: Int): Any {
//        return workersList[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return 0
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
///* LayoutInflater는 item을 Adapter에서 사용할 View로 부풀려주는(inflate) 역할을 한다. */
//        val view: View = LayoutInflater.from(context).inflate(R.layout.workers_status_item, null)
//
//        /* 위에서 생성된 view를 res-layout-main_lv_item.xml 파일의 각 View와 연결하는 과정이다. */
//        val workersName = view.findViewById<TextView>(R.id.workers_status_text_name)
//        val workersPhoneNum = view.findViewById<TextView>(R.id.workers_status_text_phNum)
//        val workersStartTime = view.findViewById<TextView>(R.id.workers_status_text_startT)
//        val workersFinishTime= view.findViewById<TextView>(R.id.workers_status_text_finishT)
//        val workersmanager= view.findViewById<TextView>(R.id.workers_status_text_manager)
//
//        /* ArrayList<Dog>의 변수 dog의 이미지와 데이터를 ImageView와 TextView에 담는다. */
//        val worker=workersList[position]
//        //val resourceId = context.resources.getIdentifier(dog.photo, "drawable", context.packageName)
//        //dogPhoto.setImageResource(resourceId)
//        workersName.text=worker.workerName
//        workersPhoneNum.text=worker.phoneNumber
//        workersStartTime.text=worker.startTime
//        workersFinishTime.text=worker.finishTime
//        workersmanager.text=worker.manager
//
//
//        return view
//    }
//}