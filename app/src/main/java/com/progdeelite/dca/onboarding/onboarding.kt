package com.progdeelite.dca.onboarding

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.progdeelite.dca.R
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.progdeelite.dca.util_extension.inflate


data class IntroSlide(
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)

class IntroSlideAdapter(items: List<IntroSlide>) : BaseAdapter<IntroSlide>() {

    init {
        setItems(items)
    }

    inner class IntroSlideViewHolder(view: View) : BaseAdapter.BaseViewHolder<IntroSlide>(view) {
        override fun bindView(item: IntroSlide) {
            with(itemView) {
                findViewById<ImageView>(R.id.intro_slide_icon).setImageResource(item.icon)
                findViewById<TextView>(R.id.intro_slide_title).setText(item.title)
                findViewById<TextView>(R.id.intro_slide_description).setText(item.description)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<IntroSlide> {
        val itemView = parent.inflate(viewType)
        return when (viewType) {
            R.layout.intro_slide -> IntroSlideViewHolder(itemView)
            else -> throw IllegalStateException("unknown view type: $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        // UM EXEMPLO DIDÁTICO
        return when(position){
            0 -> R.layout.intro_slide
            items.size - 1 -> R.layout.intro_slide
            else -> R.layout.intro_slide
        }
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