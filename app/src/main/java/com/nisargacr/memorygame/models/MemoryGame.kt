package com.nisargacr.memorygame.models

import utils.Default_Icons

class MemoryGame(private val boardSize: BoardSize) {
    val cards: List<MemoryCard>
    val numPairsFound = 0

    init {
        val chosenImages = Default_Icons.shuffled().take(boardSize.getNumPaires())
        val randomizedImages = (chosenImages + chosenImages).shuffled()
        cards = randomizedImages.map {MemoryCard(it)}
    }
}