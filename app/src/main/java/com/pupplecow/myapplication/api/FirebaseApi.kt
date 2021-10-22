package com.pupplecow.myapplication.api

import androidx.core.net.toUri
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.pupplecow.myapplication.data.Complaint
import com.pupplecow.myapplication.data.UserData

class FirebaseApi :Contract.firebaseDatabase {
    val firestore = Firebase.firestore
    val storage = Firebase.storage.reference
    val db = Firebase.database.reference  //루트가져옴

    //유저 데이터 가져오기
    override fun getUserData(uid: String, callback: (Boolean, String, UserData?) -> Unit) {
        //루트 안에 자식들에게 접근
        db.child("users")
            .child(uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    //데이터 있으면
                    if (snapshot.exists()) {
                        callback(true, "SUCCESS", snapshot.getValue(UserData::class.java))
                    } else {
                        //epdlxj djqtdmaus
                        callback(false, "NO DATA", null)
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    //접근권한없을 때 데이터 없을때 등 실패 했을 떄
                    //callback(false,UserData().apply { this.uid=error.message.toString() })
                    callback(false, error.message, null) //이클래스 안에 겟유저데이더-> uid,받아오고 콜백 반환
                }

            })
    }

    override fun writeComplaint(
        docId: String,
        data: Complaint,
        callback: (Boolean, String) -> Unit
    ) {
        val ref = firestore.collection("COMPLAINT")
        val id = if (docId == "null") ref.document().id else docId

        //파일 업로드
        val imageRef = storage.child("COMPLAINT").child(id + ".jpg")

        if (data.imageUri.isNullOrBlank() || data.imageUri == "null") {
            ref.document(id).set(data)
        } else {

            val uploadTask = imageRef.putFile(data.imageUri!!.toUri())


            val urlTask = uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }

                imageRef.downloadUrl

            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    data.imageUri = imageRef.downloadUrl.toString()
                } else {
                    // Handle failures
                    // ...
                }
            }


        }

    }

}

