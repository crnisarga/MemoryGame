package com.nisargacr.memorygame.models

import com.google.android.material.snackbar.Snackbar
import utils.Default_Icons

class MemoryGame(private val boardSize: BoardSize) {

    val cards: List<MemoryCard>
    var numPairsFound = 0

    private var singleSelectedCard : Int? = null
    private var numOfMoves = 0

    init {
        val chosenImages = Default_Icons.shuffled().take(boardSize.getNumPaires())
        val randomizedImages = (chosenImages + chosenImages).shuffled()
        cards = randomizedImages.map {MemoryCard(it)}
    }

    fun flipCard(position: Int): Boolean {
        numOfMoves++
        val card = cards[position]
        var foundMatch = false

        if (singleSelectedCard == null) {
            restoreCard()
            singleSelectedCard = position
        }
        else {
            foundMatch = checkMatched(singleSelectedCard!!,position)
            singleSelectedCard = null
        }

        card.isFaceUp = !card.isFaceUp
        return foundMatch
    }

    private fun checkMatched(position1: Int, position2 : Int) : Boolean {
        if(cards[position1].identifier != cards[position2].identifier) {
            return false
        }
        cards[position1].isMatched = true
        cards[position2].isMatched = true
        numPairsFound++
        return true
    }

    private fun restoreCard() {
        for (card in cards) {
            if(!card.isMatched) {
                card.isFaceUp = false
            }
        }
    }

    fun haveWonGame(): Boolean {
        return numPairsFound == boardSize.getNumPaires()
    }

    fun isCardFaceUp(position: Int): Boolean {
        return cards[position].isFaceUp
    }

    fun getNumMoves(): Int {
        return numOfMoves/2
    }


}