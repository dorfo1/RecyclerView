package rodolfo.com.br.recyclerview.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_lista.*
import rodolfo.com.br.recyclerview.R
import rodolfo.com.br.recyclerview.api.getPokemonAPI
import rodolfo.com.br.recyclerview.model.Pokemon
import rodolfo.com.br.recyclerview.model.PokemonResponse
import rodolfo.com.br.recyclerview.ui.adapter.ListaPokemonAdapter

class ListaActivity : AppCompatActivity() {

    private var disposable : Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        carregarDados()
    }


    private fun carregarDados(){
        getPokemonAPI()
                .buscar(300)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<PokemonResponse> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(t: PokemonResponse) {
                        exibeLista(t.content)
                    }

                    override fun onError(e: Throwable) {
                        exibeErro(e.message)
                    }
                })
    }

    private fun exibeErro(message: String?) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
    }

    private fun exibeLista(pokemons : List<Pokemon>){
        rv_lista.layoutManager = LinearLayoutManager(applicationContext)
        rv_lista.adapter = ListaPokemonAdapter(applicationContext,pokemons,{
            Toast.makeText(applicationContext,it.name + it.number,Toast.LENGTH_SHORT).show()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
