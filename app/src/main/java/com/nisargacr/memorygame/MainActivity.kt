package com.nisargacr.memorygame

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var memoryBoard : RecyclerView
    private lateinit var movesButton : TextView
    private lateinit var pairsButton : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        memoryBoard = findViewById(R.id.memoryBoard)
        movesButton = findViewById(R.id.moves)
        pairsButton = findViewById(R.id.pairs)
    }
}