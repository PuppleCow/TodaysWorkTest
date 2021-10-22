package com.pupplecow.myapplication.ui.manager.home

import androidx.annotation.Keep


@Keep
data class workersStatus(
    //사번
    val key:String?=null,
    val workerName:String?=null,
    val confirm:String?=null,
    val startTime:String?=null,
    val finishTime:String?=null,
    val phoneNumber:String?=null,
    val manager:String?=null,
    var checked:Boolean=false
    )

