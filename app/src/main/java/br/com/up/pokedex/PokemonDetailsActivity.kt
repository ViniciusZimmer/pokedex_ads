package br.com.up.pokedex

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.up.pokedex.adapter.PokeAbilitiesAdapter
import br.com.up.pokedex.adapter.PokeStatsAdapter
import br.com.up.pokedex.adapter.PokeTypeAdapter
import br.com.up.pokedex.network.PokeApi
import br.com.up.pokedex.utils.PokemonTypeUtils
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.button.MaterialButton
import com.skydoves.progressview.ProgressView
import com.squareup.picasso.Picasso
import java.util.stream.Collectors

class PokemonDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = R.layout.activity_pokemon_details
        setContentView(layout)

    val constraintLayout : ConstraintLayout = findViewById(R.id.pokemon_details)
        val button : MaterialButton = findViewById(R.id.toMoves)
        val recyclerViewStats : RecyclerView = findViewById(R.id.poke_recycler_stats)
        val recyclerViewType : RecyclerView = findViewById(R.id.poke_recycler_types)
        val recyclerViewAbilities : RecyclerView = findViewById(R.id.poke_recycler_abilities)



        val pokemonUrl = intent.getStringExtra("pokemonUrl")
        val pokemonId = this.getPokemonId(pokemonUrl!!)

        PokeApi().getPokemonById(pokemonId) { pokemon ->
            val imageView : ImageView = findViewById(R.id.pokemon_png)
            val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$pokemonId.png"

            getSupportActionBar()?.setTitle(pokemon?.name)
            getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

            val type = pokemon?.types?.get(0)?.type?.name
            val typeColor = PokemonTypeUtils.getTypeColor(type!!)
            getSupportActionBar()?.setBackgroundDrawable(getDrawable(typeColor))

            button.backgroundTintList = getColorStateList(typeColor)

            val collapsingToolbarLayout : CollapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout)
            val appBarLayout : AppBarLayout = findViewById(R.id.appBarLayout)

            button.setBackgroundResource(typeColor)
            appBarLayout.setBackgroundResource(typeColor)
            collapsingToolbarLayout.setContentScrimColor(typeColor)
            imageView.setBackgroundResource(typeColor)

            Picasso.get().load(url).into(imageView)

            recyclerViewStats.adapter = PokeStatsAdapter(pokemon!!)
            recyclerViewStats.layoutManager = GridLayoutManager(this, 2)

            recyclerViewType.adapter = PokeTypeAdapter(pokemon, findViewById(R.id.pokeType))
            recyclerViewType.layoutManager = GridLayoutManager(this, 2)

            recyclerViewAbilities.adapter = PokeAbilitiesAdapter(pokemon, findViewById(R.id.pokeAbilities))
            recyclerViewAbilities.layoutManager = GridLayoutManager(this, 2)

            button.setOnClickListener {
                val intent = Intent(this, PokemonMovesActivity::class.java)
                intent.putStringArrayListExtra("pokemonMoves", ArrayList(pokemon.moves.map { pokemonMoves ->  pokemonMoves.move.name }))
                intent.putStringArrayListExtra("pokemonType", ArrayList(pokemon.types.map { pokemonType ->  pokemonType.type.name }))
                startActivity(intent)
            }
        }
    }


    private fun getPokemonId(url: String): String {
        return url.replace("https://pokeapi.co/api/v2/pokemon/", "").replace("/","")
    }
}