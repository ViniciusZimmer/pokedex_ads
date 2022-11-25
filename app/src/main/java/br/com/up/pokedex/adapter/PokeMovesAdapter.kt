package br.com.up.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.up.pokedex.R
import java.util.ArrayList

class PokeMovesAdapter(private val pokemonMoves: ArrayList<String>) : RecyclerView.Adapter<PokeMovesAdapter.PokeMovesViewHolder>() {

    inner class PokeMovesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeMovesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layout = layoutInflater.inflate(R.layout.poke_move_item_view, parent, false)


        return PokeMovesViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PokeMovesViewHolder, position: Int) {
        val data : TextView = holder.itemView.findViewById(R.id.data2)
        data.text = pokemonMoves[position]
    }

    override fun getItemCount(): Int {
        return pokemonMoves.size
    }
}