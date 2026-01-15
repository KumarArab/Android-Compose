package com.example.subjectapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val title:Int,
    val rank: Int,
    @DrawableRes val image:Int
)
