package br.com.up.pokedex.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val id: Int,
    val url: String,
    val name: String,
    @SerializedName("types")
    val types: List<PokemonType>,
    @SerializedName("stats")
    val stats: List<PokemonStats>,
    @SerializedName("abilities")
    val abilities: List<PokemonAbilities>,
    @SerializedName("moves")
    val moves: List<PokemonMoves>
)
