package rodolfo.com.br.recyclerview.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.pokemon_row.view.*
import rodolfo.com.br.recyclerview.R
import rodolfo.com.br.recyclerview.api.getPicassoAuth
import rodolfo.com.br.recyclerview.model.Pokemon

class ListaPokemonAdapter(private val context : Context,
                          private val pokemons : List<Pokemon>,
                          private val listener: (Pokemon) ->Unit) : RecyclerView.Adapter<ListaPokemonAdapter.PokemonHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pokemon_row,parent,false)
        return PokemonHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        var pokemon = pokemons.get(position)
        if(position==0){
            Log.d("TESTE",pokemon.image)
        }
        holder.bindView(pokemon,listener)
    }


    class PokemonHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)  {


        fun bindView(pokemon : Pokemon, listener: (Pokemon) -> Unit) = with(itemView){
            pokemon_name.text = pokemon.name
            pokemon_number.text = pokemon.number
            getPicassoAuth(itemView.context).load("https://pokedexdx.herokuapp.com" + pokemon.image).into(pokemon_icon)
            itemView.setOnClickListener{
                listener(pokemon)
            }
        }


    }
}