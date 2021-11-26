package com.progdeelite.dca.recyclerview

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

// 1) Definir o BaseAdapter Generico
// 2) Criar uma implementação referência do viewHolder
// 3) Criar uma implementacão referência do adapter

abstract class BaseAdapter<VIEW_ITEM> : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<VIEW_ITEM>>() {

    protected val items: MutableList<VIEW_ITEM> = mutableListOf()

    override fun onBindViewHolder(holder: BaseViewHolder<VIEW_ITEM>, position: Int) =
        holder.bindView(items[position])

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    open fun setItems(newItems: List<VIEW_ITEM>) {
        getDiffUtilCallback(items.toList(), newItems)
            .also { items.clear() }
            .also { items.addAll(newItems) }
            ?.let { DiffUtil.calculateDiff(it).dispatchUpdatesTo(this) }
            ?: notifyDataSetChanged()
    }

    open fun getDiffUtilCallback(oldItems: List<VIEW_ITEM>, newItems: List<VIEW_ITEM>): DiffUtil.Callback? = null

    abstract class BaseViewHolder<V>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bindView(item: V)
    }

    interface OnItemClickListener<T>{
        fun onItemClicked(item: T)
    }
}
