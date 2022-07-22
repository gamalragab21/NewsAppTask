package com.example.newsapptask.presentation.fragments.main.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.newsapptask.databinding.ItemNewsLayoutBinding
import com.example.newsapptask.domain.models.NewsData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.datetime.*
import javax.inject.Inject

class MainAdapter @Inject constructor(
    private val glide: RequestManager,
    @ApplicationContext private val context: Context,
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {


    //
    var news: List<NewsData>
        get() = differ.currentList
        set(value) = differ.submitList(value)


    private val diffCallback = object : DiffUtil.ItemCallback<NewsData>() {
        override fun areContentsTheSame(
            oldItem: NewsData,
            newItem: NewsData,
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        //
        override fun areItemsTheSame(
            oldItem: NewsData,
            newItem: NewsData,
        ): Boolean {
            return oldItem.publishedDate == newItem.publishedDate
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)

    inner class MainViewHolder(val itemBinding: ItemNewsLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindData(item: NewsData) {
            glide.load(item.thumbnailStandard).into(itemBinding.itemNewsImageHolder)
            itemBinding.itemNewsDateTv.text = item.publishedDate.substringBefore("T")
            itemBinding.itemNewsTitleTv.text = item.slugName
            itemBinding.itemNewsSectionName.text = item.section
            item.subsection.also {
                itemBinding.itemNewsSubSectionName.text = if (it.isNotEmpty()) it else "UNKnown"
            }
            itemBinding.root.setOnClickListener {
                onItemClickListener?.let { action ->
                    action(item)
                }
            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemBinding =
            ItemNewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(itemBinding)
    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val tran = news[position]


        holder.apply {
            bindData(tran)
        }


    }


    override fun getItemCount(): Int = news.size


    private var onItemClickListener: ((NewsData) -> Unit)? = null

    fun setOnItemClickListener(listener: (NewsData) -> Unit) {
        onItemClickListener = listener
    }
//    fun getTime(timezoneId: String): String {
//        // 1
//        val timezone = TimeZone.of(timezoneId)
//// 2
//        val currentMoment: Instant = Clock.System.now()
//        // 3
//        val dateTime: LocalDateTime =
//            currentMoment.toLocalDateTime(timezone)
//// 4
//        return formatDateTime(dateTime)
//    }

}