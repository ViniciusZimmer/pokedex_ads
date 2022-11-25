package br.com.up.pokedex.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.up.pokedex.R
import br.com.up.pokedex.model.Pokemon
import br.com.up.pokedex.utils.PokemonTypeUtils
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.textview.MaterialTextView

class PokeTypeAdapter(private val pokemon: Pokemon, private val pokeType : TextView) : RecyclerView.Adapter<PokeTypeAdapter.PokeTypeViewHolder>() {

    inner class PokeTypeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeTypeViewHolder {
        pokeType.text = "Tipo"
        val layoutInflater = LayoutInflater.from(parent.context)
        val layout = layoutInflater.inflate(R.layout.poke_text_view, parent, false)

        return PokeTypeViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PokeTypeViewHolder, position: Int) {
        val type = pokemon.types[position].type.name
        val data : MaterialTextView = holder.itemView.findViewById(R.id.data)


        data.text = type
    }

    override fun getItemCount(): Int {
        return pokemon.types.size
    }
}