
package com.pupplecow.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pupplecow.myapplication.R
import com.pupplecow.myapplication.ui.manager.announcement.AnnouncementData

import kotlin.collections.ArrayList

class AnnouncementListAdapterActivity(val context:Context, val itemClick: (AnnouncementData) -> Unit):
    RecyclerView.Adapter<AnnouncementListAdapterActivity.Holder>() {
    var announcement: ArrayList<AnnouncementData> = arrayListOf()




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val view = LayoutInflater.from(context).inflate(R.layout.activity_frameofannouncementlist, parent, false)
        return Holder(view,itemClick)
    }

    override fun getItemCount(): Int {
        return announcement.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //holder?.bind(announcementList[position], context)
        var viewHolder = holder.itemView


        holder?.itemView.setOnClickListener{
            itemClick(announcement[position])
        }
    }

    inner class Holder(itemView: View,itemClick: (AnnouncementData) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        val Number = itemView?.findViewById<TextView>(R.id.frameofannouncementlist_number)
        val Category = itemView?.findViewById<TextView>(R.id.frameofannouncementlist_category)
        val Title = itemView?.findViewById<TextView>(R.id.frameofannouncementlist_title)
        val Date = itemView?.findViewById<TextView>(R.id.frameofannouncementlist_date)

        //val EssentialRead = itemView?.findViewById<TextView>(R.id.frame_textView5)

        fun bind(Announce: AnnouncementData, context: Context) {
          /*  Number?.text = Announce.number
            Title?.text = Announce.date
            Category?.text = Announce.category
            //EssentialRead?.text= Announce.EssentialRead*/

            //itemview??? ???????????? itemClick(??????)
            itemView.setOnClickListener { itemClick(AnnouncementData()) }

        }
    }


}
