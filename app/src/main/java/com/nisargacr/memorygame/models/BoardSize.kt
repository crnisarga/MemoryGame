package com.nisargacr.memorygame.models

enum class BoardSize(val numCards : Int) {
    Easy(8),
    Medium(18),
    Hard(24);

    fun getWidth() : Int {
        return when(this) {
            Easy -> 2
            Medium -> 3
            Hard -> 4
        }
    }

    fun getHeight() : Int {
        return numCards/getWidth()
    }

    fun getNumPaires() : Int {
        return numCards / 2
    }
}