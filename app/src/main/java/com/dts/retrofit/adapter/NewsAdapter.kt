package com.dts.retrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dts.retrofit.databinding.ViewItemNewsBinding
import com.dts.retrofit.model.NewsModel
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val time = SimpleDateFormat("dd.MMM.yy HH:mm", Locale.US)
    private val items = mutableListOf<NewsModel>()
    private var onItemClickListener: ((NewsModel) -> Unit)? = null

    inner class NewsViewHolder(private val binding: ViewItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: NewsModel, listener: ((NewsModel) -> Unit)?) {
            binding.tvNewsMsg.text = news.title
            binding.tvAuthor.text = news.author
            binding.tvDate.text = time.format(news.createAt)
            binding.root.setOnClickListener {
                listener?.invoke(news)
            }
        }
    }

    fun addNews(newList: List<NewsModel>) {
        val difutil = DiffUtil.calculateDiff(DifUtilUpdate(items, newList))
        items.clear()
        items.addAll(newList)
        difutil.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ViewItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(items[position], onItemClickListener)
    }

    override fun getItemCount(): Int = items.size

    fun setOnItemClickListener(news: (NewsModel) -> Unit) {
        onItemClickListener = news
    }

    inner class DifUtilUpdate(
        private val oldItems: List<NewsModel>,
        private val newItems: List<NewsModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItems.size
        }

        override fun getNewListSize(): Int {
            return newItems.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] == newItems[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] == newItems[newItemPosition]
        }
    }
}
