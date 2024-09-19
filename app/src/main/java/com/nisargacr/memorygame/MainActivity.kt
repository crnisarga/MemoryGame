package com.nisargacr.memorygame

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator
import com.google.android.material.snackbar.Snackbar
import com.nisargacr.memorygame.models.BoardSize
import com.nisargacr.memorygame.models.MemoryCard
import com.nisargacr.memorygame.models.MemoryGame
import utils.Default_Icons

class MainActivity : AppCompatActivity() {

    private lateinit var memoryGame: MemoryGame
    private lateinit var memoryBoard : RecyclerView
    private lateinit var movesButton : TextView
    private lateinit var pairsButton : TextView
    private lateinit var clRoot : ConstraintLayout
    private lateinit var adapter : MemoryBoardAdapter

    private var boardSize: BoardSize = BoardSize.Easy


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memoryBoard = findViewById(R.id.memoryBoard)
        movesButton = findViewById(R.id.moves)
        pairsButton = findViewById(R.id.pairs)
        clRoot = findViewById(R.id.main)

        pairsButton.setTextColor(ContextCompat.getColor(this, R.color.color_progress_none))

        memoryGame = MemoryGame(boardSize)

        adapter = MemoryBoardAdapter(this,boardSize,memoryGame.cards,object : MemoryBoardAdapter.CardClickListener {
            override fun onCardClicked(position: Int) {
                updateGameWithFlip(position)
            }

        })
        memoryBoard.adapter = adapter
        memoryBoard.setHasFixedSize(true);
        memoryBoard.layoutManager = GridLayoutManager(this,boardSize.getWidth());
    }

    private fun  updateGameWithFlip(position: Int) {

        if(memoryGame.haveWonGame()) {
            Snackbar.make(clRoot, "You Won The Game Hurray", Snackbar.LENGTH_LONG).show()
            return
        }
        if (memoryGame.isCardFaceUp(position)) {
            Snackbar.make(clRoot,"The Card Is Face UP",Snackbar.LENGTH_SHORT).show()
            return
        }


        if (memoryGame.flipCard(position)) {

            val color = android.animation.ArgbEvaluator().evaluate(
                memoryGame.numPairsFound.toFloat() / boardSize.getNumPaires(),
                ContextCompat.getColor(this, R.color.color_progress_none),
                ContextCompat.getColor(this, R.color.color_progress_full)
            ) as Int

            pairsButton.setTextColor(color)
            pairsButton.text = "Pairs: ${memoryGame.numPairsFound}/${boardSize.getNumPaires()}"
            if(memoryGame.haveWonGame()) {
                Snackbar.make(clRoot, "You Won The Game Hurray", Snackbar.LENGTH_LONG).show()
            }
        }

        movesButton.text = "Moves: ${memoryGame.getNumMoves()}"
        adapter.notifyDataSetChanged()
    }
}