package br.com.up.pokedex.model

import com.google.gson.annotations.SerializedName

data class PokemonMoves(
    @SerializedName("move")
    val move: MoveName
)
