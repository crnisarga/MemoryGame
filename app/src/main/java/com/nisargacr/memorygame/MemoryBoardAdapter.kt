package com.nisargacr.memorygame

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.nisargacr.memorygame.models.BoardSize
import com.nisargacr.memorygame.models.MemoryCard
import kotlin.math.min

class MemoryBoardAdapter(
    private val context: Context,
    private val boardSize: BoardSize,
    private val cards: List<MemoryCard>,
    private val cardClickListener: CardClickListener
) :
    RecyclerView.Adapter<MemoryBoardAdapter.ViewHolder>() {

        companion object {
            private const val MarginSize = 10
        }

    interface CardClickListener {
        fun onCardClicked(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardWidth = parent.width / boardSize.getWidth() - (2 * MarginSize)
        val cardHeight = parent.height / boardSize.getHeight() - (2 * MarginSize)
        val cardSideLength = min(cardHeight,cardWidth)
        val view =  LayoutInflater.from(context).inflate(R.layout.memory_card,parent,false)
        val layoutParams = view.findViewById<CardView>(R.id.cardView).layoutParams as MarginLayoutParams
        layoutParams.width = cardSideLength
        layoutParams.height = cardSideLength
        layoutParams.setMargins(MarginSize, MarginSize, MarginSize, MarginSize)
        return ViewHolder(view)
    }

    override fun getItemCount() = boardSize.numCards

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private  val imageButton = itemView.findViewById<ImageButton>(R.id.imageButton)
        fun bind(position: Int) {
            val cardMemory = cards[position]
            imageButton.setImageResource(
                if (cardMemory.isFaceUp)
                cardMemory.identifier
                else
                R.drawable.ic_launcher_background
            )
            imageButton.setOnClickListener {
                Log.d("myWork","the image clicked is in position $position")
                cardClickListener.onCardClicked(position)
            }
        }
    }
}
