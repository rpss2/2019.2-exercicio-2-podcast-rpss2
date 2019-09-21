package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.doAsync
import java.net.URL
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Declarando lista de objetos do tipo ItemFeed
        var podcasts : List<ItemFeed> = emptyList()
        listFeed.layoutManager = LinearLayoutManager(this)
        val database = ItemFeedDB.getDatabase(this)

        doAsync {
            try {
                //Carregando a URL dos podcasts
                val url = "https://s3-us-west-1.amazonaws.com/podcasts.thepolyglotdeveloper.com/podcast.xml"
                //Lendo conteúdo da URL
                val xml = URL(url).readText()
                // Realizando o parse dos arquivos da URL
                podcasts = Parser.parse(xml)

                database.ItemFeedDAO().pushList(podcasts)
            } catch (e: Throwable) {
                Log.e("Dale deu Erro: ", e.message.toString())
                podcasts = database.ItemFeedDAO().getAll()
            } finally {
                uiThread {
                    listFeed.adapter = ItemFeedCustomAdapter(podcasts, this@MainActivity)
                }
            }
        }
    }
}
