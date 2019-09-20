package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.doAsync
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Declarando lista de objetos do tipo ItemFeed
        var podcasts : List<ItemFeed> = emptyList()

        doAsync {
            //Carregando a URL dos podcasts
            val url = "https://s3-us-west-1.amazonaws.com/podcasts.thepolyglotdeveloper.com/podcast.xml"
            //Lendo conteúdo da URL
            val xml = URL(url).readText()
            // Realizando o parse dos arquivos da URL
            podcasts = Parser.parse(xml)
        }
    }
}
