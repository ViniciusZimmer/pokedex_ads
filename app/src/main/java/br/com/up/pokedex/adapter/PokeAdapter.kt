package br.com.up.pokedex.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.up.pokedex.PokemonDetailsActivity
import br.com.up.pokedex.R
import br.com.up.pokedex.model.Pokemon
import com.squareup.picasso.Picasso

class PokeAdapter(private val pokemons:List<Pokemon>,
                private val listener:(pokemon:Pokemon) -> Unit ) :
    RecyclerView.Adapter<PokeAdapter.PokeViewHolder>() {
        class PokeViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
            var txtNome = itemView.findViewById<TextView>(R.id.txtNome)
            var img = itemView.findViewById<ImageView>(R.id.imgPokemon)
            var txtNumero = itemView.findViewById<TextView>(R.id.txtNumero)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.poke_item_view, parent, false)
            return PokeViewHolder(view)
        }

        override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
            val pokemon = pokemons[position]

            val id = pokemon.url.replace(
                "https://pokeapi.co/api/v2/pokemon/",
                "").replace("/","")

            val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
            val imageView : ImageView = holder.itemView.findViewById(R.id.imgPokemon)


        Picasso.get().load(url).into(imageView)
            holder.txtNome.text = pokemon.name
            holder.itemView.setOnClickListener {
                listener(pokemon)
            }
            holder.txtNumero.text = '#' + id

        }

        override fun getItemCount(): Int = pokemons.size
    }