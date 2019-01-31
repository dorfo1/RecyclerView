package rodolfo.com.br.recyclerview.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rodolfo.com.br.recyclerview.model.PokemonResponse

interface PokemonAPI {

    @GET("/api/pokemon/")
    fun buscar(@Query("size")size : Int): Observable<PokemonResponse>

}