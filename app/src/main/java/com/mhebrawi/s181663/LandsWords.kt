package com.mhebrawi.s181663

import android.content.ContentValues
import android.util.Log


val landsName = ArrayList<String>()
var randomLands: String = ""
var guesLtter: String = ""
var message: String = ""
var counter: Int = 0
var hiddenWordArrays = Array<String>(0) { "it = $it" }
var hiddenword: String = ""





fun addLandsName() {
    landsName.add("Finland")
    landsName.add("Ireland")
    landsName.add("Thailand")
    landsName.add("Poland")
    landsName.add("Denmark")
    landsName.add("England")

}

fun getRandomLandName(): String {
    addLandsName()
    val randomLandsWord = (0..5).random()
    randomLands = landsName[randomLandsWord]
    return randomLands

}


fun hiddenLandsNameWord(): String {

    var wordShouldBeHidden = randomLands
    hiddenWordArrays = Array(wordShouldBeHidden.length) { "it = $it" }

    for (index in wordShouldBeHidden.indices) {
        hiddenWordArrays[index] = "-"
    }

    val list: String = hiddenWordArrays.toList().toString()
        .replace("[", "")
        .replace("]", "")
        .replace(",", "")
    hiddenword = list
    return list


}

fun findLetterAsGusset(letter: String): Boolean {

    guesLtter = letter
    var checkLetter = false
    for (index in randomLands.indices) {
        if (randomLands.get(index) == guesLtter[0] && hiddenWordArrays[index] == "-") {
            checkLetter = true
            message = "Du har gættet rigtig"
            counter += 1
            Log.d(ContentValues.TAG, "findletter : $counter")
            break
        } else if (randomLands.get(index) == guesLtter[0] && hiddenWordArrays[index] != "-") {

            checkLetter = false
            message = "orden den blv valgt før"
            break
        } else
            message = "denn ord findes ikke, du har mistet et liv"
    }
    return checkLetter
}


@JvmName("getMessage1")
fun getMessage(): String {
    return message
}

@JvmName("getCounter1")
fun getCounter(): Int {
    return counter
}

fun resetCounter() {
    counter = 0
}

fun updateTheHiddenWord(): String {
    for (index in randomLands.indices) {
        if (randomLands[index] == guesLtter[0] && hiddenWordArrays[index] == "-") {
            hiddenWordArrays[index] = guesLtter[0].toString()
        }
    }
    return hiddenWordArrays.toList().toString()
        .replace("[", "")
        .replace("]", "")
        .replace(",", "")

}

fun isWon(): Boolean {
    return hiddenWordArrays.contains("-")
}

