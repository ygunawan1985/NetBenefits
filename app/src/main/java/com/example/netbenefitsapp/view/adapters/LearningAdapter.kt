package com.example.netbenefitsapp.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.model.Article
import com.example.netbenefitsapp.view.activities.webview.LibraryWebViewActivity
import kotlinx.android.synthetic.main.article_item.view.*

class LearningAdapter(private val articles : ArrayList<Article>) : RecyclerView.Adapter<LearningAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false))

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.setValues(articles[position])


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var mArticle: Article

        fun setValues(article: Article) {
            itemView.tvTitle.text = article.title
            itemView.tvDescription.text = article.description
            this.mArticle = article
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val intent = Intent(view?.context, LibraryWebViewActivity::class.java)
            intent.putExtra("libraryUrl", mArticle.url)
            view?.context?.startActivity(intent)
        }
    }
}