package rodolfo.com.br.recyclerview.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(val content:List<Pokemon>)


data class Pokemon(
        val number : String,
        val name : String,
        @SerializedName("imageURL") val image : String
        )
