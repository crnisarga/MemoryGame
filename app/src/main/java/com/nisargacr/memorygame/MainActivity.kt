package com.nisargacr.memorygame

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var memoryBoard : RecyclerView
    private lateinit var movesButton : TextView
    private lateinit var pairsButton : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memoryBoard = findViewById(R.id.memoryBoard)
        movesButton = findViewById(R.id.moves)
        pairsButton = findViewById(R.id.pairs)

        memoryBoard.adapter = MemoryBoardAdapter(this,8);
        memoryBoard.setHasFixedSize(true);
        memoryBoard.layoutManager = GridLayoutManager(this,2);
    }
}