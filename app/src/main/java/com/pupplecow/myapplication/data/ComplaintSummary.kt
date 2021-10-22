package com.pupplecow.myapplication.data

import androidx.annotation.Keep

//숫자,제목,항목,사진
/*
1. Keep - 난독화 방지
2. 변수 타입 - String, Long , Double, Map, List Boolean
3. 초기값 필수
4. 변수 이름 시작 제외 get set is 제외 해야함
(5. nullable 권장 )
 */

@Keep
data class ComplaintSummary (
    var uid:String="",
    var month: String?="",
    var date:String?="",
    var title:String="",
    var category: String="",
    var writerName:String="",
    var isReply:Boolean=false,


    //reply여부
    var imageUrl:String?=null,
    var body:String="",
    var replyWriter:String?=null,
    var replyBody:String?=null,
    var replyDate:String?=null,



    //val photo: String
    )
