package com.pupplecow.myapplication.data

import androidx.annotation.Keep

@Keep
data class Announcement (
    var uid:String="",
    var month:String="",
    var date:String="",
    var title:String="",
    var category:String

    )