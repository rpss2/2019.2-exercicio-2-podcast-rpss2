package br.ufpe.cin.android.podcast

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemlista.view.*

class ItemFeedCustomAdapter (private val podcasts: List<ItemFeed>, private val ctx: Context) : RecyclerView.Adapter<ItemFeedCustomAdapter.ViewHolder>() {

    class ViewHolder (item: View) : RecyclerView.ViewHolder(item) {
        val title = item.item_title
        val action = item.item_action
        val date = item.item_date
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.itemlista, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val podcast = podcasts[position]
        holder.title?.text = podcast.title
        holder.date?.text = podcast.pubDate

        holder.action.setOnClickListener {
            Toast.makeText(
                ctx,
                "Baixando ${podcast.title}!",
                Toast.LENGTH_SHORT
            ).show()
        }

        holder.title.setOnClickListener{
            val intent = Intent(ctx, EpisodeDetailActivity::class.java)
            intent.putExtra("title", podcast.title)
            intent.putExtra("description", podcast.description)
            intent.putExtra("link", podcast.link)
            ctx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return podcasts.size
    }
}