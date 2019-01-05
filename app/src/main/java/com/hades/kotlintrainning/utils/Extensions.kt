package com.hades.kotlintrainning.utils

import android.widget.TextView

// # Kotlin Extensions

//- [View](#view)
//- [Context](#context)
//- [Fragment](#fragment)
//- [Activity](#activity)
//- [ViewGroup](#viewgroup)
//- [TextView](#textview)
//- [String](#string)
//- [Other](#other)

fun TextView.bold() {
    paint.isFakeBoldText = true
    paint.isAntiAlias = true
}
