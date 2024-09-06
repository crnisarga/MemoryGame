package com.nisargacr.memorygame

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nisargacr.memorygame.models.BoardSize
import com.nisargacr.memorygame.models.MemoryCard
import com.nisargacr.memorygame.models.MemoryGame
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

        val memoryGame = MemoryGame(boardSize)

        memoryBoard.adapter = MemoryBoardAdapter(this,boardSize,memoryGame.cards,object : MemoryBoardAdapter.CardClickListener {
            override fun onCardClicked(position: Int) {
                Log.d("click","CardClicked")
            }

        })
        memoryBoard.setHasFixedSize(true);
        memoryBoard.layoutManager = GridLayoutManager(this,boardSize.getWidth());
    }
}