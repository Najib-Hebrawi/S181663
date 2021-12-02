package com.mhebrawi.s181663




private val word = ArrayList<String>()
private var spinner = 0
private var ms = ""

fun spinWheelFun() {
    spinner = (0..4).random()
}

fun getSpinner(): Int {
    return spinner
}

fun getSpinnerResult(): String {
    if (spinner == 0)
        ms = "+1000"
    if (spinner == 1)
        ms = "Extra life"
    if (spinner == 2)
        ms = "Reset Score"
    if (spinner == 3)
        ms = "minus life"
    if (spinner == 4)
        ms="+500"
    return ms
}