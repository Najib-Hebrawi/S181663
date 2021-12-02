package com.mhebrawi.s181663




private var lives = 0
private var scores = 0


fun setLife(life: Int) {
    lives = life
}

fun setScore(score: Int) {
    scores = score
}

fun extraLife(life: Int) {
    lives = life
    lives = life + 1
}

fun minusLife(life: Int) {
    lives = life
    lives = life - 1
}

fun extra1000(score: Int) {
    scores = score
    scores += 1000
}
fun extra500(score: Int) {
    scores = score
    scores += 500
}


fun resetScore(score: Int) {
    scores = score
    scores = score - score
}


fun updateLives(): Int {
    return lives
}

fun updateScore(): Int {
    return scores
}

fun getScore(): Int {
    return scores
}

fun getLives(): Int {
    return lives
}