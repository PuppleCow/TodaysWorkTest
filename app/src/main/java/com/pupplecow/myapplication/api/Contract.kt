package com.pupplecow.myapplication.api

import com.pupplecow.myapplication.data.Complaint
import com.pupplecow.myapplication.data.UserData

//파베데에서 쓸수있는 함수를 제약해놓음
interface Contract {
    interface firebaseDatabase{
        fun getUserData(uid: String, callback: (Boolean, String,UserData?) -> Unit)
        fun writeComplaint(docId:String,data: Complaint, callback: (Boolean, String) -> Unit)
        //fun updatePhoneNumber(uid:String,phonenumber:Int,callback: (Boolean, String) -> Unit)
    }
}