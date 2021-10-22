package com.pupplecow.myapplication.data

import androidx.annotation.Keep

/*
1. Keep - 난독화 방지
2. 변수 타입 - String, Long , Double, Map, List Boolean
3. 초기값 필수
4. 변수 이름 시작 제외 get set is 제외 해야함
(5. nullable 권장 )
 */
@Keep
data class UserData (
    var uid:String?=null,
    var email:String?=null,
    var name:String?=null,
    var photoUrl:String?=null,
    var providerData:String?=null, //로그인 방식
    var fcmToken:String?=null,  //푸시 알림
    var phoneNumber:String?=null, //01012341234
    var birthDate:String?=null,  //19990727
    var userType:Int?=null,  //0: 관리자, 1:일용직 , 2:상용직
    var ssid:String?=null,  //990727-1234567


    var emergencyPhoneNumber:String?=null,  //0101012341234
    var emergencyRelationship:String?=null,  //어머니
    var bloodType:String?=null,   //Rh-A, Rh+b
    var extraData:ArrayList<String>?=null,  //["당뇨","고혈압","난시"]


)