package com.progdeelite.dca.view_types

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.progdeelite.dca.R
import com.progdeelite.dca.util_extension.inflate

sealed class ViewItem {

    data class Title(
        @StringRes val title: Int = -1,
    ) : ViewItem()

    data class TitleSubTitle(
        @StringRes val title: Int = -1,
        @StringRes val subtitle: Int = -1
    ) : ViewItem()

    data class Ad(
        @DrawableRes val adBanner: Int = -1,
    ) : ViewItem()
}

@Suppress("UNCHECKED_CAST")
class ViewItemAdapter(private val listener: ClickListener) : BaseAdapter<ViewItem>() {

    interface ClickListener {
        fun onListItemClicked(item: ViewItem)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewItem> {
        val itemView = parent.inflate(viewType)
        return when (viewType) {
            R.layout.view_item_title -> TitleViewHolder(itemView, listener)
            R.layout.view_item_title_subtitle -> TitleSubTitleViewHolder(itemView, listener)
            R.layout.view_item_ad_banner -> AdBannerViewHolder(itemView, listener)
            else -> throw IllegalStateException("unknown view type: $viewType")
        } as BaseViewHolder<ViewItem>
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ViewItem.Ad -> R.layout.view_item_ad_banner
            is ViewItem.Title -> R.layout.view_item_title
            is ViewItem.TitleSubTitle -> R.layout.view_item_title_subtitle
        }
    }

    override fun getDiffUtilCallback(
        oldItems: List<ViewItem>,
        newItems: List<ViewItem>
    ) =
        SettingsDiffUtilCallback(oldItems, newItems)

    inner class TitleSubTitleViewHolder(view: View, private val listener: ClickListener) :
        BaseAdapter.BaseViewHolder<ViewItem.TitleSubTitle>(view) {
        override fun bindView(item: ViewItem.TitleSubTitle) {
            with(itemView) {
                findViewById<TextView>(R.id.title_text).setText(item.title)
                findViewById<TextView>(R.id.subtitle_text).setText(item.subtitle)
                setOnClickListener { listener.onListItemClicked(item) }
            }
        }
    }

    inner class TitleViewHolder(view: View, private val listener: ClickListener) :
        BaseAdapter.BaseViewHolder<ViewItem.Title>(view) {
        override fun bindView(item: ViewItem.Title) {
            with(itemView) {
                findViewById<TextView>(R.id.title_text).setText(item.title)
                setOnClickListener { listener.onListItemClicked(item) }
            }
        }
    }

    inner class AdBannerViewHolder(view: View, private val listener: ClickListener) :
        BaseAdapter.BaseViewHolder<ViewItem.Ad>(view) {
        override fun bindView(item: ViewItem.Ad) {
            with(itemView) {
                val ad = findViewById<ImageView>(R.id.ad_banner_img)
                ad.setImageResource(item.adBanner)
                ad.setOnClickListener {
                    Toast.makeText(context,"Navega para propaganda", Toast.LENGTH_SHORT).show()
                }
                setOnClickListener { listener.onListItemClicked(item) }
            }
        }
    }

    inner class SettingsDiffUtilCallback(
        private val oldItems: List<ViewItem>,
        private val newItems: List<ViewItem>
    ) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldItems.size
        override fun getNewListSize(): Int = newItems.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]

        // We use a == b as this translates to a?.equals(b) ?: (b === null), which means that we check for structural equality
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<T>>() {

    protected val items: MutableList<T> = mutableListOf()

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) =
        holder.bindView(items[position])

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    open fun setItems(newItems: List<T>) {
        getDiffUtilCallback(items.toList(), newItems)
            .also { items.clear() }
            .also { items.addAll(newItems) }
            ?.let { DiffUtil.calculateDiff(it).dispatchUpdatesTo(this) }
            ?: notifyDataSetChanged()
    }

    open fun getDiffUtilCallback(oldItems: List<T>, newItems: List<T>): DiffUtil.Callback? = null

    abstract class BaseViewHolder<V>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bindView(item: V)
    }

}