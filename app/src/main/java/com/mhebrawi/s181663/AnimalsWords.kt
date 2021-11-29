package com.mhebrawi.s181663

import android.content.ContentValues
import android.util.Log


var guesLtterr: String = ""
var messagee: String = ""
var counterr: Int = 0
var hiddenWordArrayss = Array<String>(0) { "it = $it" }
var hiddenwordd: String = ""


val animalsNames= ArrayList<String>()
var randomAnimals:String =""



fun addAnimalsName(){
    animalsNames.add("Dog")
    animalsNames.add("cat")
    animalsNames.add("Lion")
    animalsNames.add("Tiger")
    animalsNames.add("Horse")
    animalsNames.add("Gazelle")
}


fun getRandomAnimalsName():String{
    addAnimalsName()
    val randomAnimalsWord =(0..5).random()
    randomAnimals = animalsNames[randomAnimalsWord]
    return randomAnimals
}


fun hiddenAnimalsNameWord(): String {

    var wordShouldBeHiddenn = randomAnimals
    hiddenWordArrayss = Array(wordShouldBeHiddenn.length) { "it = $it" }

    for (index in wordShouldBeHiddenn.indices) {
        hiddenWordArrayss[index] = "-"
    }

    val listt: String = hiddenWordArrayss.toList().toString()
        .replace("[", "")
        .replace("]", "")
        .replace(",", "")
    hiddenwordd = listt
    return listt


}

fun findLetterAsGussett(letter: String): Boolean {

    guesLtterr = letter
    var checkLetterr = false
    for (index in randomAnimals.indices) {
        if (randomAnimals.get(index) == guesLtterr[0] && hiddenWordArrayss[index] == "-") {
            checkLetterr = true
            messagee = "Du har gættet rigtig"
            counterr += 1
            Log.d(ContentValues.TAG, "findletter : $counterr")
            break
        } else if (randomAnimals.get(index) == guesLtterr[0] && hiddenWordArrayss[index] != "-") {

            checkLetterr = false
            messagee = "orden den blv valgt før"
            break
        } else
            messagee = "denn ord findes ikke, du har mistet et liv"
    }
    return checkLetterr
}





@JvmName("getMessage1")
fun getMessagee(): String {
    return messagee
}

@JvmName("getCounter1")
fun getCounterr(): Int {
    return counterr
}

fun resetCounterr() {
    counterr = 0
}

fun updateTheHiddenWordd(): String {
    for (index in randomAnimals.indices) {
        if (randomAnimals[index] == guesLtterr[0] && hiddenWordArrayss[index] == "-") {
            hiddenWordArrayss[index] = guesLtterr[0].toString()
        }
    }
    return hiddenWordArrayss.toList().toString()
        .replace("[", "")
        .replace("]", "")
        .replace(",", "")

}

fun isWonn(): Boolean {
    return hiddenWordArrayss.contains("-")
}
