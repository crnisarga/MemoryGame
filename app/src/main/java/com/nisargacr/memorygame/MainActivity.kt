package com.nisargacr.memorygame

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import models.BoardSize
import utils.Default_Icons

class MainActivity : AppCompatActivity() {

    private lateinit var memoryBoard : RecyclerView
    private lateinit var movesButton : TextView
    private lateinit var pairsButton : TextView

    private var boardSize: BoardSize = BoardSize.Medium


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memoryBoard = findViewById(R.id.memoryBoard)
        movesButton = findViewById(R.id.moves)
        pairsButton = findViewById(R.id.pairs)

        val chosenImages = Default_Icons.shuffled().take(boardSize.getNumPaires())
        val randomizedImages = (chosenImages + chosenImages).shuffled()

        memoryBoard.adapter = MemoryBoardAdapter(this,boardSize,randomizedImages);
        memoryBoard.setHasFixedSize(true);
        memoryBoard.layoutManager = GridLayoutManager(this,boardSize.getWidth());
    }
}